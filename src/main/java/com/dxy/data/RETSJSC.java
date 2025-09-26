package com.dxy.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dxy.anotations.Column;
import com.dxy.anotations.Table;
import com.alibaba.excel.annotation.ExcelIgnore;

/*
INSERT INTO 数据输出(`商品名称`,`商品ID`,`店铺名称`,`商品编码`,`商品链接`,`商品ID1`,`库存`,`累计销量`,`_30日销量`,`资源位`,`活动价`,`ERP编码`,`图片`,`成本`,`全网价格_持续更新_`,`百亿资源位失效`,`百亿基础价`,`新客立减_元_`,`是否有百亿促销`,`百亿基础价提报时间`,`百亿满减专区`,`百亿自主降价自主降价`,`百亿长期216折扣活动名字`,`百亿长期216折扣活动力度`,`_226品牌限时折扣名称`,`_226品牌限时折扣力度`,`_226品牌限时折扣活动价`,`百亿秒杀`,`月卡专享价7月`,`细分属性`,`属性分类`,`男女分类`,`新款_动销款_清仓款`,`季节`,`供应商`,`_24年款式店铺定位`,`满两件折扣`,`活动ID`,`惊喜券`,`商品立减券`,`类目名称`,`类目全称`,`拼团价`,`单买价`,`参考价`,`收藏数`,`创建时间`,`商品状态`,`_1件实收最低场景商家预估实收价格`,`_2件实收最低场景商家预估实收价格`,`券前价`,`价格类型`,`商家出资常规优惠额度`,`商家出资常规优惠`,`是否预售`,`id`)VALUES(#{商品名称} ,#{商品ID} ,#{店铺名称} ,#{商品编码} ,#{商品链接} ,#{商品ID1} ,#{库存} ,#{累计销量} ,#{_30日销量} ,#{资源位} ,#{活动价} ,#{ERP编码} ,#{图片} ,#{成本} ,#{全网价格_持续更新_} ,#{百亿资源位失效} ,#{百亿基础价} ,#{新客立减_元_} ,#{是否有百亿促销} ,#{百亿基础价提报时间} ,#{百亿满减专区} ,#{百亿自主降价自主降价} ,#{百亿长期216折扣活动名字} ,#{百亿长期216折扣活动力度} ,#{_226品牌限时折扣名称} ,#{_226品牌限时折扣力度} ,#{_226品牌限时折扣活动价} ,#{百亿秒杀} ,#{月卡专享价7月} ,#{细分属性} ,#{属性分类} ,#{男女分类} ,#{新款_动销款_清仓款} ,#{季节} ,#{供应商} ,#{_24年款式店铺定位} ,#{满两件折扣} ,#{活动ID} ,#{惊喜券} ,#{商品立减券} ,#{类目名称} ,#{类目全称} ,#{拼团价} ,#{单买价} ,#{参考价} ,#{收藏数} ,#{创建时间} ,#{商品状态} ,#{_1件实收最低场景商家预估实收价格} ,#{_2件实收最低场景商家预估实收价格} ,#{券前价} ,#{价格类型} ,#{商家出资常规优惠额度} ,#{商家出资常规优惠} ,#{是否预售} ,#{id} )
*/
@EqualsAndHashCode
@Data
@Table(name = "数据输出") // 指定表名
public class RETSJSC {
        /**
        *   商品名称 
        */
        @ExcelProperty(value = "商品名称 ")
        @ColumnWidth(15)
        @Column(name = "商品名称",type = "VARCHAR(100)")
        private String 商品名称;
        /**
        *   商品ID 
        */
        @ExcelProperty(value = "商品ID ")
        @ColumnWidth(15)
        @Column(name = "商品ID",type = "VARCHAR(100)")
        private String 商品ID;
        /**
        *   店铺名称 
        */
        @ExcelProperty(value = "店铺名称 ")
        @ColumnWidth(15)
        @Column(name = "店铺名称",type = "VARCHAR(100)")
        private String 店铺名称;
        /**
        *   商品编码 
        */
        @ExcelProperty(value = "商品编码 ")
        @ColumnWidth(15)
        @Column(name = "商品编码",type = "VARCHAR(100)")
        private String 商品编码;
        /**
        *   商品链接 
        */
        @ExcelProperty(value = "商品链接 ")
        @ColumnWidth(15)
        @Column(name = "商品链接",type = "VARCHAR(100)")
        private String 商品链接;
        /**
        *   商品ID1 
        */
        @ExcelProperty(value = "商品ID1 ")
        @ColumnWidth(15)
        @Column(name = "商品ID1",type = "VARCHAR(100)")
        private String 商品ID1;
        /**
        *   库存 
        */
        @ExcelProperty(value = "库存 ")
        @ColumnWidth(15)
        @Column(name = "库存",type = "VARCHAR(100)")
        private String 库存;
        /**
        *   累计销量
        */
        @ExcelProperty(value = "累计销量")
        @ColumnWidth(15)
        @Column(name = "累计销量",type = "VARCHAR(100)")
        private String 累计销量;
        /**
        *    _30日销量 
        */
        @ExcelProperty(value = " _30日销量 ")
        @ColumnWidth(15)
        @Column(name = "_30日销量",type = "VARCHAR(100)")
        private String _30日销量;
        /**
        *   资源位
        */
        @ExcelProperty(value = "资源位")
        @ColumnWidth(15)
        @Column(name = "资源位",type = "VARCHAR(100)")
        private String 资源位;
        /**
        *    活动价 
        */
        @ExcelProperty(value = " 活动价 ")
        @ColumnWidth(15)
        @Column(name = "活动价",type = "VARCHAR(100)")
        private String 活动价;
        /**
        *   ERP编码 
        */
        @ExcelProperty(value = "ERP编码 ")
        @ColumnWidth(15)
        @Column(name = "ERP编码",type = "VARCHAR(100)")
        private String ERP编码;
        /**
        *   图片
        */
        @ExcelProperty(value = "图片")
        @ColumnWidth(15)
        @Column(name = "图片",type = "VARCHAR(100)")
        private String 图片;
        /**
        *    成本
        */
        @ExcelProperty(value = " 成本")
        @ColumnWidth(15)
        @Column(name = "成本",type = "VARCHAR(100)")
        private String 成本;
        /**
        *    全网价格_持续更新_ 
        */
        @ExcelProperty(value = " 全网价格_持续更新_ ")
        @ColumnWidth(15)
        @Column(name = "全网价格_持续更新_",type = "VARCHAR(100)")
        private String 全网价格_持续更新_;
        /**
        *   百亿资源位失效
        */
        @ExcelProperty(value = "百亿资源位失效")
        @ColumnWidth(15)
        @Column(name = "百亿资源位失效",type = "VARCHAR(100)")
        private String 百亿资源位失效;
        /**
        *    百亿基础价 
        */
        @ExcelProperty(value = " 百亿基础价 ")
        @ColumnWidth(15)
        @Column(name = "百亿基础价",type = "VARCHAR(100)")
        private String 百亿基础价;
        /**
        *   新客立减_元_ 
        */
        @ExcelProperty(value = "新客立减_元_ ")
        @ColumnWidth(15)
        @Column(name = "新客立减_元_",type = "VARCHAR(100)")
        private String 新客立减_元_;
        /**
        *   是否有百亿促销
        */
        @ExcelProperty(value = "是否有百亿促销")
        @ColumnWidth(15)
        @Column(name = "是否有百亿促销",type = "VARCHAR(100)")
        private String 是否有百亿促销;
        /**
        *   百亿基础价提报时间
        */
        @ExcelProperty(value = "百亿基础价提报时间")
        @ColumnWidth(15)
        @Column(name = "百亿基础价提报时间",type = "VARCHAR(100)")
        private String 百亿基础价提报时间;
        /**
        *   百亿满减专区
        */
        @ExcelProperty(value = "百亿满减专区")
        @ColumnWidth(15)
        @Column(name = "百亿满减专区",type = "VARCHAR(100)")
        private String 百亿满减专区;
        /**
        *   百亿自主降价自主降价
        */
        @ExcelProperty(value = "百亿自主降价自主降价")
        @ColumnWidth(15)
        @Column(name = "百亿自主降价自主降价",type = "VARCHAR(100)")
        private String 百亿自主降价自主降价;
        /**
        *   百亿长期216折扣活动名字
        */
        @ExcelProperty(value = "百亿长期216折扣活动名字")
        @ColumnWidth(15)
        @Column(name = "百亿长期216折扣活动名字",type = "VARCHAR(100)")
        private String 百亿长期216折扣活动名字;
        /**
        *   百亿长期216折扣活动力度
        */
        @ExcelProperty(value = "百亿长期216折扣活动力度")
        @ColumnWidth(15)
        @Column(name = "百亿长期216折扣活动力度",type = "VARCHAR(100)")
        private String 百亿长期216折扣活动力度;
        /**
        *   _226品牌限时折扣名称
        */
        @ExcelProperty(value = "_226品牌限时折扣名称")
        @ColumnWidth(15)
        @Column(name = "_226品牌限时折扣名称",type = "VARCHAR(100)")
        private String _226品牌限时折扣名称;
        /**
        *   _226品牌限时折扣力度
        */
        @ExcelProperty(value = "_226品牌限时折扣力度")
        @ColumnWidth(15)
        @Column(name = "_226品牌限时折扣力度",type = "VARCHAR(100)")
        private String _226品牌限时折扣力度;
        /**
        *   _226品牌限时折扣活动价
        */
        @ExcelProperty(value = "_226品牌限时折扣活动价")
        @ColumnWidth(15)
        @Column(name = "_226品牌限时折扣活动价",type = "VARCHAR(100)")
        private String _226品牌限时折扣活动价;
        /**
        *   百亿秒杀
        */
        @ExcelProperty(value = "百亿秒杀")
        @ColumnWidth(15)
        @Column(name = "百亿秒杀",type = "VARCHAR(100)")
        private String 百亿秒杀;
        /**
        *   月卡专享价7月
        */
        @ExcelProperty(value = "月卡专享价7月")
        @ColumnWidth(15)
        @Column(name = "月卡专享价7月",type = "VARCHAR(100)")
        private String 月卡专享价7月;
        /**
        *   细分属性
        */
        @ExcelProperty(value = "细分属性")
        @ColumnWidth(15)
        @Column(name = "细分属性",type = "VARCHAR(100)")
        private String 细分属性;
        /**
        *    属性分类
        */
        @ExcelProperty(value = " 属性分类")
        @ColumnWidth(15)
        @Column(name = "属性分类",type = "VARCHAR(100)")
        private String 属性分类;
        /**
        *    男女分类 
        */
        @ExcelProperty(value = " 男女分类 ")
        @ColumnWidth(15)
        @Column(name = "男女分类",type = "VARCHAR(100)")
        private String 男女分类;
        /**
        *   新款_动销款_清仓款
        */
        @ExcelProperty(value = "新款_动销款_清仓款")
        @ColumnWidth(15)
        @Column(name = "新款_动销款_清仓款",type = "VARCHAR(100)")
        private String 新款_动销款_清仓款;
        /**
        *    季节 
        */
        @ExcelProperty(value = " 季节 ")
        @ColumnWidth(15)
        @Column(name = "季节",type = "VARCHAR(100)")
        private String 季节;
        /**
        *   供应商 
        */
        @ExcelProperty(value = "供应商 ")
        @ColumnWidth(15)
        @Column(name = "供应商",type = "VARCHAR(100)")
        private String 供应商;
        /**
        *   _24年款式店铺定位 
        */
        @ExcelProperty(value = "_24年款式店铺定位 ")
        @ColumnWidth(15)
        @Column(name = "_24年款式店铺定位",type = "VARCHAR(100)")
        private String _24年款式店铺定位;
        /**
        *   满两件折扣 
        */
        @ExcelProperty(value = "满两件折扣 ")
        @ColumnWidth(15)
        @Column(name = "满两件折扣",type = "VARCHAR(100)")
        private String 满两件折扣;
        /**
        *   活动ID 
        */
        @ExcelProperty(value = "活动ID ")
        @ColumnWidth(15)
        @Column(name = "活动ID",type = "VARCHAR(100)")
        private String 活动ID;
        /**
        *   惊喜券 
        */
        @ExcelProperty(value = "惊喜券 ")
        @ColumnWidth(15)
        @Column(name = "惊喜券",type = "VARCHAR(100)")
        private String 惊喜券;
        /**
        *   商品立减券 
        */
        @ExcelProperty(value = "商品立减券 ")
        @ColumnWidth(15)
        @Column(name = "商品立减券",type = "VARCHAR(100)")
        private String 商品立减券;
        /**
        *   类目名称 
        */
        @ExcelProperty(value = "类目名称 ")
        @ColumnWidth(15)
        @Column(name = "类目名称",type = "VARCHAR(100)")
        private String 类目名称;
        /**
        *   类目全称 
        */
        @ExcelProperty(value = "类目全称 ")
        @ColumnWidth(15)
        @Column(name = "类目全称",type = "VARCHAR(100)")
        private String 类目全称;
        /**
        *   拼团价 
        */
        @ExcelProperty(value = "拼团价 ")
        @ColumnWidth(15)
        @Column(name = "拼团价",type = "VARCHAR(100)")
        private String 拼团价;
        /**
        *   单买价
        */
        @ExcelProperty(value = "单买价")
        @ColumnWidth(15)
        @Column(name = "单买价",type = "VARCHAR(100)")
        private String 单买价;
        /**
        *    参考价 
        */
        @ExcelProperty(value = " 参考价 ")
        @ColumnWidth(15)
        @Column(name = "参考价",type = "VARCHAR(100)")
        private String 参考价;
        /**
        *   收藏数 
        */
        @ExcelProperty(value = "收藏数 ")
        @ColumnWidth(15)
        @Column(name = "收藏数",type = "VARCHAR(100)")
        private String 收藏数;
        /**
        *   创建时间 
        */
        @ExcelProperty(value = "创建时间 ")
        @ColumnWidth(15)
        @Column(name = "创建时间",type = "VARCHAR(100)")
        private String 创建时间;
        /**
        *   商品状态
        */
        @ExcelProperty(value = "商品状态")
        @ColumnWidth(15)
        @Column(name = "商品状态",type = "VARCHAR(100)")
        private String 商品状态;
        /**
        *    _1件实收最低场景商家预估实收价格
        */
        @ExcelProperty(value = " _1件实收最低场景商家预估实收价格")
        @ColumnWidth(15)
        @Column(name = "_1件实收最低场景商家预估实收价格",type = "VARCHAR(100)")
        private String _1件实收最低场景商家预估实收价格;
        /**
        *    _2件实收最低场景商家预估实收价格
        */
        @ExcelProperty(value = " _2件实收最低场景商家预估实收价格")
        @ColumnWidth(15)
        @Column(name = "_2件实收最低场景商家预估实收价格",type = "VARCHAR(100)")
        private String _2件实收最低场景商家预估实收价格;
        /**
        *    券前价
        */
        @ExcelProperty(value = " 券前价")
        @ColumnWidth(15)
        @Column(name = "券前价",type = "VARCHAR(100)")
        private String 券前价;
        /**
        *    价格类型 
        */
        @ExcelProperty(value = " 价格类型 ")
        @ColumnWidth(15)
        @Column(name = "价格类型",type = "VARCHAR(100)")
        private String 价格类型;
        /**
        *   商家出资常规优惠额度
        */
        @ExcelProperty(value = "商家出资常规优惠额度")
        @ColumnWidth(15)
        @Column(name = "商家出资常规优惠额度",type = "VARCHAR(100)")
        private String 商家出资常规优惠额度;
        /**
        *    商家出资常规优惠 
        */
        @ExcelProperty(value = " 商家出资常规优惠 ")
        @ColumnWidth(15)
        @Column(name = "商家出资常规优惠",type = "VARCHAR(100)")
        private String 商家出资常规优惠;
        /**
        *   是否预售
        */
        @ExcelProperty(value = "是否预售")
        @ColumnWidth(15)
        @Column(name = "是否预售",type = "VARCHAR(100)")
        private String 是否预售;
        /**
        *   id
        */
        @ExcelProperty(value = "id")
        @ColumnWidth(15)
        @Column(name = "id",type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        private Long id;
}
