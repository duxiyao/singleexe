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
@Table(name = "营销活动-报名记录") // 指定表名
public class YXHDBMJL {
        /**
        *   提交时间
        */
        @ExcelProperty(value = "提交时间")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 提交时间;
        /**
        *   活动时间
        */
        @ExcelProperty(value = "活动时间")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动时间;
        /**
        *   累计销量
        */
        @ExcelProperty(value = "累计销量")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 累计销量;
        /**
        *   商品图片
        */
        @ExcelProperty(value = "商品图片")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品图片;
        /**
        *   类目名称
        */
        @ExcelProperty(value = "类目名称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 类目名称;
        /**
        *   失效原因
        */
        @ExcelProperty(value = "失效原因")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 失效原因;
        /**
        *   活动价
        */
        @ExcelProperty(value = "活动价")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动价;
        /**
        *   库存
        */
        @ExcelProperty(value = "库存")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 库存;
        /**
        *   商品名称
        */
        @ExcelProperty(value = "商品名称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品名称;
        /**
        *   活动状态
        */
        @ExcelProperty(value = "活动状态")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动状态;
        /**
        *   活动ID
        */
        @ExcelProperty(value = "活动ID")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动ID;
        /**
        *   商品ID
        */
        @ExcelProperty(value = "商品ID")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 商品ID;
        /**
        *   活动名称
        */
        @ExcelProperty(value = "活动名称")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动名称;
        /**
        *   活动库存
        */
        @ExcelProperty(value = "活动库存")
        @ColumnWidth(15)
        @Column(type = "VARCHAR(100)", nullable = false)
        private String 活动库存;
        /**
        *   id
        */
        @ExcelProperty(value = "id")
        @ColumnWidth(15)
        @Column(type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        private String id;
}
