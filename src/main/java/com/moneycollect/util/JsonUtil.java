package com.moneycollect.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JsonUtil {

    /** UTC Time：yyyy-MM-dd'T'HH:mm:ss.SSS'Z' */
    public final static String UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final ObjectMapper objectMapper = init();

    private static ObjectMapper init() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(new SimpleDateFormat(UTC_MS_PATTERN));
        SimpleModule module = new SimpleModule();
        module.addDeserializer(java.util.Date.class, new DateDeserializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    public static String toJson(Object object)  {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String json , Class<T> clazz) {

        if (json == null) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toJavaObject(Object json , Class<T> clazz){
        //json maybe is a list
        if (json instanceof List) {
            Map map = new HashMap();
            map.put("data", json);
            json=map;
        }
        return  objectMapper.convertValue(json, clazz);
    }

    public static Map<String, Object> toMap(Object object){
        return  objectMapper.convertValue(object, Map.class);
    }
}
