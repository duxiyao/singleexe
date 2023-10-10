package com.dxy.util.test.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcelData extends BaseRowModel {
    @ExcelProperty(value = "ID", index = 0)
    @ColumnWidth(15)
    private String id;
    @ExcelProperty(value = "名称", index = 1)
    @ColumnWidth(25)
    private String name;
    @ExcelProperty(value = "price", index = 2)
    @ColumnWidth(15)
    private String price;
}
