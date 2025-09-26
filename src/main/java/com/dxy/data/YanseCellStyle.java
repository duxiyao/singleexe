package com.dxy.data;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YanseCellStyle extends AbstractCellStyleStrategy {
    private static class Inner {
        private static YanseCellStyle instance = new YanseCellStyle();
    }

    public static YanseCellStyle getRedCellStyle() {
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

    // 这里千万记住 想办法能复用的地方把他缓存起来 一个表格最多创建6W个样式 , 不同单元格尽量传同一个 cellStyle
    static Map<String, CellStyle> map = new HashMap<>();

    static CellStyle getS(String h, WriteSheetHolder writeSheetHolder, IndexedColors indexedColors, Cell cell) {
        CellStyle s = map.get(h);
        if (s != null) {
            return s;
        }
        Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
        CellStyle style = workbook.createCellStyle();

        // 克隆原有样式（保留边框等属性）
        if (cell.getCellStyle() != null) {
            style.cloneStyleFrom(cell.getCellStyle());
        }

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 设置橙色背景 IndexedColors.ORANGE.getIndex()
        style.setFillForegroundColor(indexedColors.getIndex());
        map.put(h, style);
        return style;
    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        String h = head.getFieldName();
        if ("是否有百亿促销".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.BRIGHT_GREEN, cell);
            cell.setCellStyle(style);
        }
        if ("百亿基础价提报时间".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.DARK_RED, cell);
            cell.setCellStyle(style);
        }
        if ("百亿满减专区".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.GREEN, cell);
            cell.setCellStyle(style);
        }
        if ("百亿自主降价自主降价".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.GREY_50_PERCENT, cell);
            cell.setCellStyle(style);
        }
        if ("百亿长期216折扣活动名字".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.LIGHT_GREEN, cell);
            cell.setCellStyle(style);
        }
        if ("百亿长期216折扣活动力度".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.PALE_BLUE, cell);
            cell.setCellStyle(style);
        }
        String t = "";
        if (h.startsWith("_226")) {
            t = h.substring(1);
        }
        if ("226品牌限时折扣名称".equals(t)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.GOLD, cell);
            cell.setCellStyle(style);
        }
        if ("226品牌限时折扣力度".equals(t)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.ORANGE, cell);
            cell.setCellStyle(style);
        }
        if ("226品牌限时折扣活动价".equals(t)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.GREY_40_PERCENT, cell);
            cell.setCellStyle(style);
        }
        if ("百亿秒杀".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.SEA_GREEN, cell);
            cell.setCellStyle(style);
        }
        if ("月卡专享价7月".equals(h)) {
            CellStyle style = getS(h, writeSheetHolder, IndexedColors.GREY_80_PERCENT, cell);
            cell.setCellStyle(style);
        }

//        if("".equals(h)){
//
//            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//            CellStyle cellStyle = workbook.createCellStyle();
//            Font writeFont = workbook.createFont();
//            //字体设置成红色
//            writeFont.setColor(IndexedColors.GREEN.getIndex());
//            cellStyle.setFont(writeFont);
//            cell.setCellStyle(cellStyle);
//        }
//        if("".equals(h)){
//            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
//            CellStyle cellStyle = workbook.createCellStyle();
//            Font writeFont = workbook.createFont();
//            //字体设置成红色
//            writeFont.setColor(IndexedColors.RED.getIndex());
//            cellStyle.setFont(writeFont);
//            cell.setCellStyle(cellStyle);
//        }


    }
}
