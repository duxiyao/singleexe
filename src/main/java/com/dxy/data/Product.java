package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Product extends BaseRowModel {

    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "商品图片")
    @ColumnWidth(15)
    private String f19;

    @ExcelProperty(value = "店铺名称")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "商品编码")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "商品链接")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "资源位")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "类目名称")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "类目全称")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "活动价")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "拼团价")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "单买价")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "参考价")
    @ColumnWidth(15)
    private String f11;

    @ExcelProperty(value = "收藏数")
    @ColumnWidth(15)
    private String f12;

    @ExcelProperty(value = "库存")
    @ColumnWidth(15)
    private String f13;

    @ExcelProperty(value = "累计销量")
    @ColumnWidth(15)
    private String f14;

    @ExcelProperty(value = "30日销量")
    @ColumnWidth(15)
    private String f15;

    @ExcelProperty(value = "创建时间")
    @ColumnWidth(15)
    private String f16;

    @ExcelProperty(value = "商品状态")
    @ColumnWidth(15)
    private String f17;

    @ExcelProperty(value = "满两件折扣")
    @ColumnWidth(15)
    private String f18;

    @ExcelProperty(value = "是否预售")
    @ColumnWidth(15)
    private String f20;
}
