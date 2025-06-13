package com.dxy.util.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPictureData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelImageExtractor {

    public static void main(String[] args) {
        // 输入Excel文件路径
        String excelFilePath = "input.xlsx";
        // 输出图片文件夹路径
        String outputFolder = "images/";

        // 创建输出文件夹
        new File(outputFolder).mkdirs();

        try {
            // 读取Excel并处理图片
            extractImagesFromExcel(excelFilePath, outputFolder);
            System.out.println("图片导出完成！");
        } catch (Exception e) {
            System.err.println("处理Excel文件时出错: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void extractImagesFromExcel(String filePath, String outputFolder) throws IOException {
        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // 根据文件扩展名创建适当的Workbook实例
            if (filePath.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (filePath.endsWith(".xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("不支持的文件格式，请使用.xls或.xlsx文件");
            }

            // 获取第一个sheet
            Sheet sheet = workbook.getSheetAt(0);

            // 存储行数据
            List<Map<Integer, String>> dataList = extractSheetData(sheet);

            // 处理图片
            processImages(workbook, sheet, dataList, outputFolder);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

    private static List<Map<Integer, String>> extractSheetData(Sheet sheet) {
        List<Map<Integer, String>> dataList = new ArrayList<>();

        // 读取所有行数据
        for (Row row : sheet) {
            Map<Integer, String> rowData = new HashMap<>();
            for (Cell cell : row) {
                // 获取单元格值 - 使用POI 3.17兼容的方式
                String cellValue = "";
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    cellValue = cell.getCellFormula();
                }
                rowData.put(cell.getColumnIndex(), cellValue);
            }
            dataList.add(rowData);
        }
        return dataList;
    }

    private static void processImages(Workbook workbook, Sheet sheet,
                                      List<Map<Integer, String>> dataList,
                                      String outputFolder) throws IOException {
        // 只处理XSSFWorkbook（.xlsx格式）
        if (workbook instanceof XSSFWorkbook) {
            XSSFWorkbook xssfWorkbook = (XSSFWorkbook) workbook;

            // 获取绘图对象
            XSSFDrawing drawing = (XSSFDrawing) sheet.getDrawingPatriarch();
            if (drawing != null) {
                // 存储图片位置和数据的映射
                Map<String, byte[]> pictureMap = new HashMap<>();

                // 遍历所有形状
                for (XSSFShape shape : drawing.getShapes()) {
                    if (shape instanceof XSSFPicture) {
                        XSSFPicture pic = (XSSFPicture) shape;
                        ClientAnchor anchor = pic.getPreferredSize();

                        // 获取图片位置
                        String cellKey = anchor.getRow1() + "," + anchor.getCol1();

                        // 获取图片数据
                        XSSFPictureData pictureData = pic.getPictureData();
                        byte[] imageBytes = pictureData.getData();

                        pictureMap.put(cellKey, imageBytes);
                    }
                }

                // 处理图片
                for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                    Map<Integer, String> rowData = dataList.get(rowIndex);
                    String name = rowData.get(0); // 第一列是名字

                    // 检查第二列是否有图片
                    String cellKey = rowIndex + "," + 1; // 第二列索引为1
                    byte[] imageBytes = pictureMap.get(cellKey);

                    if (imageBytes != null && name != null && !name.trim().isEmpty()) {
                        // 获取图片格式
                        String extension = getImageExtension(pictureMap.get(cellKey));
                        saveImage(name, imageBytes, extension, outputFolder);
                    }
                }
            }
        } else {
            System.out.println("警告: .xls格式的图片提取功能有限，建议使用.xlsx格式");
        }
    }

    private static String getImageExtension(byte[] imageBytes) {
//        if (imageBytes == null || imageBytes.length < 8) {
//            return "dat";
//        }

        // PNG格式判断
        if (imageBytes[1] == 'P' && imageBytes[2] == 'N' && imageBytes[3] == 'G') {
            return "png";
        }
        // JPEG格式判断
        if (imageBytes[6] == 'J' && imageBytes[7] == 'F' && imageBytes[8] == 'I') {
            return "jpg";
        }
        // 其他情况
        return "jpg";
    }

    private static void saveImage(String name, byte[] imageBytes, String extension, String outputFolder) {
        try {
            // 清理文件名中的非法字符
            String safeName = name.replaceAll("[\\\\/:*?\"<>|]", "_");

            // 创建输出文件
            String fileName = outputFolder + safeName + "." + extension;
            File outputFile = new File(fileName);

            // 确保文件名唯一
            int counter = 1;
            while (outputFile.exists()) {
                fileName = outputFolder + safeName + "_" + counter + "." + extension;
                outputFile = new File(fileName);
                counter++;
            }

            // 写入文件
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                fos.write(imageBytes);
                System.out.println("保存图片: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("保存图片失败: " + name);
            e.printStackTrace();
        }
    }
}