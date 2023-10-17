package com.dxy;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dxy.data.A;
import com.dxy.data.DINGDAN;
import com.dxy.data.ERP;
import com.dxy.data.XSBB;
import com.dxy.util.ExcelUtil;
import com.dxy.util.StringUtils;
import com.dxy.util.TypeUtil;
import com.dxy.util.test.Java8GroupBy;
import javafx.util.Pair;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
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
//            Map<String, DINGDAN> map = list.parallelStream().collect(Collectors.toMap(a -> a.getF4(), a -> a, (item1, item2) -> item1));
//            Map<String, String> mprice = list.parallelStream().collect(Collectors.toMap(a -> a.getF5(), a -> a.getF8(), (item1, item2) -> item1));

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
                Pair<Double, Long> p =  m.get(xsbb.getF9());
                try {
                    xsbb.setF3(xsbb.getF9());
                    xsbb.setF13(decimalFormat.format(p.getValue()));
                    xsbb.setF14(decimalFormat.format(p.getKey()));
                    //todo
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return list;
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

//        List<XSBB> rets = new ArrayList<>();
//        rets.addAll(futureTask2.get());
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
//        String outFilename = simpleDateFormat.format(new Date()) + "aa.xlsx";
//        File outfp = new File(workspace, outFilename);
//        //todo
//        ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), rets);
//
//        // 方法3 如果写到不同的sheet 不同的对象
//        fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 指定文件
//        try (ExcelWriter excelWriter = EasyExcel.write(fileName).build()) {
//            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
//            for (int i = 0; i < 5; i++) {
//                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class
//                // 实际上可以一直变
//                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(DemoData.class).build();
//                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
//                List<DemoData> data = data();
//                excelWriter.write(data, writeSheet);
//            }
//        }

        System.out.println("end");

    }
}
