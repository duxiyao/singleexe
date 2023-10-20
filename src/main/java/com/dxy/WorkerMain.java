package com.dxy;

import com.dxy.util.VersionCtlUtil;

import java.util.Scanner;

public class WorkerMain {
//    static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
//            60L, TimeUnit.SECONDS,
//            new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        Scanner scan = null;
        try {
            try {
                VersionCtlUtil.test("1");
                VersionCtlUtil.test("2");
                VersionCtlUtil.up("");
            } catch (Exception e) {
            }

            scan = new Scanner(System.in);
            boolean flag = true;
            while (flag) {
                System.out.println();
                System.out.println("请将对应要处理的数据文件，放入对应的workerspace1 或 workerspace2 中.");
                System.out.println("请选择要执行的功能，并敲回车确定：");
                System.out.println("1：处理【多多店铺商品资料】");
                System.out.println("2：处理【销售报表】");
                System.out.println("q：退出程序");
                System.out.println("请输入：");
                if (scan.hasNext()) {
                    String s = scan.next();

                    if ("1".equals(s)) {
                        VersionCtlUtil.test("1");
                        Tasks.exe1();
                        System.err.println("1：【多多店铺商品资料】 处理完成");
                    }
                    if ("2".equals(s)) {
                        VersionCtlUtil.test("2");
                        Tasks.exe2();
                        System.err.println("2：【销售报表】 处理完成");
                    }
                    if ("q".equals(s)) {
                        flag = false;
                        Tasks.E.shutdownNow();
                    }

                    System.err.println();
                    System.err.println();
                    System.err.println();
                    System.err.println("电脑要爆炸了！！！！！！！");
                    Thread.sleep(1000);
                    System.out.println(3);
                    Thread.sleep(1000);
                    System.out.println(2);
                    Thread.sleep(1000);
                    System.out.println(1);
                    System.out.println("逗你玩~");
                    System.out.println();
                    System.out.println();
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scan != null) {
                scan.close();
            }
        }

    }
}
