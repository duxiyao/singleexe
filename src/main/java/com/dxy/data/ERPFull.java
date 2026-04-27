package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ERP店铺商品资料 - 输入模型 (b.xlsx)
 * 用于exe7，读取聚水潭资源位信息表所需的8个字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ERPFull extends BaseRowModel {

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

    @ExcelProperty(value = "平台店铺商品编码")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "线上款式编码")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "线上商品编码")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "链接同步设置")
    @ColumnWidth(15)
    private String f7;
}
