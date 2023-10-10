package com.dxy.util.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.dxy.util.test.data.ExcelData;
import com.dxy.util.test.data.PanHuo;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;

public class ExcelTest {
    public static void main(String[] args) throws InterruptedException {

//        Executors.newCachedThreadPool()
        parseObj(PanHuo.class);
//        String filePath = "D:/tmp/盘货表xlsx.xlsx";
        String filePath = "E:/盘货表xlsx.xlsx";
//        List<Object> datas = ExcelUtil.readMoreThan1000Row(filePath);
//        String s = "";

        new Thread(() -> {

            EasyExcelFactory.read(new File(filePath), new ReadListener<Map<Integer, String>>() {
                int i = 0;

                @Override
                public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

                    String s = "";
                }

                @Override
                public void invokeHead(Map map, AnalysisContext analysisContext) {

                    String s = "";
                }

                @Override
                public void invoke(Map<Integer, String> o, AnalysisContext analysisContext) {
                    i++;
                    System.out.println("invoke  " + i);
                    String s = "";
                }

                @Override
                public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {

                    String s = "";
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                    String s = "";
                }

                @Override
                public boolean hasNext(AnalysisContext analysisContext) {
                    return true;
                }
            })
                    .doReadAll();
//                    .doReadAllSync();
            System.out.println(Thread.currentThread().getName());

        }).start();
        Thread.sleep(1000);
        System.out.println("end");

//        ExcelUtil.readExcelData(filePath, new ExcelUtil.TExcelListener<Object>() {
//
//            @Override
//            public void invoke(Object t, AnalysisContext analysisContext) {
//
//                String s = "";
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//
//                String s = "";
//            }
//
//            @Override
//            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//
//                String s = "";
//            }
//        });
//
//        List<ExcelData> ds = new ArrayList<>();
//        ds.add(create("1","1","1"));
//        ds.add(create("2","2","2"));
//        ds.add(create("3","3","3"));
//
//        filePath = "D:/tmp/ExcelTest.xlsx";
//        ExcelUtil.writeWithTemplate(filePath, ds);
    }

    private static void parseObj(Class excelData1Class) {
        Field[] fields = excelData1Class.getDeclaredFields();
        for (Field field : fields) {
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            String v = excelProperty.value()[0];
            int i = excelProperty.index();
            String s = "";
        }
    }

    static ExcelData create(String id, String name, String price) {
        ExcelData d = new ExcelData();
        d.setId(id);
        d.setName(name);
        d.setPrice(price);
        return d;
    }
}
