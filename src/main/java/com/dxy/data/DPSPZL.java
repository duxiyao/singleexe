package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dxy.anotations.Column;
import com.dxy.anotations.Table;
import com.alibaba.excel.annotation.ExcelIgnore;

@EqualsAndHashCode
@Data
@Table(name = "多多店铺商品资料") // 指定表名
public class DPSPZL {
        /**
        *   是否预售
        */
        @ExcelProperty(value = "是否预售")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 是否预售;
        /**
        *   30日销量
        */
        @ExcelProperty(value = "30日销量")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 30日销量;
        /**
        *   图片
        */
        @ExcelProperty(value = "图片")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 图片;
        /**
        *   类目全称
        */
        @ExcelProperty(value = "类目全称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 类目全称;
        /**
        *   满两件折扣
        */
        @ExcelProperty(value = "满两件折扣")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 满两件折扣;
        /**
        *   类目名称
        */
        @ExcelProperty(value = "类目名称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 类目名称;
        /**
        *   商品状态
        */
        @ExcelProperty(value = "商品状态")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品状态;
        /**
        *   属性分类
        */
        @ExcelProperty(value = "属性分类")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 属性分类;
        /**
        *   成本
        */
        @ExcelProperty(value = "成本")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 成本;
        /**
        *   惊喜券
        */
        @ExcelProperty(value = "惊喜券")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 惊喜券;
        /**
        *   2件实收最低场景 商家预估实收价格
        */
        @ExcelProperty(value = "2件实收最低场景 商家预估实收价格")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 2件实收最低场景 商家预估实收价格;
        /**
        *   商品ID
        */
        @ExcelProperty(value = "商品ID")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品ID;
        /**
        *   erp编码
        */
        @ExcelProperty(value = "erp编码")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String erp编码;
        /**
        *   id
        */
        @ExcelProperty(value = "id")
        @ColumnWidth(15)
        @Column(type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        private String id;
        /**
        *   参考价
        */
        @ExcelProperty(value = "参考价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 参考价;
        /**
        *   资源位
        */
        @ExcelProperty(value = "资源位")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 资源位;
        /**
        *   单买价
        */
        @ExcelProperty(value = "单买价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 单买价;
        /**
        *   百亿资源位失效
        */
        @ExcelProperty(value = "百亿资源位失效")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 百亿资源位失效;
        /**
        *   全网价格（持续更新）
        */
        @ExcelProperty(value = "全网价格（持续更新）")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 全网价格（持续更新）;
        /**
        *   累计销量
        */
        @ExcelProperty(value = "累计销量")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 累计销量;
        /**
        *   细分属性
        */
        @ExcelProperty(value = "细分属性")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 细分属性;
        /**
        *   新款/动销款/清仓款
        */
        @ExcelProperty(value = "新款/动销款/清仓款")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 新款/动销款/清仓款;
        /**
        *   新客立减(元)
        */
        @ExcelProperty(value = "新客立减(元)")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 新客立减(元);
        /**
        *   库存
        */
        @ExcelProperty(value = "库存")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 库存;
        /**
        *   活动价
        */
        @ExcelProperty(value = "活动价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动价;
        /**
        *   价格类型
        */
        @ExcelProperty(value = "价格类型")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 价格类型;
        /**
        *   拼团价
        */
        @ExcelProperty(value = "拼团价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 拼团价;
        /**
        *   商品链接
        */
        @ExcelProperty(value = "商品链接")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品链接;
        /**
        *   商品名称
        */
        @ExcelProperty(value = "商品名称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品名称;
        /**
        *   男女分类
        */
        @ExcelProperty(value = "男女分类")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 男女分类;
        /**
        *   季节
        */
        @ExcelProperty(value = "季节")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 季节;
        /**
        *   24年款式店铺定位
        */
        @ExcelProperty(value = "24年款式店铺定位")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 24年款式店铺定位;
        /**
        *   活动ID
        */
        @ExcelProperty(value = "活动ID")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动ID;
        /**
        *   商品编码
        */
        @ExcelProperty(value = "商品编码")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品编码;
        /**
        *   1件实收最低场景 商家预估实收价格
        */
        @ExcelProperty(value = "1件实收最低场景 商家预估实收价格")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 1件实收最低场景 商家预估实收价格;
        /**
        *   百亿基础价
        */
        @ExcelProperty(value = "百亿基础价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 百亿基础价;
        /**
        *   创建时间
        */
        @ExcelProperty(value = "创建时间")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 创建时间;
        /**
        *   商家出资常规优惠额度
        */
        @ExcelProperty(value = "商家出资常规优惠额度")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商家出资常规优惠额度;
        /**
        *   供应商
        */
        @ExcelProperty(value = "供应商")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 供应商;
        /**
        *   收藏数
        */
        @ExcelProperty(value = "收藏数")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 收藏数;
        /**
        *   券前价
        */
        @ExcelProperty(value = "券前价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 券前价;
        /**
        *   商家出资常规优惠
        */
        @ExcelProperty(value = "商家出资常规优惠")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商家出资常规优惠;
        /**
        *   店铺名称
        */
        @ExcelProperty(value = "店铺名称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 店铺名称;
        /**
        *   商品立减券
        */
        @ExcelProperty(value = "商品立减券")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品立减券;
}
