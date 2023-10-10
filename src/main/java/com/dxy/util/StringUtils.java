package com.dxy.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    private static final String SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static SimpleDateFormat F1 = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random RANDOM = new SecureRandom();

    /**
     * 驼峰转下划线,最后转为大写
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }

    /**
     * 下划线转驼峰,正常输出
     *
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将文件中的文本转换成实体对象
     *
     * @param filePath
     * @param cls
     * @param <T>
     * @return
     */
//    public static <T> T txtToObj(String filePath, Class<T> cls) {
//        String base = FileHelper.readFileContent(filePath);
//        T model = new Gson().fromJson(base, cls);
//        return model;
//    }

    /**
     * 根据文件中的json数组转换为对象集合
     *
     * @param filePath
     * @param cls
     * @param <T>
     * @return
     */
//    public static <T> List<T> txtToObjs(String filePath, Class<T> cls) {
//        List<T> rets = new ArrayList<T>();
//        String base = FileHelper.readFileContent(filePath);
//        JSONArray jsonArray = JSONArray.fromObject(base);
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonObject = jsonArray.optJSONObject(i);
//            T model = new Gson().fromJson(jsonObject.toString(), cls);
//            rets.add(model);
//        }
//        return rets;
//    }

    /**
     * 从sql类型转换为java类型
     *
     * @param fieldType
     * @return
     */
    public static String getFieldType(String fieldType, boolean isOtype) {
        String ret = "";
        if (fieldType == null)
            fieldType = "";
        if (fieldType.toLowerCase().contains("varchar")) {
            ret = "String";
            if (isOtype) {
                ret = "VARCHAR";
            }
        }
        if (fieldType.toLowerCase().contains("int")) {
            ret = "Integer";
            if (isOtype) {
                ret = "INTEGER";
            }
        }
        if (fieldType.toLowerCase().contains("datetime") || fieldType.toLowerCase().contains("timestamp")) {
            ret = "Date";
            if (isOtype) {
                ret = "TIMESTAMP";
            }
        }
        return ret;
    }

    /**
     * 判断是否是sql的主键
     *
     * @param s
     * @return
     */
    public static boolean isKeyFlag(String s) {
        return "PRI".equals(s);
    }

    public static String getValue(String s) {
        if (s == null)
            s = "";
        return s;
    }

    public static String getValue(Object s) {
        if (s == null)
            s = "";
        return s.toString();
    }

//    public static Map jsonStr2Map(String json) {
//        Gson gson = new Gson();
//        return gson.fromJson(json, Map.class);
//    }

    public static String generateNonceStr() {
        char[] nonceChars = new char[8];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }

    public static String generateUniCode() {
        String pre = F1.format(new Date());
        return pre + generateNonceStr();
    }

    public static void main(String[] args) {
        System.out.println(generateUniCode());
    }

    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        } else {
            int strLen = str.length();

            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }
}
