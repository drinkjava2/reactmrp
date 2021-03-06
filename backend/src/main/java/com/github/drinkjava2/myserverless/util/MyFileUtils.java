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
package com.github.drinkjava2.myserverless.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * File Utilities usually used inside of of this project
 * 
 * @author Yong Zhu
 * @since 1.0.0
 */
public class MyFileUtils {

	private MyFileUtils() {
		// default constructor
	}

	public static void writeFile(String fileFullPath, byte[] byteArry) {
		File file = new File(fileFullPath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(byteArry);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					try {
						fos.flush();
					} catch (Exception e) {
					}
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void writeAndPrintFilename(String fileFullPath, String text, String encoding) {
	    System.out.println("Write file:"+fileFullPath);
	    writeFile(fileFullPath, text, encoding);
	}

	public static void writeFile(String fileFullPath, String text, String encoding) { 
		File file = new File(fileFullPath);
		if (!file.getParentFile().exists())
			file.getParentFile().mkdirs();
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			byte[] bytes;
			bytes = text.getBytes(encoding);
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					try {
						fos.flush();
					} catch (Exception e) {
					}
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String readFile(String fileFullPath, String encoding) {
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(new File(fileFullPath));
		} catch (FileNotFoundException e1) {
			return null;
		}
		try {
			return inputStreamToString(inputStream, encoding);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	public static String inputStreamToString(InputStream inputStream, String encoding) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1)
			result.write(buffer, 0, length);
		return result.toString(encoding);
	}

	public static void appendFile(String fileName, String content) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName, true);
			fos.write(content.getBytes());
			fos.write("\r\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					try {
						fos.flush();
					} catch (Exception e) {
					}
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
