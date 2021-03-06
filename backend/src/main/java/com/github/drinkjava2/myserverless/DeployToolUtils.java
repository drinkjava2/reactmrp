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
                if(MyStrUtils.containsIgnoreCase(methodId, "FRONTEND")) { // ????????????ID??????FRONTEND??????????????????????????????????????????????????????
                    formated = restoreKeyToOriginText(formated, key, piece); 
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
		if (text.contains(wrongStart)) { //??????????????????????????????????????????????????????????????? 
		    System.err.println("ERROR: in file '"+frontFile.getName() +"', should use ` instead of use '");
		    System.exit(1);
		}
		if (!text.contains("$"+MyServerlessEnv.call_server_method+"(`")) //??????????????????????????????????????????,?????????????????????
			return;

		boolean changed = false;
		Map<String, SqlJavaPiece> map = new LinkedHashMap<String, SqlJavaPiece>();
		//????????????????????????????????????????????????????????????????????????????????????????????????SqlJavaPiece??????map???
		String formated = formatText(frontFile, text, map, rightStart, '`');
        for (Entry<String, SqlJavaPiece> item : map.entrySet()) {
            String key = item.getKey();
            SqlJavaPiece piece = item.getValue();
            String javaSrcFileName;
            javaSrcFileName = MyServerlessEnv.getSrcDeployFolder() + "/" + piece.getOriginText() + ".java"; // .../com/xx/xxx/deploy/PUBLIC_q1h8arktaif5ljzab96k.java
            String src = MyFileUtils.readFile(javaSrcFileName, "UTF-8"); //deploy????????????java?????????
            String template = null;
            if (MyStrUtils.isEmpty(src)) { //????????????java???????????????
                formated = restoreKeyToOriginText(formated, key, piece);
                continue;
            }

            template = MyStrUtils.substringBetween(src, "extends", "Template"); //com.xxx.template.XxXxxx
            template = MyStrUtils.substringAfterLast(template, "."); //XxXxxx
            String remoteMethod = MyStrUtils.toLowerCaseFirstOne(template); //xxXxxx
            SqlJavaPiece newPiece = SqlJavaPiece.parseFromJavaSrcFile(javaSrcFileName); 
            String methodId = MyStrUtils.substringBefore(piece.getOriginText(), "_"); //PUBLIC
            if (MyStrUtils.isEmpty(piece.getOriginText()) || MyStrUtils.containsIgnoreCase(methodId, "BACKEND")) { //????????????BACKEND?????????
                formated = restoreKeyToOriginText(formated, key, piece);
                continue;
            }

            ////?????????????????????????????????????????????????????????
            changed = true;
            if ("default".equalsIgnoreCase(methodId)) //default???????????????methodId???
                methodId = "";
            else
                methodId = "#" + methodId + " ";
            String newPieceStr = SrcBuilder.createFrontText(PieceType.byRemoteMethodName(remoteMethod), newPiece); //??????????????????????????????JavaSqlPiece??????
            formated = MyStrUtils.replaceFirst(formated, key, "$" + remoteMethod + "(`" + methodId + newPieceStr + "`"); //??????????????????????????????key
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
