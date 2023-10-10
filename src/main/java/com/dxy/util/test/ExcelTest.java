package com.dxy.util.test;

import com.dxy.util.ExcelUtil;
import com.dxy.util.test.data.ExcelData;

import java.util.ArrayList;
import java.util.List;

public class ExcelTest {
    public static void main(String[] args) {

        String filePath = "D:/tmp/盘货表xlsx.xlsx";
        List<Object> datas = ExcelUtil.readMoreThan1000Row(filePath);
        String s = "";

        List<ExcelData> ds = new ArrayList<>();
        ds.add(create("1","1","1"));
        ds.add(create("2","2","2"));
        ds.add(create("3","3","3"));

        filePath = "D:/tmp/ExcelTest.xlsx";
        ExcelUtil.writeWithTemplate(filePath, ds);
    }

    static ExcelData create(String id, String name, String price) {
        ExcelData d = new ExcelData();
        d.setId(id);
        d.setName(name);
        d.setPrice(price);
        return d;
    }
}
