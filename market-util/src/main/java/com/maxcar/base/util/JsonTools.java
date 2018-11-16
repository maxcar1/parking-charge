package com.maxcar.base.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
* json工具类
*/
public class JsonTools {
    static ObjectMapper objectMapper;

    private static ObjectMapper getObjectMapper() {
        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
            //设置不识别的字段过滤掉不报错
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    /**
     * 对象转json
     * @param obj 对象
     * @return String
     */
    public static String toJson(Object obj) {
        if (null == obj) return "";
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (Exception ex) {
            throw new RuntimeException("json序列化失败", ex);
        }
    }

    /**
     * json转对象
     * @param jsonString json
     * @param type Class
     * @param <T> Class
     * @return T
     */
    public static <T> T toObj(String jsonString, Class<T> type) {
        try {
            return getObjectMapper().readValue(jsonString, type);
        } catch (Exception ex) {
            throw new RuntimeException("json反序列化失败", ex);
        }
    }

    /**
     * 带泛型的json转对象
     * @param jsonStr json串
     * @param type 泛型类
     * @param elementClasses 泛型指定类
     * @param <T>
     * @return
     */
    public static <T> T toObjWithGeneric(String jsonStr, Class<T> type, Class<?>... elementClasses){
    	try {
    		JavaType javaType = getObjectMapper().getTypeFactory().constructParametricType(type, elementClasses);//constructParametrizedType(type,type,elementClasses);
    		return getObjectMapper().readValue(jsonStr, javaType);
		} catch (Exception ex) {
            throw new RuntimeException("带泛型json反序列化失败", ex);
		}
    }

    /**
     * json转Map
     * @param jsonString json
     * @return Map
     */
    public static Map<String, Object> jsonToMap(String jsonString) {
        try {
            return getObjectMapper().readValue(jsonString, new TypeReference<Map<String, Object>>(){});
        } catch (Exception ex) {
            throw new RuntimeException("json转map失败", ex);
        }
    }

}
