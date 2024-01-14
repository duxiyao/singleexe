package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

//标准推广-关键词
@EqualsAndHashCode
@Data
public class BztgGjc extends BaseRowModel {

    @ExcelProperty(value = "关键词")
    @ColumnWidth(25)
    private String f0;

    @ExcelProperty(value = "搜索热度")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "上升幅度")
    @ColumnWidth(25)
    private String f2;

    @ExcelProperty(value = "竞争强度")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "点击率")
    @ColumnWidth(25)
    private String f4;

    @ExcelProperty(value = "市场平均出价")
    @ColumnWidth(25)
    private String f5;

    @ExcelProperty(value = "建议出价")
    @ColumnWidth(25)
    private String f6;

}
