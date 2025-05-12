package com.dxy.util.test.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class ${clsName} {
    <#list datas as item>
        /**
        *   ${item.title}
        */
        @ExcelProperty(value = "${item.title}")
        @ColumnWidth(15)
        private String ${item.fn};
    </#list>
}
