package com.dxy.util.test;

import com.dxy.util.ExcelUtil;
import com.dxy.util.test.data.PanHuo;

import java.util.*;

public class ExcelTest1 {
    public static void main(String[] args) {

//        String filePath = "D:/tmp/盘货表xlsx.xlsx";
        String filePath = "E:/盘货表xlsx.xlsx";
        List<PanHuo> data1s = ExcelUtil.read(filePath, PanHuo.class);
        String s = "";

        filePath = "E:/ExcelTest1.xlsx";
        ExcelUtil.writeWithTemplate(filePath, data1s);
    }


}
