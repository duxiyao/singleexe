package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class A  extends BaseRowModel {

    @ExcelProperty(value = "a")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "b")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "c")
    @ColumnWidth(15)
    private String f2;
}
