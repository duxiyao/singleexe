package com.dxy.util.test;

import com.dxy.util.ExcelUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

public class FTLTest {
    public static void main(String[] args) throws Exception {
        two();
    }

    private static void two() throws IOException, TemplateException {
        String mbftl = "Data1.ftl";
        System.out.println(System.getProperty("user.dir"));
        File ftl = new File(System.getProperty("user.dir"), mbftl);
        System.out.println(ftl.getAbsolutePath());
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(ftl.getParentFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

//        String filePath = "D:\\yyexportimgs\\1\\活动ID和活动类型分解.xlsx";
//        String tabname = "活动ID和活动类型分解";
//        String clsName = "HDIDHDFJ";

//        String filePath = "D:\\yyexportimgs\\1\\多多店铺商品资料.xlsx";
//        String tabname = "多多店铺商品资料";
//        String clsName = "DPSPZL";
        String filePath = "D:\\yyexportimgs\\1\\营销活动-报名记录.xlsx";
        String tabname = "营销活动-报名记录";
        String clsName = "YXHDBMJL";

        Map<String, Object> root = new HashMap<>();
        root.put("clsName", clsName);
        root.put("tabname", tabname);
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        root.put("datas", datas);
        Map<String, Object> f1 = null;

        List<String> tmp = ExcelUtil.readTitles(filePath);
        tmp.add("id");

        List<String> titles = new ArrayList<>(new HashSet<>(tmp));
        if (titles.size() < tmp.size()) {
            System.out.println("=======去重了 " + (tmp.size() - titles.size()));
        }
        for (int i = 0; i < titles.size(); i++) {
            String name = titles.get(i);
            f1 = newMap("title", name);
            f1.put("fn", name);
            datas.add(f1);
        }

        Template temp = cfg.getTemplate(mbftl);
        File output = new File("out");
        output.mkdirs();
        output = new File(output, clsName + ".java");
        Writer out = new OutputStreamWriter(new FileOutputStream(output));
        temp.process(root, out);
        out.flush();
        out.close();
    }

    private static void one() throws IOException, TemplateException {
        String mbftl = "Data.ftl";
        System.out.println(System.getProperty("user.dir"));
        File ftl = new File(System.getProperty("user.dir"), mbftl);
        System.out.println(ftl.getAbsolutePath());
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
//        cfg.setDirectoryForTemplateLoading(new File("/where/you/store/templates"));
        cfg.setDirectoryForTemplateLoading(ftl.getParentFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        String filePath = "D:\\1.xlsx";
        String clsName = "TCBJB";
        Map<String, Object> root = new HashMap<>();
        root.put("clsName", clsName);
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        root.put("datas", datas);
        Map<String, Object> f1 = null;

        List<String> titles = ExcelUtil.readTitles(filePath);

        for (int i = 0; i < titles.size(); i++) {
            String name = titles.get(i);
            f1 = newMap("title", name);
            f1.put("fn", "fn" + i);
            datas.add(f1);
        }


        Template temp = cfg.getTemplate(mbftl);
        File output = new File("out");
        output.mkdirs();
        output = new File(output, clsName + ".java");
        Writer out = new OutputStreamWriter(new FileOutputStream(output));
        temp.process(root, out);
        out.flush();
        out.close();
    }

    private static Map<String, Object> newMap(String k, Object v) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(k, v);
        return map;
    }
}
