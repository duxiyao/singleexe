package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode
@Data
public class TCBJB {
    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    @ColumnWidth(15)
    private String f0;
    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f1;
    /**
     * 店铺名称
     */
    @ExcelProperty(value = "店铺名称")
    @ColumnWidth(15)
    private String f2;
    /**
     * 商品编码
     */
    @ExcelProperty(value = "商品编码")
    @ColumnWidth(15)
    private String f3;
    /**
     * 商品链接
     */
    @ExcelProperty(value = "商品链接")
    @ColumnWidth(15)
    private String f4;
    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    @ColumnWidth(15)
    private String f5;
    /**
     * 库存
     */
    @ExcelProperty(value = "库存")
    @ColumnWidth(15)
    @NumberFormat("#")
    private Integer f6;
    /**
     * 累计销量
     */
    @ExcelProperty(value = "累计销量")
    @ColumnWidth(15)
    @NumberFormat("#")
    private Integer f7;
    /**
     * 30日销量
     */
    @ExcelProperty(value = "30日销量")
    @ColumnWidth(15)
    @NumberFormat("#")
    private Integer f8;
    /**
     * erp编码
     */
    @ExcelProperty(value = "erp编码")
    @ColumnWidth(15)
    private String f9;
    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    @ColumnWidth(15)
    private String f10;
    /**
     * 成本
     */
    @ExcelProperty(value = "成本")
    @ColumnWidth(15)
    private BigDecimal f11;
    /**
     * 新客立减(元)
     */
    @ExcelProperty(value = "新客立减(元)")
    @ColumnWidth(15)
    private String f12;
    /**
     * 惊喜券
     */
    @ExcelProperty(value = "惊喜券")
    @ColumnWidth(15)
    private String f13;
    /**
     * 商品立减券
     */
    @ExcelProperty(value = "商品立减券")
    @ColumnWidth(15)
    private String f14;

    /**
     * 首单立减
     */
    @ExcelProperty(value = "建议搭配首件立减（元）")
    @ColumnWidth(15)
    private String f24;
    /**
     * 改价建议
     */
    @ExcelProperty(value = "改价建议")
    @ColumnWidth(15)
    private String f25;
    /**
     * 下午资源位
     */
    @ExcelProperty(value = "下午资源位")
    @ColumnWidth(15)
    private String f26;
    /**
     * 下午活动价
     */
    @ExcelProperty(value = "下午活动价")
    @ColumnWidth(15)
    private String f27;
    /**
     * 有无百亿标
     */
    @ExcelProperty(value = "有无百亿标")
    @ColumnWidth(15)
    private String f28;
    /**
     * 当前单件提报价(元)
     */
    @ExcelProperty(value = "当前单件提报价(元)")
    @ColumnWidth(15)
    private String f29;
    /**
     * 当前多件折扣
     */
    @ExcelProperty(value = "当前多件折扣")
    @ColumnWidth(15)
    private String f30;
    /**
     * 建议单件提报价(元)
     */
    @ExcelProperty(value = "建议单件提报价(元)")
    @ColumnWidth(15)
    private String f31;
    /**
     * 降价后多件折扣
     */
    @ExcelProperty(value = "降价后多件折扣")
    @ColumnWidth(15)
    private String f32;
    /**
     * 单件差异
     */
    @ExcelProperty(value = "单件差异")
    @ColumnWidth(15)
    private String f33;
    /**
     * 折扣差异
     */
    @ExcelProperty(value = "折扣差异")
    @ColumnWidth(15)
    private String f34;
    /**
     * 建议单件提报价(元)毛利
     */
    @ExcelProperty(value = "建议单件提报价(元)毛利")
    @ColumnWidth(15)
    private String f35;
    /**
     * 建议单件提报价(元)毛利率
     */
    @ExcelProperty(value = "建议单件提报价(元)毛利率")
    @ColumnWidth(15)
    private String f36;
    /**
     * 降价后多件折扣价
     */
    @ExcelProperty(value = "降价后多件折扣价")
    @ColumnWidth(15)
    private String f37;
    /**
     * 降价后多件折扣价毛利
     */
    @ExcelProperty(value = "降价后多件折扣价毛利")
    @ColumnWidth(15)
    private String f38;
    /**
     * 降价后多件折扣价毛利率
     */
    @ExcelProperty(value = "降价后多件折扣价毛利率")
    @ColumnWidth(15)
    private String f39;
    /**
     * 活动价-百亿基础价
     */
    @ExcelProperty(value = "活动价-百亿基础价")
    @ColumnWidth(15)
    private String f40;
    /**
     * 全网价格（持续更新）
     */
    @ExcelProperty(value = "全网价格（持续更新）")
    @ColumnWidth(15)
    private String f41;
    /**
     * 百亿资源位失效
     */
    @ExcelProperty(value = "百亿资源位失效")
    @ColumnWidth(15)
    private String f42;
    /**
     * 百亿基础价
     */
    @ExcelProperty(value = "百亿基础价")
    @ColumnWidth(15)
    private String f43;
    /**
     * 细分属性
     */
    @ExcelProperty(value = "细分属性")
    @ColumnWidth(15)
    private String f44;
    /**
     * 属性分类
     */
    @ExcelProperty(value = "属性分类")
    @ColumnWidth(15)
    private String f45;
    /**
     * 男女分类
     */
    @ExcelProperty(value = "男女分类")
    @ColumnWidth(15)
    private String f46;
    /**
     * 新款/动销款/清仓款
     */
    @ExcelProperty(value = "新款/动销款/清仓款")
    @ColumnWidth(15)
    private String f47;
    /**
     * 季节
     */
    @ExcelProperty(value = "季节")
    @ColumnWidth(15)
    private String f48;
    /**
     * 供应商
     */
    @ExcelProperty(value = "供应商")
    @ColumnWidth(15)
    private String f49;
    /**
     * 24年款式店铺定位
     */
    @ExcelProperty(value = "24年款式店铺定位")
    @ColumnWidth(15)
    private String f50;
    /**
     * 满两件折扣
     */
    @ExcelProperty(value = "满两件折扣")
    @ColumnWidth(15)
    private String f51;
    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    @ColumnWidth(15)
    private String f52;
    /**
     * 类目名称
     */
    @ExcelProperty(value = "类目名称")
    @ColumnWidth(15)
    private String f53;
    /**
     * 类目全称
     */
    @ExcelProperty(value = "类目全称")
    @ColumnWidth(15)
    private String f54;
    /**
     * 拼团价
     */
    @ExcelProperty(value = "拼团价")
    @ColumnWidth(15)
    private String f55;
    /**
     * 单买价
     */
    @ExcelProperty(value = "单买价")
    @ColumnWidth(15)
    private String f56;
    /**
     * 参考价
     */
    @ExcelProperty(value = "参考价")
    @ColumnWidth(15)
    private String f57;
    /**
     * 收藏数
     */
    @ExcelProperty(value = "收藏数")
    @ColumnWidth(15)
    private String f58;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    @ColumnWidth(15)
    private String f59;
    /**
     * 商品状态
     */
    @ExcelProperty(value = "商品状态")
    @ColumnWidth(15)
    private String f60;
}
