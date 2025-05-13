package com.dxy.util;

import com.alibaba.excel.annotation.ExcelProperty;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EasyExcelUtil {

    public static Map<Integer, Class<?>> getFieldTypeMap(Class<?> clazz) {
        Map<Integer, Class<?>> map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                map.put(i, field.getType());
            }
        }
        return map;
    }
}