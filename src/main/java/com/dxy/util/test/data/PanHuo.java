package com.dxy.util.test.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PanHuo extends BaseRowModel {
    @ExcelProperty(value = "erp编码")
    @ColumnWidth(15)
    private String erpId;
    @ExcelProperty(value = "全网价格（持续更新）")
    @ColumnWidth(25)
    private String netPrice;
    @ExcelProperty(value = "成本价")
    @ColumnWidth(15)
    private String cost;
    @ExcelProperty(value = "产品图")
    @ColumnWidth(15)
    private String img;
    @ExcelProperty(value = "男女分类")
    @ColumnWidth(15)
    private String c;
    @ExcelProperty(value = "属性分类")
    @ColumnWidth(15)
    private String pc;
    @ExcelProperty(value = "供应商")
    @ColumnWidth(15)
    private String supply;
    @ExcelProperty(value = "细分属性分类")
    @ColumnWidth(15)
    private String xc;
    @ExcelProperty(value = "季节分类")
    @ColumnWidth(15)
    private String jc;
    @ExcelProperty(value = "新款/动销款/清仓款")
    @ColumnWidth(15)
    private String xk;
}
