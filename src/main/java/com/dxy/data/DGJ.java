package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class DGJ {
        /**
        *   活动id
        */
        @ExcelProperty(value = "活动id")
        @ColumnWidth(15)
        private String f0;
        /**
        *   商品id
        */
        @ExcelProperty(value = "商品id")
        @ColumnWidth(15)
        private String f1;
        /**
        *   sku id
        */
        @ExcelProperty(value = "sku id")
        @ColumnWidth(15)
        private String f2;
        /**
        *   建议单件提报价(元)
        */
        @ExcelProperty(value = "建议单件提报价(元)")
        @ColumnWidth(15)
        private String f3;
        /**
        *   降价后多件折扣
        */
        @ExcelProperty(value = "降价后多件折扣")
        @ColumnWidth(15)
        private String f4;
        /**
        *   建议搭配首件立减（元）
        */
        @ExcelProperty(value = "建议搭配首件立减（元）")
        @ColumnWidth(15)
        private String f5;
        /**
        *   活动名称(用于参考，无需填写）
        */
        @ExcelProperty(value = "活动名称(用于参考，无需填写）")
        @ColumnWidth(15)
        private String f6;
        /**
        *   商品名称(用于参考，无需填写）
        */
        @ExcelProperty(value = "商品名称(用于参考，无需填写）")
        @ColumnWidth(15)
        private String f7;
        /**
        *   sku名称(用于参考，无需填写）
        */
        @ExcelProperty(value = "sku名称(用于参考，无需填写）")
        @ColumnWidth(15)
        private String f8;
        /**
        *   当前单件提报价(元)(用于参考，无需填写）
        */
        @ExcelProperty(value = "当前单件提报价(元)(用于参考，无需填写）")
        @ColumnWidth(15)
        private String f9;
        /**
        *   当前多件折扣(用于参考，无需填写）
        */
        @ExcelProperty(value = "当前多件折扣(用于参考，无需填写）")
        @ColumnWidth(15)
        private String f10;
        /**
        *   当前首件立减（元）(用于参考，无需填写）
        */
        @ExcelProperty(value = "当前首件立减（元）(用于参考，无需填写）")
        @ColumnWidth(15)
        private String f11;
        /**
        *   改价原因
        */
        @ExcelProperty(value = "改价原因")
        @ColumnWidth(15)
        private String f12;
}
