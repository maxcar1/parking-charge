package com.maxcar.base.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	public static int getNAppearPosition(String source, String regex, Integer n) {
		if (isEmpty(source) || isEmpty(regex) || n == null || n < 1) {
			return -1;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		matcher.matches();
		int start = 0;
		for (int i = 0; i < n; i++) {
			boolean b = matcher.find();
			start = matcher.start();
		}
		return start;
	}
	
	public static Boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		String string = str.trim();
		if ("".equals(string) || "null".equals(string)) {
			return true;
		}
		return false;
	}
	
	public static Boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * json数据驼峰转下划线
	 * @return
	 */
	public static JSONObject camelToUnderline(JSONObject sourceJsonObject, Class... destClassArray) {
		if (sourceJsonObject == null || sourceJsonObject.size() == 0 || destClassArray == null || destClassArray.length == 0) {
			return null;
		}

        JSONObject returnObj = new JSONObject();
        for (Class destClass :
                destClassArray) {
            Field[] fields = destClass.getDeclaredFields();
            if (fields == null || fields.length == 0) {
                return null;
            }
            Map<String, String> map = new HashMap<>(fields.length << 1);
            for (Field field :
                    fields) {
                String fieldName = field.getName();
                if (isEmpty(fieldName)) {
                    logger.error("fieldName of {} shuold not be empty, {}", destClass, fieldName);
                    return null;
                }
                String upperCase = fieldName.replace("_", "").toUpperCase();
                processDuplicateField(map, fieldName, upperCase);
            }

            Iterator it = sourceJsonObject.keys();
            // 遍历符合当前对象的属性值
            while (it.hasNext()) {
                String key = (String) it.next();
                if (isEmpty(key)) {
                    logger.error("key of JsonObject shuold not be empty, {}", key);
                    return null;
                }
                if ("picList".equals(key)) {
                    returnObj.element("picList", sourceJsonObject.getJSONArray("picList"));
                    continue;
                }
                String upperCaseKey = key.replace("_", "").toUpperCase();
                processDuplicateField(sourceJsonObject, map, returnObj, key, upperCaseKey);
            }
        }
		returnObj.putAll(sourceJsonObject);
		return returnObj;
	}

    private static void processDuplicateField(JSONObject sourceJsonObject, Map<String, String> map, JSONObject returnObj, String key, String upperCaseKey) {
        int i = 1;
        String upperCaseKeyi = upperCaseKey + i;
        while (map.containsKey(upperCaseKeyi)) {
            String fieldName = map.get(upperCaseKeyi);
            returnObj.put(fieldName, sourceJsonObject.get(key));
            i++;
            upperCaseKeyi = upperCaseKey + i;
        }
    }

    private static void processDuplicateField(Map<String, String> map, String fieldName, String upperCase) {
        int i = 1;
        String key = upperCase + i;
        while (map.containsKey(key)) {
            i++;
            key = upperCase + i;
        }
        map.put(key, fieldName);
    }
}
