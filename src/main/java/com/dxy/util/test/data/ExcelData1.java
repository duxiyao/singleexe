package com.dxy.util.test.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExcelData1 extends BaseRowModel {
    @ExcelProperty(value = "erp编码", index = 0)
    @ColumnWidth(15)
    private String id;
    @ExcelProperty(value = "全网价格（持续更新）", index = 1)
    @ColumnWidth(25)
    private String name;
    @ExcelProperty(value = "成本价", index = 2)
    @ColumnWidth(15)
    private String price;
}
