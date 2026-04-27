package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 聚水潭资源位信息表 - 输出模型 (c.xlsx)
 * 用于exe7，输出12列，列顺序严格按照模板要求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JSTZY extends BaseRowModel {

    @ExcelProperty(value = "店铺名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "系统款式编码")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "系统商品编码")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "平台店铺款式编码")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "百亿基础价")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "资源位")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "新客立减")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "是否预售")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "价格类型")
    @ColumnWidth(15)
    private String f7a;

    @ExcelProperty(value = "1件实收最低场景 商家预估实收价格")
    @ColumnWidth(15)
    private String f7a1;

    @ExcelProperty(value = "商家出资常规优惠额度")
    @ColumnWidth(15)
    private String f7b;

    @ExcelProperty(value = "商家出资常规优惠")
    @ColumnWidth(15)
    private String f7c;

    @ExcelProperty(value = "平台店铺商品编码")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "线上款式编码")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "线上商品编码")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "链接同步设置")
    @ColumnWidth(15)
    private String f11;
}
