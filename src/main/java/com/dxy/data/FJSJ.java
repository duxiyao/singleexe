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
INSERT INTO 分解数据(`商品名称`,`商品ID`,`商品ID`,`活动ID`,`活动ID`,`活动大类`,`活动名称`,`商品图片`,`类目名称`,`提交时间`,`活动价`,`活动库存`,`活动时间`,`活动状态`,`累计销量`,`库存`,`失效原因`,`id`)VALUES(#{商品名称} ,#{商品ID} ,#{商品ID} ,#{活动ID} ,#{活动ID} ,#{活动大类} ,#{活动名称} ,#{商品图片} ,#{类目名称} ,#{提交时间} ,#{活动价} ,#{活动库存} ,#{活动时间} ,#{活动状态} ,#{累计销量} ,#{库存} ,#{失效原因} ,#{id} )


*/
@EqualsAndHashCode
@Data
@Table(name = "分解数据") // 指定表名
public class FJSJ {
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
        *   商品ID 
        */
        @ExcelProperty(value = "商品ID ")
        @ColumnWidth(15)
        private String 商品ID1;
        /**
        *   活动ID 
        */
        @ExcelProperty(value = "活动ID ")
        @ColumnWidth(15)
        @Column(name = "活动ID",type = "VARCHAR(100)")
        private String 活动ID;
        /**
        *   活动ID
        */
        @ExcelProperty(value = "活动ID")
        @ColumnWidth(15)
        private String 活动ID1;
        /**
        *   活动大类 
        */
        @ExcelProperty(value = "活动大类 ")
        @ColumnWidth(15)
        @Column(name = "活动大类",type = "VARCHAR(100)")
        private String 活动大类;
        /**
        *   活动名称 
        */
        @ExcelProperty(value = "活动名称 ")
        @ColumnWidth(15)
        @Column(name = "活动名称",type = "VARCHAR(100)")
        private String 活动名称;
        /**
        *   商品图片 
        */
        @ExcelProperty(value = "商品图片 ")
        @ColumnWidth(15)
        @Column(name = "商品图片",type = "VARCHAR(100)")
        private String 商品图片;
        /**
        *   类目名称 
        */
        @ExcelProperty(value = "类目名称 ")
        @ColumnWidth(15)
        @Column(name = "类目名称",type = "VARCHAR(100)")
        private String 类目名称;
        /**
        *   提交时间 
        */
        @ExcelProperty(value = "提交时间 ")
        @ColumnWidth(15)
        @Column(name = "提交时间",type = "VARCHAR(100)")
        private String 提交时间;
        /**
        *   活动价 
        */
        @ExcelProperty(value = "活动价 ")
        @ColumnWidth(15)
        @Column(name = "活动价",type = "VARCHAR(100)")
        private String 活动价;
        /**
        *   活动库存 
        */
        @ExcelProperty(value = "活动库存 ")
        @ColumnWidth(15)
        @Column(name = "活动库存",type = "VARCHAR(100)")
        private String 活动库存;
        /**
        *   活动时间 
        */
        @ExcelProperty(value = "活动时间 ")
        @ColumnWidth(15)
        @Column(name = "活动时间",type = "VARCHAR(100)")
        private String 活动时间;
        /**
        *   活动状态 
        */
        @ExcelProperty(value = "活动状态 ")
        @ColumnWidth(15)
        @Column(name = "活动状态",type = "VARCHAR(100)")
        private String 活动状态;
        /**
        *   累计销量 
        */
        @ExcelProperty(value = "累计销量 ")
        @ColumnWidth(15)
        @Column(name = "累计销量",type = "VARCHAR(100)")
        private String 累计销量;
        /**
        *   库存
        */
        @ExcelProperty(value = "库存")
        @ColumnWidth(15)
        @Column(name = "库存",type = "VARCHAR(100)")
        private String 库存;
        /**
        *   失效原因 
        */
        @ExcelProperty(value = "失效原因 ")
        @ColumnWidth(15)
        @Column(name = "失效原因",type = "VARCHAR(100)")
        private String 失效原因;
        /**
        *   id
        */
        @ExcelProperty(value = "id")
        @ColumnWidth(15)
        @Column(name = "id",type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        private Long id;
}
