package com.dxy.util;

import com.dxy.anotations.Column;
import com.dxy.anotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableGenerator {

    public static String generateCreateTableSQL(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class is not annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name().isEmpty() ?
                clazz.getSimpleName().toLowerCase() : tableAnnotation.name();

        List<String> columnDefinitions = new ArrayList<>();
        List<String> primaryKeys = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name().isEmpty() ?
                        field.getName() : columnAnnotation.name();

                StringBuilder columnDefinition = new StringBuilder();
                columnDefinition.append(columnName).append(" ").append(columnAnnotation.type());

                if (!columnAnnotation.nullable()) {
                    columnDefinition.append(" NOT NULL");
                }

                if (columnAnnotation.autoIncrement()) {
                    columnDefinition.append(" AUTO_INCREMENT");
                }

                columnDefinitions.add(columnDefinition.toString());

                if (columnAnnotation.primaryKey()) {
                    primaryKeys.add(columnName);
                }
            }
        }

        if (!primaryKeys.isEmpty()) {
            columnDefinitions.add("PRIMARY KEY (" + String.join(", ", primaryKeys) + ")");
        }

        return "CREATE TABLE IF NOT EXISTS " + tableName + " (\n" +
                String.join(",\n", columnDefinitions) +
                "\n);";
    }
}