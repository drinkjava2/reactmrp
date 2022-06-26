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
package text;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.drinkjava2.myserverless.util.JacksonUtil;
import com.github.drinkjava2.myserverless.util.Txt;

/**
 * Store multiple line Strings for unit test
 */

public class Texts {

    public static class DemoTxt extends Txt {
        /*-  
         public class A {
          public String name="a"; 
         } 
         */
    }

    public static class t2 extends Txt {
        /*-  
        {"a":"1",
         "b": "a",
          "c":{"ab\"cd":"1",
            "b":1 
          },
         "d":{"a":"1",
               "b":1,
               "c":{"a":"1",
                       "b":2 
                      },
                "e":[1,{"a":5},3]       
            } 
            
        }
         */
    }

    public static void main(String[] args) {
        String s = new t2().toString();
        System.out.println(s);
        JsonNode jn = null;
        try {
            jn = new ObjectMapper().readTree(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(jn);
        System.out.println(JacksonUtil.get(s, "d.e.1.a"));

        try {
            Object result = JacksonUtil.singleTonObjectMapper.readValue(s, Object.class);
            System.out.println(result);
            System.out.println(result.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}