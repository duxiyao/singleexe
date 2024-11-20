package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class YHQ extends BaseRowModel {

    @ExcelProperty(value = "优惠券名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "优惠券类型")
    @ColumnWidth(15)
    private String f1;


    @ExcelProperty(value = "优惠券对象")
    @ColumnWidth(15)
    private String f2;


    @ExcelProperty(value = "优惠券对象ID")
    @ColumnWidth(15)
    private String f3;


    @ExcelProperty(value = "面额/折扣")
    @ColumnWidth(15)
    private String f4;


    @ExcelProperty(value = "领取条件")
    @ColumnWidth(15)
    private String f5;


    @ExcelProperty(value = "领取时间")
    @ColumnWidth(15)
    private String f6;


    @ExcelProperty(value = "使用时间")
    @ColumnWidth(15)
    private String f7;


    @ExcelProperty(value = "用券渠道")
    @ColumnWidth(15)
    private String f8;


    @ExcelProperty(value = "每人限领")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "发行量")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "活动状态")
    @ColumnWidth(15)
    private String f11;

    @ExcelProperty(value = "已经领取张数")
    @ColumnWidth(15)
    private String f12;

    @ExcelProperty(value = "剩余张数")
    @ColumnWidth(15)
    private String f13;

}
