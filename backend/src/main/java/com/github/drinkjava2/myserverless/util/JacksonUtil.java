/*
 * Copyright 2022 the original author or authors.
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Small Jackson util， all methods will return null if exception happen
 *  
 * @author Yong
 */
public class JacksonUtil {
    //为了避免每次 new ObjectMapper()，这里全局使用单例。如果有不同配置就生成多个不同配置的单例
    public static final ObjectMapper singleTonObjectMapper = new ObjectMapper();
    public static final ObjectMapper singleTonObjectMapper_NONEMPTY = new ObjectMapper();
    public static final ObjectMapper singleTonObjectMapper_NONNULL = new ObjectMapper();

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        singleTonObjectMapper_NONEMPTY.setSerializationInclusion(Include.NON_EMPTY);
        singleTonObjectMapper_NONEMPTY.setDateFormat(dateFormat);

        singleTonObjectMapper_NONNULL.setSerializationInclusion(Include.NON_NULL);
        singleTonObjectMapper_NONNULL.setDateFormat(dateFormat);
    }

    /**
     * 从json字符串中直接取指定的节点解析为对象实例, ，通常是整数或字符串，使用示例如下：
     * Object obj = JsonUtil.get(json,"node1.node2.x.node4"); //x表示列表下标序号
     * 如获取失败返回null 
     */
    @SuppressWarnings("unchecked")
    public static Object get(String json, String nodeKey) {
        try {
            Object result = singleTonObjectMapper.readValue(json, Object.class);
            int pos;
            String key = nodeKey;
            do {
                pos = key.indexOf(".");
                if (pos >= 0) {
                    if (result == null)
                        throw new RuntimeException("Node key '" + nodeKey + "' does not exist.");
                    if (result instanceof Map)
                        result = ((Map<String, Object>) result).get(key.substring(0, pos));
                    else
                        result = ((List<?>) result).get(Integer.parseInt(key.substring(0, pos)));
                    key = key.substring(pos + 1);
                } else {
                    if (result == null)
                        throw new RuntimeException("Node key '" + nodeKey + "' does not exist.");
                    if (result instanceof Map)
                        result = ((Map<String, Object>) result).get(key);
                    else
                        result = ((List<?>) result).get(Integer.parseInt(key));
                    return result;
                }

            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** 把json字符串转为指定类的实例对象, 如失败返回null  */
    public static <T> T toObj(String json, Class<T> claz) {
        try {
            return singleTonObjectMapper.readValue(json, claz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** 把json字符串转为JSONObject, 如失败返回null */
    public static JSONObject toJSONObject(String json) {
        try {
            return singleTonObjectMapper.readValue(json, JSONObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /** 把object转为json, 如失败返回null  */
    public static String toJSON(Object object) {
        ObjectWriter objectWriter = singleTonObjectMapper_NONEMPTY.writer();
        return toJSON(object, objectWriter);
    }

    /** 把object转为格式化json, 如失败返回null  */
    public static String toJSONFormatted(Object object) {
        ObjectWriter objectWriter = singleTonObjectMapper_NONNULL.writer().withDefaultPrettyPrinter();
        return toJSON(object, objectWriter);
    }

    /** 把json字符串格式化, 如失败返回null  */
    public static String formatJSON(String input) {
        Object json;
        String indented = "";
        try {
            json = singleTonObjectMapper.readValue(input, Object.class);
            indented = singleTonObjectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return indented;
    }

    /** 私有方法，以指定的ObjectWriter转object为json字符串 , 如失败返回null */
    private static String toJSON(Object object, ObjectWriter objectWriter) {
        try {
            return objectWriter.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAsText(JsonNode node, String field) {
        JsonNode n = node.get(field);
        if (n == null)
            return null;
        String s = n.asText();
        if (MyStrUtils.isEmpty(s))
            s = n.toString();
        return s;
    }
}