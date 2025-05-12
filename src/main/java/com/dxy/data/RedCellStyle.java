package com.dxy.data;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class RedCellStyle extends AbstractCellStyleStrategy {
    private static class Inner {
        private static com.dxy.data.RedCellStyle instance = new com.dxy.data.RedCellStyle();
    }

    public static com.dxy.data.RedCellStyle getRedCellStyle() {
        return Inner.instance;
    }

    @Override
    protected void initCellStyle(Workbook workbook) {

    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer integer) {

    }

    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer integer) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead)
            return;
//        // 这里千万记住 想办法能复用的地方把他缓存起来 一个表格最多创建6W个样式 , 不同单元格尽量传同一个 cellStyle
        String v = cell.getStringCellValue();
        String h = head.getFieldName();
        if ("有百亿标".equals(v)) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            CellStyle cellStyle = workbook.createCellStyle();
            Font writeFont = workbook.createFont();
            //字体设置成红色
            writeFont.setColor(IndexedColors.GREEN.getIndex());
            cellStyle.setFont(writeFont);
            cell.setCellStyle(cellStyle);
        } else if ("没有百亿标".equals(v)) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            CellStyle cellStyle = workbook.createCellStyle();
            Font writeFont = workbook.createFont();
            //字体设置成红色
            writeFont.setColor(IndexedColors.RED.getIndex());
            cellStyle.setFont(writeFont);
            cell.setCellStyle(cellStyle);

        }
    }
}
