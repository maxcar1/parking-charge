package com.maxcar.web.aop;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @program: maxcar-util
 * @description: 反射获取市场集合
 * @author: 罗顺锋
 * @create: 2018-05-23 17:19
 **/
public class MarketOption {
    public static void main(String[] args) throws Exception {
        Class<?> apply = Class.forName("com.maxcar.statistics.pojo.params.CommonParams");
        Field[] fields = apply.getFields();
        //获取所有属性
        List allFieldList = getBomFields1(new ArrayList(), fields);
        for (Object list : allFieldList) {
            List list1 = (List) list;
            Field field = (Field) list1.get(0);
            System.out.println(field.get(new ArrayList<>()));
        }
    }

    public static List getBomFields1(List chain,
                                     Field[] fields) {
        List result = new ArrayList();
        for (Field field : fields) {
            Class<?> fieldClass = field.getType();
            if (fieldClass.isPrimitive()
                    || fieldClass.getName().startsWith("java.lang")
                    || fieldClass.getName().startsWith("java.util.Date")
                    || fieldClass.getName().startsWith("javax")
                    || fieldClass.getName().startsWith("com.sun")
                    || fieldClass.getName().startsWith("sun")
                    || fieldClass.getName().startsWith("boolean")
                    || fieldClass.getName().startsWith("double")
                    || fieldClass.getName().startsWith("int")) {
                List endChain = new ArrayList(chain);
                endChain.add(field);
                result.add(endChain);
                continue;
            } else {
                if (fieldClass.isAssignableFrom(List.class)) // 【2】
                {
                    Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
                    if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
                    {
                        ParameterizedType pt = (ParameterizedType) fc;
                        Class genericClazz = (Class) pt.getActualTypeArguments()[0]; // 【4】
//                        if (genericClazz.getName().startsWith("java.lang") //设置list的终止类型
//                                || genericClazz.getName().startsWith("java.util.Date")
//                                || genericClazz.getName().startsWith("javax")
//                                || genericClazz.getName().startsWith("com.sun")
//                                || genericClazz.getName().startsWith("sun")
//                                || genericClazz.getName().startsWith("boolean")
//                                || genericClazz.getName().startsWith("double")
//                                || genericClazz.getName().startsWith("int")) {
//                            continue;
//                        }
                        //System.out.println(genericClazz);
                        // 得到泛型里的class类型对象。
                        List thisChain = new ArrayList(chain);
//                      System.out.println(chain);
                        thisChain.add(field); //!!
                        result.addAll(getBomFields1(new ArrayList(thisChain), genericClazz.getDeclaredFields()));
                    }
                } else {
                    List thisChain = new ArrayList(chain);
                    thisChain.add(field);
                    result.addAll(getBomFields1(new ArrayList(thisChain),
                            fieldClass.getDeclaredFields()));
                }
            }
        }
        return result;
    }

    protected static List getBomFields(List chain, Field[] fields) {
        List result = new ArrayList();
        for (Field field : fields) {
            Class<?> fieldClass = field.getType();
            if (fieldClass.getName().startsWith("java")
                    || fieldClass.getName().startsWith("javax")
                    || fieldClass.getName().startsWith("com.sun")
                    || fieldClass.getName().startsWith("boolean")
                    || fieldClass.getName().startsWith("double")
                    || fieldClass.getName().startsWith("int")) {
                List endChain = new ArrayList(chain);
                endChain.add(field);
                result.add(endChain);
                continue;
            } else {
                List thisChain = new ArrayList(chain);
                thisChain.add(field);
                result.addAll(getBomFields(new java.util.ArrayList(
                        thisChain), fieldClass.getDeclaredFields()));
            }
        }
        return result;
    }
}
