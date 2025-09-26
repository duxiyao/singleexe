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
INSERT INTO 活动ID和活动类型分解(`活动ID`,`活动名称`,`活动大类`)VALUES(#{活动ID} ,#{活动名称} ,#{活动大类} )

SELECT 活动大类 FROM 活动ID和活动类型分解 group by 活动大类
*/
@EqualsAndHashCode
@Data
@Table(name = "活动ID和活动类型分解") // 指定表名
public class HDIDHDFJ {
        /**
        *   活动ID
        */
        @ExcelProperty(value = "活动ID")
        @ColumnWidth(15)
        @Column(name = "活动ID",type = "VARCHAR(100)")
        private String 活动ID;
        /**
        *   活动名称
        */
        @ExcelProperty(value = "活动名称")
        @ColumnWidth(15)
        @Column(name = "活动名称",type = "VARCHAR(100)")
        private String 活动名称;
        /**
        *   活动大类
        */
        @ExcelProperty(value = "活动大类")
        @ColumnWidth(15)
        @Column(name = "活动大类",type = "VARCHAR(100)")
        private String 活动大类;
        /**
        *   id
        */
        @ExcelProperty(value = "id")
        @ColumnWidth(15)
        @Column(name = "id",type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        private Long id;
}
