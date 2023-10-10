package com.dxy.util.test;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.dxy.util.ExcelUtil;
import com.dxy.util.test.data.ExcelData;
import com.dxy.util.test.data.ExcelData1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelTest {
    public static void main(String[] args) {

        String filePath = "D:/tmp/盘货表xlsx.xlsx";
//        List<Object> datas = ExcelUtil.readMoreThan1000Row(filePath);
//        String s = "";

        new Thread(() -> {

        EasyExcelFactory.read(new File(filePath), new ReadListener<Object>() {
            int i=0;
            @Override
            public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

                String s = "";
            }

            @Override
            public void invokeHead(Map map, AnalysisContext analysisContext) {

                String s = "";
            }

            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                i++;
                System.out.println("invoke  "+i);
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
//                .doReadAll();
                .doReadAllSync();

        }).start();
        System.out.println("end");

//        ExcelUtil.readExcelData(filePath, new ExcelUtil.TExcelListener<ExcelData1>() {
//
//            @Override
//            public void invoke(ExcelData1 t, AnalysisContext analysisContext) {
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

    static ExcelData create(String id, String name, String price) {
        ExcelData d = new ExcelData();
        d.setId(id);
        d.setName(name);
        d.setPrice(price);
        return d;
    }
}
