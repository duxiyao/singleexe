package com.dxy.data;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.dxy.util.EasyExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;

public class NumberFormatStrategy implements CellWriteHandler {

    private final String numberFormat;
    private Map<Integer, Class<?>> fieldTypeMap;

    public NumberFormatStrategy(String format, Class<?> clazz) {
        this.numberFormat = format;
        // 初始化字段类型映射
        this.fieldTypeMap = EasyExcelUtil.getFieldTypeMap(clazz);
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead || head == null) {
            return;
        }
        // 获取当前列的字段类型
        Class<?> fieldType = fieldTypeMap.get(cell.getColumnIndex());
        // 只处理数字类型字段
        if (fieldType != null && Number.class.isAssignableFrom(fieldType)) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(workbook.createDataFormat().getFormat(numberFormat));
            cell.setCellStyle(cellStyle);
        }
    }
}