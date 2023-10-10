package com.dxy.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @description:
 * @author: chenmingjian
 * @date: 19-3-18 16:16
 * https://github.com/alibaba/easyexcel
 * https://www.yuque.com/easyexcel/doc/read#f14acd88
 */
@Slf4j
public class ExcelUtil {

    private static Sheet initSheet;

    static {
        initSheet = new Sheet(1, 0);
        initSheet.setSheetName("sheet");
        //设置自适应宽度
        initSheet.setAutoWidth(Boolean.TRUE);
    }

    public static Sheet getInitSheet() {
        return initSheet;
    }

    /**
     * 读取少于1000行数据
     *
     * @param filePath 文件绝对路径
     * @return
     */
    public static List<Object> readLessThan1000Row(String filePath) {
        return readLessThan1000RowBySheet(filePath, null);
    }

    /**
     * 读小于1000行数据, 带样式
     * filePath 文件绝对路径
     * initSheet ：
     * sheetNo: sheet页码，默认为1
     * headLineMun: 从第几行开始读取数据，默认为0, 表示从第一行开始读取
     * clazz: 返回数据List<Object> 中Object的类名
     */
    public static List<Object> readLessThan1000RowBySheet(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            return EasyExcelFactory.read(fileStream, sheet);
        } catch (FileNotFoundException e) {
            log.info("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.info("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 读大于1000行数据
     *
     * @param filePath 文件觉得路径
     * @return
     */
    public static List<Object> readMoreThan1000Row(String filePath) {
        return readMoreThan1000RowBySheet(filePath, null);
    }

    /**
     * 读大于1000行数据, 带样式
     *
     * @param filePath 文件觉得路径
     * @return
     */
    public static List<Object> readMoreThan1000RowBySheet(String filePath, Sheet sheet) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        sheet = sheet != null ? sheet : initSheet;

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            ExcelListener excelListener = new ExcelListener();
            EasyExcelFactory.readBySax(fileStream, sheet, excelListener);
            return excelListener.getDatas();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    public static <T> List<T> readExcelData(String filePath, TExcelListener<T> excelListener) {
        if (!StringUtils.hasText(filePath)) {
            return null;
        }

        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filePath);
            EasyExcelFactory.readBySax(fileStream, initSheet, excelListener);
            return excelListener.getDatas();
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (fileStream != null) {
                    fileStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件读取失败, 失败原因：{}", e);
            }
        }
        return null;
    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     * @param head     表头
     */
    public static void writeBySimple(String filePath, List<List<Object>> data, List<String> head) {
        writeSimpleBySheet(filePath, data, head, null);
    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     * @param sheet    excle页面样式
     * @param head     表头
     */
    public static void writeSimpleBySheet(String filePath, List<List<Object>> data, List<String> head, Sheet sheet) {
        sheet = (sheet != null) ? sheet : initSheet;

        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data, sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     */
    public static void writeWithTemplate(String filePath, List<? extends BaseRowModel> data) {
        writeWithTemplateAndSheet(filePath, data, null);
    }

    /**
     * 生成excle
     *
     * @param filePath 绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param data     数据源
     * @param sheet    excle页面样式
     */
    public static void writeWithTemplateAndSheet(String filePath, List<? extends BaseRowModel> data, Sheet sheet) {
        if (CollectionUtils.isEmpty(data)) {
            return;
        }

        sheet = (sheet != null) ? sheet : initSheet;
        sheet.setClazz(data.get(0).getClass());

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write(data, sheet);
        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }

    /**
     * 生成多Sheet的excle
     *
     * @param filePath              绝对路径, 如：/home/chenmingjian/Downloads/aaa.xlsx
     * @param multipleSheelPropetys
     */
    public static void writeWithMultipleSheel(String filePath, List<MultipleSheelPropety> multipleSheelPropetys) {
        if (CollectionUtils.isEmpty(multipleSheelPropetys)) {
            return;
        }

        OutputStream outputStream = null;
        ExcelWriter writer = null;
        try {
            outputStream = new FileOutputStream(filePath);
            writer = EasyExcelFactory.getWriter(outputStream);
            for (MultipleSheelPropety multipleSheelPropety : multipleSheelPropetys) {
                Sheet sheet = multipleSheelPropety.getSheet() != null ? multipleSheelPropety.getSheet() : initSheet;
                if (!CollectionUtils.isEmpty(multipleSheelPropety.getData())) {
                    sheet.setClazz(multipleSheelPropety.getData().get(0).getClass());
                }
                writer.write(multipleSheelPropety.getData(), sheet);
            }

        } catch (FileNotFoundException e) {
            log.error("找不到文件或文件路径错误, 文件：{}", filePath);
        } finally {
            try {
                if (writer != null) {
                    writer.finish();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                log.error("excel文件导出失败, 失败原因：{}", e);
            }
        }

    }


    /*********************匿名内部类开始，可以提取出去******************************/

    @Data
    public static class MultipleSheelPropety {

        private List<? extends BaseRowModel> data;

        private Sheet sheet;
    }

    /**
     * 解析监听器，
     * 每解析一行会回调invoke()方法。
     * 整个excel解析结束会执行doAfterAllAnalysed()方法
     *
     * @author: chenmingjian
     * @date: 19-4-3 14:11
     */
    @Getter
    @Setter
    public static class ExcelListener extends AnalysisEventListener {

        private List<Object> datas = new ArrayList<>();

        /**
         * 逐行解析
         * object : 当前行的数据
         */
        @Override
        public void invoke(Object object, AnalysisContext context) {
            //当前行
            // context.getCurrentRowNum()
            if (object != null) {
                datas.add(object);
            }
        }


        /**
         * 解析完所有数据后会调用该方法
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            //解析结束销毁不用的资源
        }

    }


    @Getter
    @Setter
    public static abstract class TExcelListener<T> extends AnalysisEventListener<T> {

        protected List<T> datas = new ArrayList<>();

//        @Override
//        public void invoke(T t, AnalysisContext analysisContext) {
//
//        }
//
//        @Override
//        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//
//        }
//
//        @Override
//        public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
//
//        }
    }

    /************************匿名内部类结束，可以提取出去***************************/


    private static Map<String, Field> parseObj(Class excelData1Class) {
        Map<String, Field> map = new HashMap<>();
        Field[] fields = excelData1Class.getDeclaredFields();
        for (Field field : fields) {
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            String h = excelProperty.value()[0];
            map.put(h, field);
        }
        return map;
    }

    public static List<String> readTitles(String filePath) {
        List<String> ret = new ArrayList<>();

        EasyExcelFactory.read(new File(filePath), new ReadListener<Map<Integer, String>>() {
            @Override
            public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
            }

            @Override
            public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
                Iterator<Integer> iterator = map.keySet().iterator();
                iterator.forEachRemaining(integer -> {
                    ret.add(map.get(integer).getStringValue());
                });
            }

            @Override
            public void invoke(Map<Integer, String> o, AnalysisContext analysisContext) {
            }

            @Override
            public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }

            @Override
            public boolean hasNext(AnalysisContext analysisContext) {
                return true;
            }
        })
                .doReadAll();
        return ret;
    }

    public static <T> List<T> read(String filePath, Class<T> dcls) {
        List<T> ret = new ArrayList<>();
        Map<String, Field> clsHeader = parseObj(dcls);
        Map<Integer, String> dataHeader = new HashMap<>();

        EasyExcelFactory.read(new File(filePath), new ReadListener<Map<Integer, String>>() {

            @Override
            public void onException(Exception e, AnalysisContext analysisContext) throws Exception {
//                String s = "";
            }

            @Override
            public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {
                Iterator<Integer> iterator = map.keySet().iterator();
                iterator.forEachRemaining(integer -> {
                    dataHeader.put(integer, map.get(integer).getStringValue());
                });
//                String s = "";
            }

            @Override
            public void invoke(Map<Integer, String> o, AnalysisContext analysisContext) {

                T t = null;
                try {
                    t = dcls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                Iterator<Integer> iterator = o.keySet().iterator();
                T finalT = t;
                iterator.forEachRemaining(i -> {
                    String h = dataHeader.get(i);
                    Field field = clsHeader.get(h);
                    String v = o.get(i);
                    try {
                        field.setAccessible(true);
                        field.set(finalT, v);
                    } catch (Exception e) {
                        if (!(e instanceof NullPointerException)) {
                            e.printStackTrace();
                        }
                    }
//                    String s = "";
                });

                ret.add(finalT);
//                String s = "";
            }

            @Override
            public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {
//                String s = "";
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                String s = "";
            }

            @Override
            public boolean hasNext(AnalysisContext analysisContext) {
                return true;
            }
        })
                .doReadAll();
//                .doReadAllSync();
        return ret;
    }
}