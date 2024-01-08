package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BZTG extends BaseRowModel {

    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "商品分组")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "推广名称")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "出价（元）")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "是否已删除")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "推广方案")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "是否保留推广")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "总花费(元)")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "成交花费(元)")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "每笔成交花费(元)")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "成交笔数")
    @ColumnWidth(15)
    private String f11;

    @ExcelProperty(value = "交易额(元)")
    @ColumnWidth(15)
    private String f12;

    @ExcelProperty(value = "实际投产比")
    @ColumnWidth(15)
    private String f13;

    @ExcelProperty(value = "每笔成交金额(元)")
    @ColumnWidth(15)
    private String f14;

    @ExcelProperty(value = "询单花费(元)")
    @ColumnWidth(15)
    private String f15;

    @ExcelProperty(value = "询单量")
    @ColumnWidth(15)
    private String f16;

    @ExcelProperty(value = "平均询单成本(元)")
    @ColumnWidth(15)
    private String f17;

    @ExcelProperty(value = "收藏花费(元)")
    @ColumnWidth(15)
    private String f18;

    @ExcelProperty(value = "收藏量")
    @ColumnWidth(15)
    private String f19;

    @ExcelProperty(value = "平均收藏成本(元)")
    @ColumnWidth(15)
    private String f20;

    @ExcelProperty(value = "关注花费(元)")
    @ColumnWidth(15)
    private String f21;

    @ExcelProperty(value = "关注量")
    @ColumnWidth(15)
    private String f22;

    @ExcelProperty(value = "平均关注成本(元)")
    @ColumnWidth(15)
    private String f23;

    @ExcelProperty(value = "曝光量")
    @ColumnWidth(15)
    private String f24;

    @ExcelProperty(value = "点击量")
    @ColumnWidth(15)
    private String f25;

    @ExcelProperty(value = "直接交易额(元)")
    @ColumnWidth(15)
    private String f26;

    @ExcelProperty(value = "间接交易额(元)")
    @ColumnWidth(15)
    private String f27;

    @ExcelProperty(value = "直接成交笔数")
    @ColumnWidth(15)
    private String f28;

    @ExcelProperty(value = "间接成交笔数")
    @ColumnWidth(15)
    private String f29;
}
