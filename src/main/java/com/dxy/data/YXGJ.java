package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class YXGJ extends BaseRowModel {

    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "拼单价(元)")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "活动时间")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "新客立减(元)")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "新客预估到手价(元)")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "活动状态")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "活动备注")
    @ColumnWidth(15)
    private String f7;
}
