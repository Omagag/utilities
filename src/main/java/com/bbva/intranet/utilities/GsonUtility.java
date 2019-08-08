package com.bbva.intranet.utilities;

import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;

public abstract class GsonUtility {

	private static final Logger logger = LoggerFactory.getLogger(GsonUtility.class);

//    public static void jsonResponse(Object data, HttpServletResponse res) throws IOException {
    public static void jsonResponse(Object data, HttpServletResponse res) {
		Gson gson = new Gson();

		// Se convierte a JSON en Map para manipularlo desde el JS
		String jsonData = gson.toJson(data);
		logger.info(jsonData);

		res.setCharacterEncoding("ISO-8859-1");
		res.setContentType("application/json;charset=ISO-8859-1");

        try {
            PrintWriter out;
            out = res.getWriter();
            out.println(jsonData);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String objectToJson(Object obj) {
        return objectToJson(obj, null);
    }

    public static String objectToJson(Object obj, String dateFormat) {
        String json;
        logger.info("Tiempo n");
        Gson gson;
        if (dateFormat == null) {
            gson = new GsonBuilder().create();
        } else {
            gson = new GsonBuilder().setDateFormat(dateFormat).create();
        }
        json = gson.toJson(obj);

        logger.info(json);

        return json;
    }

    public static Object jsonToObject(String json, String dateFormat, Class clazz) {
        Object obj;
        Gson gson;
        if (dateFormat == null) {
            gson = new GsonBuilder().create();
        } else {
            gson = new GsonBuilder().setDateFormat(dateFormat).create();
        }
        try {

            logger.info(json);
            obj = gson.fromJson(json, clazz);
//        } catch (IllegalStateException e) {
        } catch (JsonSyntaxException e) {
            JsonParser jsonParser = new JsonParser();
//            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            JsonElement jsonElement = jsonParser.parse(json).getAsJsonObject();
//            logger.info(String.format("Json repaired [%s]", jsonObject.getAsString()));
            logger.info(String.format("Json repaired [%s]", jsonElement.getAsString()));
//            obj = gson.fromJson(jsonObject.getAsString(), clazz);
            obj = gson.fromJson(jsonElement.getAsString(), clazz);
        }
        return obj;
    }

//    public static Object jsonElementObjectToObject(String json, String dateFormat, @SuppressWarnings("rawtypes") Class type, String jsonObject) {
//        Object obj;
//
//        Gson gson;
//        if (dateFormat == null) {
//            gson = new GsonBuilder().create();
//        } else {
//            gson = new GsonBuilder().setDateFormat(dateFormat).create();
//        }
//        JsonElement jsonElements = gson.fromJson(json, JsonElement.class);
//
//        if (jsonObject != null && !jsonObject.isEmpty()) {
//            JsonElement jsonElement = jsonElements.getAsJsonObject().getAsJsonObject(jsonObject);
//            logger.info(String.format("json: %s", jsonElement.toString()));
//            obj = gson.fromJson(jsonElement, type);
//        } else {
//            logger.info(String.format("json: %s", jsonElements.toString()));
//            obj = gson.fromJson(jsonElements.toString(), type);
//        }
//
//        return obj;
//    }

    public static Object jsonElementObjectToObject(String json, String dateFormat, @SuppressWarnings("rawtypes") Class clazz, String jsonObject) {
        Object obj;

        Gson gson;
        if (dateFormat == null) {
            gson = new GsonBuilder().create();
        } else {
            gson = new GsonBuilder().setDateFormat(dateFormat).create();
        }
        JsonElement jsonElements = gson.fromJson(json, JsonElement.class);
        logger.info(String.format("original json: %s", jsonElements.toString()));
        if (jsonObject != null && !jsonObject.equals("")) {
            JsonElement jsonElement = jsonElements.getAsJsonObject().get(jsonObject);
            logger.info(String.format("json: %s", jsonElement.toString()));
            obj = gson.fromJson(jsonElement, clazz);
        } else {
            logger.info(String.format("json: %s", jsonElements.toString()));
            obj = gson.fromJson(jsonElements.toString(), clazz);
        }

        return obj;
    }

    public static Object jsonElementObjectToObject(String json, String dateFormat, @SuppressWarnings("rawtypes") Type type, String jsonObject) {
        Object obj;

        Gson gson;
        if (dateFormat == null) {
            gson = new GsonBuilder().create();
        } else {
            gson = new GsonBuilder().setDateFormat(dateFormat).create();
        }
        JsonElement jsonElements = gson.fromJson(json, JsonElement.class);
        logger.info(String.format("original json: %s", jsonElements.toString()));
        if (jsonObject != null && !jsonObject.equals("")) {
            JsonElement jsonElement = jsonElements.getAsJsonObject().get(jsonObject);
            logger.info(String.format("json: %s", jsonElement.toString()));
            obj = gson.fromJson(jsonElement, type);
        } else {
            logger.info(String.format("json: %s", jsonElements.toString()));
            obj = gson.fromJson(jsonElements.toString(), type);
        }

        return obj;
    }
}