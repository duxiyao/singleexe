package com.dxy;


import com.dxy.data.DINGDAN;
import com.dxy.data.ERP;
import com.dxy.util.ExcelUtil;
import com.dxy.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkerMain2 {

    static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        File workspace = new File(System.getProperty("user.dir"), "workspace");

        //todo
        File dd = new File(workspace, "xlsx");
        FutureTask<List<DINGDAN>> futureTask = new FutureTask<>(() -> {
            System.out.println("开始读取" + dd.getName() + "的数据");
            List<DINGDAN> list = ExcelUtil.read(dd.getAbsolutePath(), DINGDAN.class);
            list = list.parallelStream().filter(dingdan -> {
                try {
                    boolean flag = dingdan.getF5() == null || dingdan.getF5().isEmpty();
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

        FutureTask<List<DINGDAN>> futureTask1 = new FutureTask<>(() -> {
            List<DINGDAN> list = futureTask.get();
            Map<String, DINGDAN> map = list.parallelStream().collect(Collectors.toMap(a -> a.getF5(), a -> a, (item1, item2) -> item1));
//            list.parallelStream().

            return list;
        });


        E.submit(futureTask);

        E.shutdown();
        System.out.println("end");

    }
}
