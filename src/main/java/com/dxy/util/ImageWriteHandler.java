package com.dxy.util;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ImageWriteHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        // 不需要实现
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        // 不需要实现
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if (isHead || cell == null || cellDataList == null || cellDataList.isEmpty()) {
            return;
        }

        // 检查是否是图片列（f10对应的列）
        if ("图片".equals(head.getHeadNameList().get(0))) {
            CellData<?> cellData = cellDataList.get(0);
            String imageUrl = (String) cellData.getStringValue();

            if (imageUrl != null && !imageUrl.isEmpty()) {
                try {
                    // 下载图片
                    byte[] imageBytes = downloadImage(imageUrl);

                    // 获取当前sheet
                    Sheet sheet = writeSheetHolder.getSheet();

                    // 获取图片位置信息
                    Drawing<?> drawing = sheet.createDrawingPatriarch();
                    ClientAnchor anchor = sheet.getWorkbook().getCreationHelper()
                            .createClientAnchor();
                    anchor.setCol1(cell.getColumnIndex());
                    anchor.setRow1(cell.getRowIndex());
                    anchor.setCol2(cell.getColumnIndex() + 1);
                    anchor.setRow2(cell.getRowIndex() + 1);

                    // 添加图片
                    int pictureIdx = sheet.getWorkbook().addPicture(imageBytes, Workbook.PICTURE_TYPE_JPEG);
                    Picture picture = drawing.createPicture(anchor, pictureIdx);

                    // 调整图片大小
                    picture.resize();

                    // 清空单元格文本内容
                    cell.setCellValue("");
                } catch (IOException e) {
                    cell.setCellValue("图片加载失败: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private byte[] downloadImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            return out.toByteArray();
        }
    }
}