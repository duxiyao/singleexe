package com.dxy.util.test.data;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ${clsName} extends BaseRowModel {
    <#list datas as item>

        @ExcelProperty(value = "${item.title}")
        @ColumnWidth(15)
        private String ${item.fn};
    </#list>
}
