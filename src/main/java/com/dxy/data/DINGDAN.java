package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DINGDAN extends BaseRowModel {

    @ExcelProperty(value = "店铺名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "退款状态")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "下单时间")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "线上订单号")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "内部订单号")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "店铺款式编码")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "款号")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "成本价")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "已付金额")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "数量")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "应付金额")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "供应商")
    @ColumnWidth(15)
    private String f11;

    @ExcelProperty(value = "分类")
    @ColumnWidth(15)
    private String f12;

    @ExcelProperty(value = "虚拟分类")
    @ColumnWidth(15)
    private String f13;

    @ExcelProperty(value = "原始线上订单号")
    @ColumnWidth(15)
    private String f14;

    @ExcelProperty(value = "订单类型")
    @ColumnWidth(15)
    private String f15;

    @ExcelProperty(value = "状态")
    @ColumnWidth(15)
    private String f16;

    @ExcelProperty(value = "店铺主账号")
    @ColumnWidth(15)
    private String f17;

    @ExcelProperty(value = "订单来源")
    @ColumnWidth(15)
    private String f18;

    @ExcelProperty(value = "付款日期")
    @ColumnWidth(15)
    private String f19;

    @ExcelProperty(value = "省份")
    @ColumnWidth(15)
    private String f20;

    @ExcelProperty(value = "发货日期")
    @ColumnWidth(15)
    private String f21;

    @ExcelProperty(value = "城市")
    @ColumnWidth(15)
    private String f22;

    @ExcelProperty(value = "运费")
    @ColumnWidth(15)
    private String f23;

    @ExcelProperty(value = "快递单号")
    @ColumnWidth(15)
    private String f24;

    @ExcelProperty(value = "快递公司")
    @ColumnWidth(15)
    private String f25;

    @ExcelProperty(value = "异常类型")
    @ColumnWidth(15)
    private String f26;

    @ExcelProperty(value = "店铺状态")
    @ColumnWidth(15)
    private String f27;

    @ExcelProperty(value = "收货人")
    @ColumnWidth(15)
    private String f28;

    @ExcelProperty(value = "订单备注")
    @ColumnWidth(15)
    private String f29;

    @ExcelProperty(value = "标签")
    @ColumnWidth(15)
    private String f30;

    @ExcelProperty(value = "子订单编号")
    @ColumnWidth(15)
    private String f31;

    @ExcelProperty(value = "发货仓")
    @ColumnWidth(15)
    private String f32;

    @ExcelProperty(value = "计划发货日期")
    @ColumnWidth(15)
    private String f33;

    @ExcelProperty(value = "平台站点")
    @ColumnWidth(15)
    private String f34;

    @ExcelProperty(value = "商品总成交金额")
    @ColumnWidth(15)
    private String f35;

    @ExcelProperty(value = "商品编码")
    @ColumnWidth(15)
    private String f36;

    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f37;

    @ExcelProperty(value = "颜色及规格")
    @ColumnWidth(15)
    private String f38;

    @ExcelProperty(value = "商品单价")
    @ColumnWidth(15)
    private String f39;

    @ExcelProperty(value = "商品金额")
    @ColumnWidth(15)
    private String f40;

    @ExcelProperty(value = "原价")
    @ColumnWidth(15)
    private String f41;

    @ExcelProperty(value = "是否赠品")
    @ColumnWidth(15)
    private String f42;

    @ExcelProperty(value = "子订单状态")
    @ColumnWidth(15)
    private String f43;

    @ExcelProperty(value = "标准商品名")
    @ColumnWidth(15)
    private String f44;

    @ExcelProperty(value = "店铺商品编码")
    @ColumnWidth(15)
    private String f45;

    @ExcelProperty(value = "登记数量")
    @ColumnWidth(15)
    private String f46;

    @ExcelProperty(value = "实退数量")
    @ColumnWidth(15)
    private String f47;
}
