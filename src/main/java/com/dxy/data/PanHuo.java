package com.dxy.data;

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
        private String f0;

        @ExcelProperty(value = "全网价格（持续更新）")
        @ColumnWidth(15)
        private String f1;

        @ExcelProperty(value = "成本价")
        @ColumnWidth(15)
        private String f2;

        @ExcelProperty(value = "产品图")
        @ColumnWidth(15)
        private String f3;

        @ExcelProperty(value = "男女分类")
        @ColumnWidth(15)
        private String f4;

        @ExcelProperty(value = "属性分类")
        @ColumnWidth(15)
        private String f5;

        @ExcelProperty(value = "供应商")
        @ColumnWidth(15)
        private String f6;

        @ExcelProperty(value = "细分属性分类")
        @ColumnWidth(15)
        private String f7;

        @ExcelProperty(value = "季节分类")
        @ColumnWidth(15)
        private String f8;

        @ExcelProperty(value = "新款/动销款/清仓款")
        @ColumnWidth(15)
        private String f9;
}
