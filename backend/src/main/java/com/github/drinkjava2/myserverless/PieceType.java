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

import com.github.drinkjava2.myserverless.util.MyStrUtils;

/**
 * PieceType is the type of SqlJavaPiece, can be: 
 * QRY(a piece of query SQL), 
 * EXECUTE(a piece of query SQL),
 * JAVA (a piece of java in a class extended from ServletTemplate),
 * UNKNOW
 * 
 * @author Yong Zhu
 * @since 1.0.1
 */
public enum PieceType {
	QRY, EXECUTE, JAVA, UNKNOW;

	/** Return a PieceType instance based on method name */
	public static PieceType byRemoteMethodName(String method) {
	    if(MyStrUtils.isEmpty(method))
	        return PieceType.UNKNOW;
		if (method.startsWith("qry"))
			return PieceType.QRY;
	      if (method.startsWith("execute"))
	            return PieceType.EXECUTE;
		if (method.startsWith("java"))
			return PieceType.JAVA;
		return PieceType.UNKNOW;
	}
}
