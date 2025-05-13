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
        String h = head.getFieldName();
        if (isHead) {
            if ("f28".equals(h)
                    ||"f24".equals(h)
                    ||"f25".equals(h)
                    ||"f29".equals(h)
                    ||"f30".equals(h)
                    ||"f31".equals(h)
                    ||"f32".equals(h)
                    ||"f33".equals(h)
                    ||"f34".equals(h)
                    ||"f35".equals(h)
                    ||"f36".equals(h)
                    ||"f37".equals(h)
                    ||"f38".equals(h)
                    ||"f39".equals(h)
                    ||"f40".equals(h)
            ){
//                Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//                CellStyle cellStyle = workbook.createCellStyle();
//                Font writeFont = workbook.createFont();
//                //字体设置成红色
//                writeFont.setColor(IndexedColors.ORANGE.getIndex());
//                cellStyle.setFont(writeFont);
//                cell.setCellStyle(cellStyle);
                Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
                CellStyle style = workbook.createCellStyle();

                // 克隆原有样式（保留边框等属性）
                if (cell.getCellStyle() != null) {
                    style.cloneStyleFrom(cell.getCellStyle());
                }

                // 设置橙色背景
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());

                cell.setCellStyle(style);
            }
            return;
        }
//        // 这里千万记住 想办法能复用的地方把他缓存起来 一个表格最多创建6W个样式 , 不同单元格尽量传同一个 cellStyle
        if (!"f28".equals(h))
            return;
        String v = cell.getStringCellValue();
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
