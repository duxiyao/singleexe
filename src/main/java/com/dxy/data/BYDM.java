package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BYDM extends BaseRowModel {

    @ExcelProperty(value = "活动名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "活动ID")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "类目名称")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "提交时间")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "活动价")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "活动库存")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "活动时间")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "活动状态")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "累计销量")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "库存")
    @ColumnWidth(15)
    private String f11;

    @ExcelProperty(value = "失效原因")
    @ColumnWidth(15)
    private String f12;
}
