package com.bbva.intranet.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class ObjectConvert {

    private static final Logger logger = LoggerFactory.getLogger(ObjectConvert.class);

    public static Map<String, Object> converterObjectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        logger.info("En converterObjectToMap se recibe: "+obj);

        for (Field field : obj.getClass().getDeclaredFields()) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object object = field.get(obj);
            if(object == null) {
                logger.info(String.format("Field %s didn't store because is null.", field.getName()));
            } else {
                map.put(field.getName(), object);
            }
            field.setAccessible(accessible);
        }
        return map;
    }

//    public static Object assignMapValuesIntoObjectAndSpecificField(String fieldKey, String documentId, Map<String, Object> data, Object obj) {
    public static void assignMapValuesIntoObjectAndSpecificField(String fieldKey, String documentId, Map<String, Object> data, Object obj) {

        try {
            ObjectConvert.assignMapValuesIntoObject(obj, data);

            Field declaredField =  null;
            try {
//                declaredField = obj.getClass().getDeclaredField("id");
                declaredField = obj.getClass().getDeclaredField(fieldKey);
                boolean accessible = declaredField.isAccessible();
                declaredField.setAccessible(true);
                declaredField.set(obj, documentId);
                declaredField.setAccessible(accessible);

//            Method method = obj.getClass().getMethod("getId", null);
//
//            method.invoke(null);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        }

//        return obj;
    }

//    public static Object assignMapValuesIntoObject(Object obj, Map<String, Object> data) throws IllegalAccessException {
    public static void assignMapValuesIntoObject(Object obj, Map<String, Object> data) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();

            if (data.containsKey(fieldName)) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                field.set(obj, data.get(fieldName));
                field.setAccessible(accessible);
            } else {
                logger.warn(String.format("There is a key with name: [%s] which is not assigned.", fieldName));
            }
        }

//        return obj;
    }

//    public static Object assignValueToDeclaredFieldIntoObject(Object obj, String field, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
    public static void assignValueToDeclaredFieldIntoObject(Object obj, String field, Object fieldValue) throws NoSuchFieldException, IllegalAccessException {
        logger.info(String.format("key: %s, value: %s, object: %s",field, fieldValue, obj));

        Field declaredField;
        declaredField = obj.getClass().getDeclaredField(field);

        boolean accessible = declaredField.isAccessible();
        declaredField.setAccessible(true);
        declaredField.set(obj, fieldValue);
        declaredField.setAccessible(accessible);

//        return obj;
    }

}
