package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ERP extends BaseRowModel {

    @ExcelProperty(value = "图片")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "款式主图")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "店铺编号")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "店铺名称")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "站点名称")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "平台店铺款式编码")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "平台店铺商品编码")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "系统款式编码")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "系统商品编码")
    @ColumnWidth(15)
    private String f8;
}
