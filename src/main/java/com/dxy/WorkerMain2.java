package com.dxy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dxy.data.DINGDAN;
import com.dxy.data.XSBB;
import com.dxy.util.ExcelUtil;
import com.dxy.util.TypeUtil;
import javafx.util.Pair;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkerMain2 {

    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        File workspace = new File(System.getProperty("user.dir"), "workspace1");
        File workspace = new File("e:\\1\\", "workspace1");

        //todo
        File dd = new File(workspace, "订单_2023-10-12_11-08-40.10291624.10825900_1(1).xlsx");
        FutureTask<List<DINGDAN>> futureTask = new FutureTask<>(() -> {
            System.out.println("开始读取" + dd.getName() + "的数据");
            List<DINGDAN> list = ExcelUtil.read(dd.getAbsolutePath(), DINGDAN.class);
            list = list.parallelStream().filter(dingdan -> {
                try {
                    boolean flag = dingdan.getF5() == null || dingdan.getF5().isEmpty() || (dingdan.getF1() != null && dingdan.getF1().contains("成功退款"));
                    if (flag) {
                        return false;
                    } else {
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }).collect(Collectors.toList());
            System.out.println(dd.getName() + "的数据读取完毕");
            return list;
        });

        FutureTask<Map<String, Pair>> futureTask1 = new FutureTask<>(() -> {
            List<DINGDAN> list = futureTask.get();
            Map<String, List<DINGDAN>> mDingdanhao = list.parallelStream().collect(Collectors.groupingBy(DINGDAN::getF4));
            mDingdanhao.keySet().parallelStream().forEach(s -> {
                List<DINGDAN> l = mDingdanhao.get(s);
                for (int i = 0; i < l.size(); i++) {
                    if (i > 0) {
                        l.get(i).setF8("0");
                    }
                }
            });
//            list.forEach(new Consumer<DINGDAN>() {
//                @Override
//                public void accept(DINGDAN dd1) {
//                    String s = "";
//                }
//            });
            Map<String, Pair> m = new HashMap<>();
            Map<String, Map<String, List<DINGDAN>>> mgroup = list.parallelStream()
                    .collect(Collectors.groupingBy(DINGDAN::getF5, Collectors.groupingBy(DINGDAN::getF1)));
            mgroup.keySet().parallelStream().forEach(s -> {
                Map<String, List<DINGDAN>> mstatus = mgroup.get(s);
                List<DINGDAN> ss = new ArrayList<>();
                mstatus.values().forEach(dingdans -> {
                    dingdans.forEach(dd1 -> ss.add(dd1));
                });
                IntSummaryStatistics statsCnt = ss.stream().mapToInt((x) -> TypeUtil.parseInt(x.getF9())).summaryStatistics();
                DoubleSummaryStatistics statsPrice = ss.stream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF8())).summaryStatistics();

                m.put(s, new Pair<Double, Long>(statsPrice.getSum(), statsCnt.getSum()));
            });


            return m;
        });

        File bb = new File(workspace, "销售报表输出格式xlsx.xlsx");
        FutureTask<List<XSBB>> futureTask2 = new FutureTask<>(() -> {
            System.out.println("开始读取" + bb.getName() + "的数据");
            List<XSBB> list = ExcelUtil.read(bb.getAbsolutePath(), XSBB.class);
            System.out.println(bb.getName() + "的数据读取完毕");

            Map<String, Pair> m = futureTask1.get();
            List<XSBB> newList = list.parallelStream().filter(xsbb -> {
                boolean flag = m.containsKey(xsbb.getF9());
                return flag;
            }).collect(Collectors.toList());
            newList.forEach(xsbb -> {
                Pair<Double, Long> p = m.get(xsbb.getF9());
                try {
                    xsbb.setF3(xsbb.getF9());
                    xsbb.setF13(decimalFormat.format(p.getValue()));
                    xsbb.setF14(decimalFormat.format(p.getKey()));
                    xsbb.setF15(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF13()) * TypeUtil.parseFloat(xsbb.getF21())));
                    xsbb.setF16(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF14()) - TypeUtil.parseFloat(xsbb.getF15())));
                    xsbb.setF17(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF16()) / TypeUtil.parseFloat(xsbb.getF13())));
                    xsbb.setF18(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF14()) / TypeUtil.parseFloat(xsbb.getF13())));
                    xsbb.setF19(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF16()) / TypeUtil.parseFloat(xsbb.getF14())));
                    xsbb.setF20(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF18()) - TypeUtil.parseFloat(xsbb.getF8())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return newList;
        });

        E.submit(futureTask);
        E.submit(futureTask1);
        E.submit(futureTask2);

//        List<A> rets = new ArrayList<>();
//        Map<String, Pair> m = futureTask1.get();
//        m.keySet().stream().forEach(s -> {
//            A a = new A();
//            a.setF0(s);
//            Pair<Double, Long> p = m.get(s);
//            try {
//                a.setF1(decimalFormat.format(p.getKey()));
//                a.setF2(decimalFormat.format(p.getValue()));
////                a.setF1(p.getKey().toString());
////                a.setF2(p.getValue().toString());
//                rets.add(a);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });


        E.shutdown();

        List<XSBB> rets = new ArrayList<>();
        rets.addAll(futureTask2.get());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
        String outFilename = simpleDateFormat.format(new Date()) + "销售报表输出.xlsx";
        File outfp = new File(workspace, outFilename);
//        ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), rets);


        DoubleSummaryStatistics statsPrice = rets.parallelStream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF14())).summaryStatistics();
        double sum = statsPrice.getSum();
        Map<String, Double> m = rets.parallelStream().collect(Collectors.groupingBy(XSBB::getF33, Collectors.summingDouble(XSBB::getDoubleF14)));

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        //对m的value进行降序排列
        m.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(e -> {
                    //判断占比大于等于15%才放进sortedMap
                    if (e.getValue() / sum >= 0.15) {
                        sortedMap.put(e.getKey(), e.getValue());
                    }
                });

        List<List<XSBB>> sheetDatas = new LinkedList<>();
        sheetDatas.add(rets);
        sortedMap.keySet().forEach(s -> sheetDatas.add(rets.parallelStream().filter(xsbb -> s.equals(xsbb.getF33())).collect(Collectors.toList())));

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(outfp.getAbsolutePath()).build();
            for (int i = 0; i < sheetDatas.size(); i++) {
                List<XSBB> data = sheetDatas.get(i);
                String sheetName = i + "";
                if (i > 0) {
                    try {
                        sheetName = i + "_" + data.get(0).getF33();
                    } catch (Exception e) {
                    }
                }
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).head(XSBB.class).build();
                excelWriter.write(data, writeSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

        System.out.println("end");

    }
}
