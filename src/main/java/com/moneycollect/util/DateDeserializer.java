package com.moneycollect.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateDeserializer extends StdDeserializer {

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class vc) {
        super(vc);
    }

    public final static String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public final static DateFormat UTC_FORMAT = new SimpleDateFormat(UTC_PATTERN);
    public final static String UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public final static DateFormat UTC_MS_FORMAT = new SimpleDateFormat(UTC_MS_PATTERN);
    public final static String UTC_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public final static DateFormat UTC_WITH_ZONE_OFFSET_FORMAT = new SimpleDateFormat(UTC_WITH_ZONE_OFFSET_PATTERN);
    public final static String UTC_MS_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public final static DateFormat UTC_MS_WITH_ZONE_OFFSET_FORMAT = new SimpleDateFormat(UTC_MS_WITH_ZONE_OFFSET_PATTERN);
    public final static String UTC_MS_WITH_ZONE_OFFSET_PATTERN_1 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public final static DateFormat UTC_MS_WITH_ZONE_OFFSET_FORMAT_1 = new SimpleDateFormat(UTC_MS_WITH_ZONE_OFFSET_PATTERN_1);

    public static Date parse(String strDateValue, DateFormat format) {
        String value = strDateValue;
        if (value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value.trim())) {
            return null;
        }
        if (value.indexOf('%') >= 0) {
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {}
        }
        try {
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            return format.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("No format fit for date String " + value);
        }
    }
    /**
     * <ol>
     * <li>yyyy-MM-dd'T'HH:mm:ss'Z'</li>
     * <li>yyyy-MM-dd'T'HH:mm:ss.SSS'Z'</li>
     * <li>yyyy-MM-dd'T'HH:mm:ssZ</li>
     * <li>yyyy-MM-dd'T'HH:mm:ss.SSSZ</li>
     * </ol>
     */
    public static Date parseUTC(String utcString) {
        if (utcString == null) {
            return null;
        }
        int length = utcString.length();
        if (utcString.indexOf('Z') > -1) {
            if (length == UTC_PATTERN.length() - 4) {
                return parse(utcString, UTC_FORMAT);
            } else if (length == UTC_MS_PATTERN.length() - 4) {
                return parse(utcString, UTC_MS_FORMAT);
            }
        } else {
            if (length == UTC_WITH_ZONE_OFFSET_PATTERN.length() + 2 || length == UTC_WITH_ZONE_OFFSET_PATTERN.length() + 3) {
                // 2018-09-13T05:34:31+0800 or 2018-09-13T05:34:31+08:00
                return parse(utcString, UTC_WITH_ZONE_OFFSET_FORMAT);
            } else if (length == UTC_MS_WITH_ZONE_OFFSET_PATTERN.length() + 2 ) {
                // 2018-09-13T05:34:31.999+0800
                return parse(utcString, UTC_MS_WITH_ZONE_OFFSET_FORMAT);
            }else if( length == UTC_MS_WITH_ZONE_OFFSET_PATTERN.length() + 3){
                //2018-09-13T05:34:31.999+08:00
                return parse(utcString, UTC_MS_WITH_ZONE_OFFSET_FORMAT_1);
            }
        }
        // 没有更多匹配的时间格式
        throw new RuntimeException("No format fit for date String "+utcString);
    }


    /**
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser,
     *      com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return parseUTC(p.getValueAsString());
    }

}
