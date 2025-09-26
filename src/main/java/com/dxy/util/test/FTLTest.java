package com.dxy.util.test;

import com.dxy.util.ExcelUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

//        String filePath = "D:\\doc\\xwechat_files\\q15837125112_dcca\\msg\\file\\2025-09\\9.24号给批量测试数据\\活动ID和活动类型分解.xlsx";
//        String tabname = "活动ID和活动类型分解";
//        String clsName = "HDIDHDFJ";

//        String filePath = "D:\\doc\\xwechat_files\\q15837125112_dcca\\msg\\file\\2025-09\\9.24号给批量测试数据\\25年09月24号多多店铺商品资料.xlsx";
//        String tabname = "多多店铺商品资料";
//        String clsName = "DPSPZL";

//        String filePath = "D:\\doc\\xwechat_files\\q15837125112_dcca\\msg\\file\\2025-09\\9.24号给批量测试数据\\营销活动-报名记录-强人满汾专卖店_20250924091247.xlsx";
//        String tabname = "营销活动-报名记录";
//        String clsName = "YXHDBMJL";

//        String tabname = "数据输出";
//        String clsName = "RETSJSC";
//        String ss="商品名称 ,商品ID ,店铺名称 ,商品编码 ,商品链接 ,商品ID1 ,库存 ,累计销量, _30日销量 ,资源位, 活动价 ,ERP编码 ,图片, 成本, 全网价格_持续更新_ ,百亿资源位失效, 百亿基础价 ,新客立减_元_ ," +
//                "是否有百亿促销,百亿基础价提报时间,百亿满减专区,百亿自主降价自主降价,百亿长期216折扣活动名字,百亿长期216折扣活动力度,_226品牌限时折扣名称,_226品牌限时折扣力度,_226品牌限时折扣活动价,百亿秒杀,月卡专享价7月," +
//                "细分属性, 属性分类, 男女分类 ,新款_动销款_清仓款, 季节 ,供应商 ,_24年款式店铺定位 ,满两件折扣 ,活动ID ,惊喜券 ,商品立减券 ,类目名称 ,类目全称 ,拼团价 ,单买价, 参考价 ,收藏数 ,创建时间 ,商品状态, _1件实收最低场景商家预估实收价格, _2件实收最低场景商家预估实收价格, 券前价, 价格类型 ,商家出资常规优惠额度, 商家出资常规优惠 ,是否预售";

        String tabname = "分解数据";
        String clsName = "FJSJ";
        String ss="商品名称 ,商品ID ,商品ID ,活动ID ,活动ID,活动大类 ,活动名称 ,商品图片 ,类目名称 ,提交时间 ,活动价 ,活动库存 ,活动时间 ,活动状态 ,累计销量 ,库存,失效原因 ";


        Map<String, Object> root = new HashMap<>();
        root.put("clsName", clsName);
        root.put("tabname", tabname);

        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        root.put("datas", datas);
        Map<String, Object> f1 = null;

//        List<String> titles = ExcelUtil.readTitles(filePath);
        List<String> titles = Arrays.stream(ss.split(",")).collect(Collectors.toList());

        titles.add("id");

        for (int i = 0; i < titles.size(); i++) {
            String name = titles.get(i);
            f1 = newMap("title", name);
            name = name.replace(" ","")
                    .replace("（","_")
                    .replace("(","_")
                    .replace("）","_")
                    .replace(")","_")
                    .replace("/","_");
            if (startsWithDigit(name))
                name = "_" + name;
            f1.put("fn", name);
            f1.put("tn", name);
            f1.put("vtn", "#{"+name+"}");
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

    private static boolean startsWithDigit(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char firstChar = str.charAt(0);
        return Character.isDigit(firstChar);
    }
}
