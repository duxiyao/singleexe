package com.dxy.util.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dxy.data.MData;
import com.dxy.data.YXGJ;
import com.dxy.util.ExcelUtil;
import com.dxy.data.PanHuo;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelTest1 {
    public static void main(String[] args) {

//        String filePath = "D:/tmp/盘货表xlsx.xlsx";
//        String filePath = "E:/盘货表xlsx.xlsx";
//        List<PanHuo> data1s = ExcelUtil.read(filePath, PanHuo.class);
//        String s = "";
//
//        filePath = "E:/ExcelTest1.xlsx";
//        ExcelUtil.writeWithTemplate(filePath, data1s);


//        List<MData> ds = new ArrayList<>();
//        MData m = new MData();
//        ds.add(m);
//        String filePath = "E:/ExcelTest1.xlsx";
//        ExcelUtil.writeWithTemplate(filePath, ds);

//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy年MM月dd号");
//        System.out.println(simpleDateFormat.format(new Date()));

        List<YXGJ> list = ExcelUtil.read("E:\\workspace\\营销工具-新客立减-强人满汾专卖店-2023-10-09.xlsx", YXGJ.class);
        String s = "";
    }


}
