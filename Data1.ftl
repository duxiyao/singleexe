package com.dxy.util.test.data;

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
@Table(name = "${tabname}") // 指定表名
public class ${clsName} {
    <#list datas as item>
        /**
        *   ${item.title}
        */
        @ExcelProperty(value = "${item.title}")
        @ColumnWidth(15)
        <#if item.title == "id">
        @Column(type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        <#else>
        @Column(type = "VARCHAR(100)", nullable = false)
        </#if>
        private String ${item.fn};
    </#list>
}
