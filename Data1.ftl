package com.dxy.util.test.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dxy.anotations.Column;
import com.dxy.anotations.Table;
import com.alibaba.excel.annotation.ExcelIgnore;

/*
INSERT INTO ${tabname?trim}(<#list datas as item>`${item.tn?trim}`<#if item?has_next>,</#if></#list>)VALUES(<#list datas as item>${item.vtn?trim} <#if item?has_next>,</#if></#list>)


*/
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
        @Column(name = "${item.tn}",type = "BIGINT", primaryKey = true, autoIncrement = true)
        @ExcelIgnore
        private Long ${item.fn};
        <#else>
        @Column(name = "${item.tn}",type = "VARCHAR(100)")
        private String ${item.fn};
        </#if>
    </#list>
}
