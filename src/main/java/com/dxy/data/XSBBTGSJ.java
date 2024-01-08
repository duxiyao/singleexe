package com.dxy.data;

//销售报表输出加上推广数据模板
//全站总花费(元)	全站实际投产比	全站成交笔数	全站每笔成交花费(元)	全站每笔成交金额(元)	全站直接成交笔数	全站间接成交笔数	标准总花费(元)	标准实际投产比	标准成交笔数	标准每笔成交花费(元)	标准直接成交笔数	标准间接成交笔数	标准每笔成交金额(元)

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import com.dxy.util.TypeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class XSBBTGSJ extends BaseRowModel {


    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f0;

    @ExcelProperty(value = "商品编码")
    @ColumnWidth(15)
    private String f1;

    @ExcelProperty(value = "商品链接")
    @ColumnWidth(15)
    private String f2;

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f3;

    @ExcelProperty(value = "库存")
    @ColumnWidth(15)
    private String f4;

    @ExcelProperty(value = "累计销量")
    @ColumnWidth(15)
    private String f5;

    @ExcelProperty(value = "30日销量")
    @ColumnWidth(15)
    private String f6;

    @ExcelProperty(value = "资源位")
    @ColumnWidth(15)
    private String f7;

    @ExcelProperty(value = "活动价")
    @ColumnWidth(15)
    private String f8;

    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f9;

    @ExcelProperty(value = "店铺名称")
    @ColumnWidth(15)
    private String f10;

    @ExcelProperty(value = "erp编码")
    @ColumnWidth(15)
    private String f11;

    @ExcelProperty(value = "图片")
    @ColumnWidth(15)
    private String f12;

    @ExcelProperty(value = "销售数量")
    @ColumnWidth(15)
    private String f13;

    @ExcelProperty(value = "销售金额")
    @ColumnWidth(15)
    private String f14;

    @ExcelProperty(value = "总成本")
    @ColumnWidth(15)
    private String f15;

    @ExcelProperty(value = "总毛利")
    @ColumnWidth(15)
    private String f16;

    @ExcelProperty(value = "单双毛利")
    @ColumnWidth(15)
    private String f17;

    @ExcelProperty(value = "客单价")
    @ColumnWidth(15)
    private String f18;

    @ExcelProperty(value = "毛利率")
    @ColumnWidth(15)
    private String f19;

    @ExcelProperty(value = "客单价-活动价")
    @ColumnWidth(15)
    private String f20;

    @ExcelProperty(value = "全站总花费(元)")
    @ColumnWidth(15)
    private String q;
    @ExcelProperty(value = "全站实际投产比")
    @ColumnWidth(15)
    private String r;
    @ExcelProperty(value = "全站成交笔数")
    @ColumnWidth(15)
    private String s;
    @ExcelProperty(value = "全站每笔成交花费(元)")
    @ColumnWidth(15)
    private String t;
    @ExcelProperty(value = "全站每笔成交金额(元)")
    @ColumnWidth(15)
    private String u;
    @ExcelProperty(value = "全站直接成交笔数")
    @ColumnWidth(15)
    private String v;
    @ExcelProperty(value = "全站间接成交笔数")
    @ColumnWidth(15)
    private String w;
    @ExcelProperty(value = "标准总花费(元)")
    @ColumnWidth(15)
    private String x;
    @ExcelProperty(value = "标准实际投产比")
    @ColumnWidth(15)
    private String y;
    @ExcelProperty(value = "标准成交笔数")
    @ColumnWidth(15)
    private String z;
    @ExcelProperty(value = "标准每笔成交花费(元)")
    @ColumnWidth(15)
    private String aa;
    @ExcelProperty(value = "标准直接成交笔数")
    @ColumnWidth(15)
    private String ab;
    @ExcelProperty(value = "标准间接成交笔数")
    @ColumnWidth(15)
    private String ac;
    @ExcelProperty(value = "标准每笔成交金额(元)")
    @ColumnWidth(15)
    private String ad;

    @ExcelProperty(value = "成本")
    @ColumnWidth(15)
    private String f21;

    @ExcelProperty(value = "全网价格（持续更新）")
    @ColumnWidth(15)
    private String f22;

    @ExcelProperty(value = "百亿资源位失效")
    @ColumnWidth(15)
    private String f23;

    @ExcelProperty(value = "断码资源位失效")
    @ColumnWidth(15)
    @ExcelIgnore
    private String f24;

    @ExcelProperty(value = "百亿断码最大值")
    @ColumnWidth(15)
    @ExcelIgnore
    private String f25;

    @ExcelProperty(value = "百亿基础价")
    @ColumnWidth(15)
    private String f26;

    @ExcelProperty(value = "断码基础价")
    @ColumnWidth(15)
    @ExcelIgnore
    private String f27;

    @ExcelProperty(value = "细分属性")
    @ColumnWidth(15)
    private String f28;

    @ExcelProperty(value = "属性分类")
    @ColumnWidth(15)
    private String f29;

    @ExcelProperty(value = "男女分类")
    @ColumnWidth(15)
    private String f30;

    @ExcelProperty(value = "新款/动销款/清仓款")
    @ColumnWidth(15)
    private String f31;

    @ExcelProperty(value = "季节")
    @ColumnWidth(15)
    private String f32;

    @ExcelProperty(value = "供应商")
    @ColumnWidth(15)
    private String f33;

    @ExcelProperty(value = "新客立减(元)")
    @ColumnWidth(15)
    private String f34;

    @ExcelProperty(value = "类目名称")
    @ColumnWidth(15)
    private String f35;

    @ExcelProperty(value = "类目全称")
    @ColumnWidth(15)
    private String f36;

    @ExcelProperty(value = "拼团价")
    @ColumnWidth(15)
    private String f37;

    @ExcelProperty(value = "单买价")
    @ColumnWidth(15)
    private String f38;

    @ExcelProperty(value = "参考价")
    @ColumnWidth(15)
    private String f39;

    @ExcelProperty(value = "收藏数")
    @ColumnWidth(15)
    private String f40;

    @ExcelProperty(value = "创建时间")
    @ColumnWidth(15)
    private String f41;

    @ExcelProperty(value = "商品状态")
    @ColumnWidth(15)
    private String f42;

    @ExcelProperty(value = "满两件折扣")
    @ColumnWidth(15)
    private String f43;

    public double getDoubleF14() {
        return TypeUtil.parseDouble(getF14());
    }
}
