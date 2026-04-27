package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多多店铺商品资料 - 输入模型 (a.xlsx)
 * 用于exe7，仅读取聚水潭资源位信息表所需的5个字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DDSP extends BaseRowModel {

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "百亿基础价")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "资源位")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "新客立减(元)")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "是否预售")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "价格类型")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "1件实收最低场景 商家预估实收价格")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "商家出资常规优惠额度")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "商家出资常规优惠")
    @ColumnWidth(15)
    private String f7;
}
