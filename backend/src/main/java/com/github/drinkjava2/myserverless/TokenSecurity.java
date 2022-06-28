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

/**
 * TokenSecurity should implemented by user
 * 
 * @author Yong Zhu
 */
public interface TokenSecurity {

    /**
     * According given userId and password, create a myToken string, return null if password is not right
     * @param userId 
     * @param password 
     * @return myToken or null
     */
    public String login(String userId, String password); 

    /**
     * By given myToken and MyServerless methodId, check if allow execute
     * 
     * @param myToken current user's myToken
     * @param methodId cureent method id
     * @param hotCompile require hot compile if it's SQL or Java piece received from frontend
     * @return true if allow execute
     */
    public boolean allow(String myToken, String methodId, boolean hotCompile);

}
