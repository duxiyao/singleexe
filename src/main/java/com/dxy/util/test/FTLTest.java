//package com.dxy.util.test;
//
//import com.dxy.util.ExcelUtil;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateExceptionHandler;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class FTLTest {
//    public static void main(String[] args) throws Exception {
//
//        System.out.println(System.getProperty("user.dir"));
//        File ftl = new File(System.getProperty("user.dir"), "Data.ftl");
//        System.out.println(ftl.getAbsolutePath());
//        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
////        cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
//        cfg.setDirectoryForTemplateLoading(ftl.getParentFile());
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//
//        String filePath = "E:\\workspace\\营销工具-新客立减-强人旗舰店-2023-10-09.xlsx";
//        String clsName = "YXGJ";
//        Map<String, Object> root = new HashMap<>();
//        root.put("clsName", clsName);
//        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
//        root.put("datas", datas);
//        Map<String, Object> f1 = null;
//
////        List<String> titles = new ArrayList<>();
////        titles.add("");
////        titles.add("");
//        List<String> titles = ExcelUtil.readTitles(filePath);
//
//        for (int i = 0; i < titles.size(); i++) {
//            f1 = newMap("title", titles.get(i));
//            f1.put("fn", "f" + i);
//            datas.add(f1);
//        }
//
//
//        Template temp = cfg.getTemplate("Data.ftl");
//        File output = new File("out");
//        output.mkdirs();
//        output = new File(output, clsName + ".java");
//        Writer out = new OutputStreamWriter(new FileOutputStream(output));
//        temp.process(root, out);
//        out.flush();
//        out.close();
//    }
//
//    private static Map<String, Object> newMap(String k, Object v) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put(k, v);
//        return map;
//    }
//}
