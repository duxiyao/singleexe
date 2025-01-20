package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PriceManager {

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "SKUID")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "1件实收最低场景 商家预估实收价格")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "2件实收最低场景 商家预估实收价格")
    @ColumnWidth(15)
    private String f3;
}

