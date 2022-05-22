/* Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.github.drinkjava2.myserverless;

import static com.github.drinkjava2.myserverless.util.MyStrUtils.isEmpty;
import static com.github.drinkjava2.myserverless.util.MyStrUtils.positionOfPureChar;
import static com.github.drinkjava2.myserverless.util.MyStrUtils.substringAfter;
import static com.github.drinkjava2.myserverless.util.MyStrUtils.substringBefore;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.drinkjava2.myserverless.util.MyFileUtils;
import com.github.drinkjava2.myserverless.util.MyStrUtils; 

/**
 * Store static methods for DeployTool
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
@SuppressWarnings("all")
public class DeployToolUtils {

	/**
	 * Extract sql/java to server side for one html/javascript file, if forceDeploy
	 * is true, ignore if have FRONT control word, force extract to server side
	 */
	public static void oneFileToServ(List<SqlJavaPiece> sqlJavaPieces, File frontFile, boolean forceDeploy) {
		String text = MyFileUtils.readFile(frontFile.getAbsolutePath(), "UTF-8");
		Map<String, SqlJavaPiece> formatedMap = new LinkedHashMap<String, SqlJavaPiece>();
		boolean changed = false;
		String formated = text;

		for (Entry<String, Class<?>> entry : MyServerlessEnv.methodTemplates.entrySet()) {
			String remoteMethod = entry.getKey();
			String remoteMtd_ = "$" + remoteMethod + "(`";
			Class<?> templateClass = entry.getValue();
			formatedMap.clear();
			formated = formatText(frontFile, formated, formatedMap, remoteMtd_, '`');
            for (Entry<String, SqlJavaPiece> item : formatedMap.entrySet()) {
                String key = item.getKey();
                SqlJavaPiece piece = item.getValue();
                String className = piece.getClassName();
                String methodId=piece.getMethodId();
                if(MyStrUtils.containsIgnoreCase(methodId, "frontend")) {
                    formated = restoreKeyToOriginText(formated, key, piece); // 如果方法ID包含frontend字样，则永远保持在前端，不变换到后端。回填占位key
                    continue;
                }
                
                changed = true; 
                String src = SrcBuilder.createSourceCode(templateClass, PieceType.byRemoteMethodName(remoteMethod), piece);
                MyFileUtils.writeAndPrintFilename(MyServerlessEnv.getSrcDeployFolder() + "/" + className + ".java", src, "UTF-8");
                formated = MyStrUtils.replaceFirst(formated, key, "$"+MyServerlessEnv.call_server_method+"(`" + className + "`");
                
                piece.setLocation(frontFile.getAbsolutePath());
                sqlJavaPieces.add(piece);
            }
		}

		if (changed) {
			MyFileUtils.writeAndPrintFilename(frontFile.getAbsolutePath(), formated, "UTF-8");
		}
	}

	/**
	 * Format text to detailed SqlJavaPiece map, return formatted string with key place holder
	 */
	private static String formatText(File file, String oldText, Map<String, SqlJavaPiece> map, String start,
			char endChar) {
		String result = oldText;
		boolean needTransfer = result.contains(start);
		while (needTransfer) {
			String front = substringBefore(result, start);
			String left = substringAfter(result, start);
			if (isEmpty(left))
				throw new IllegalArgumentException(
						"Error: " + start + " not closed ` in file: " + file.getAbsolutePath());

			int pos = positionOfPureChar(left, endChar);
			if (pos == -1)
				throw new IllegalArgumentException(
						"Error: " + start + " not found end ` in file: " + file.getAbsolutePath());

			String piece = left.substring(0, pos);
			String leftover = left.substring(pos + 1);
			SqlJavaPiece parsed = SqlJavaPiece.parseFromFrontText(start, piece);
			String key = "key" + MyStrUtils.getRandomString(20);
			map.put(key, parsed);
			result = front + key + leftover;
			needTransfer = result.contains(start);
		}
		return result;
	}

	/**
	 * Push back sql/java source code  from server side to HTML/HTM/JSP
	 */
	public static void oneFileToFront(File frontFile, boolean forceGoFront, List<String> toDeleteJavas, boolean force) {
	    String rightStart="$"+MyServerlessEnv.call_server_method+"(`";
	    String wrongStart="$"+MyServerlessEnv.call_server_method+"('";
	    
		String text = MyFileUtils.readFile(frontFile.getAbsolutePath(), "UTF-8");
		if (text.contains(wrongStart)) { //如果使用单引号而不是反单引号，直接报错退出 
		    System.err.println("ERROR: in file '"+frontFile.getName() +"', should use ` instead of use '");
		    System.exit(1);
		}
		if (!text.contains("$"+MyServerlessEnv.call_server_method+"(`")) //如果没有发现要调用后端源码的,则跳过这个文件
			return;

		boolean changed = false;
		Map<String, SqlJavaPiece> map = new LinkedHashMap<String, SqlJavaPiece>();
		//下面这句把所有调用后端的地方用占位符表示，将前端部分的信息解析成SqlJavaPiece放到map里
		String formated = formatText(frontFile, text, map, rightStart, '`');
        for (Entry<String, SqlJavaPiece> item : map.entrySet()) {
            String key = item.getKey();
            SqlJavaPiece piece = item.getValue();
            String javaSrcFileName;
            javaSrcFileName = MyServerlessEnv.getSrcDeployFolder() + "/" + piece.getOriginText() + ".java"; // .../com/xx/xxx/deploy/PUBLIC_q1h8arktaif5ljzab96k.java
            String src = MyFileUtils.readFile(javaSrcFileName, "UTF-8"); //deploy目录下的java类源码
            String template = null;
            if (MyStrUtils.isEmpty(src)) { //没有找到java文件或为空
                formated = restoreKeyToOriginText(formated, key, piece);
                continue;
            }

            template = MyStrUtils.substringBetween(src, "extends", "Template"); //com.xxx.template.XxXxxx
            template = MyStrUtils.substringAfterLast(template, "."); //XxXxxx
            String remoteMethod = MyStrUtils.toLowerCaseFirstOne(template); //xxXxxx
            SqlJavaPiece newPiece = SqlJavaPiece.parseFromJavaSrcFile(javaSrcFileName); 
            String methodId = MyStrUtils.substringBefore(piece.getOriginText(), "_"); //PUBLIC
            if (MyStrUtils.isEmpty(piece.getOriginText()) || MyStrUtils.containsIgnoreCase(methodId, "backend")) { //如果包含backend就跳过
                formated = restoreKeyToOriginText(formated, key, piece);
                continue;
            }

            ////否则就开始替换并删除一个对应后端的源码
            changed = true;
            if ("default".equalsIgnoreCase(methodId)) //default表示没有起methodId的
                methodId = "";
            else
                methodId = "#" + methodId + " ";
            String newPieceStr = SrcBuilder.createFrontText(PieceType.byRemoteMethodName(remoteMethod), newPiece); //根据后端源码生成前端JavaSqlPiece片段
            formated = MyStrUtils.replaceFirst(formated, key, "$" + remoteMethod + "(`" + methodId + newPieceStr + "`"); //用后端的源码替换前端key
            toDeleteJavas.add(javaSrcFileName);

        }
		map.clear();
		if (changed) {
			MyFileUtils.writeAndPrintFilename(frontFile.getAbsolutePath(), formated, "UTF-8");
		}
	}

    private static String restoreKeyToOriginText(String formated, String key, SqlJavaPiece piece) {
        formated = MyStrUtils.replaceFirst(formated, key, "$" + MyServerlessEnv.call_server_method + "(`" + piece.getOriginText() + "`");
        return formated;
    }

}
