package com.dxy;

import com.dxy.util.FileHelper;
import com.dxy.util.VersionCtlUtil;

import java.io.File;
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
                VersionCtlUtil.test("3");
                VersionCtlUtil.test0("6");

                File workspace = new File(System.getProperty("user.dir"), "WK5");
                if (!workspace.exists()) {
                    workspace.mkdirs();
                }

                workspace = new File(System.getProperty("user.dir"), "workspace4");
                FileHelper.deleteDir(workspace.getAbsolutePath());
                if (!workspace.exists()) {
                    workspace.mkdirs();
                }
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
                System.out.println("3：处理【销售报表加上推广数据】");
                System.out.println("4：处理【标准推广-关键词-添加关键词列表】");
                System.out.println("5：处理【弹窗比价】");
                System.out.println("6：处理【图片】");
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
                    if ("3".equals(s)) {
                        VersionCtlUtil.test("3");
                        Tasks.exe3();
                        System.err.println("3：【销售报表加上推广数据】 处理完成");
                    }
                    if ("4".equals(s)) {
                        Tasks.exe4();
                        System.err.println("4：【标准推广-关键词-添加关键词列表】 处理完成");
                    }
                    if ("5".equals(s)) {
                        Tasks.exe5();
                        System.err.println("5：【弹窗比价】 处理完成");
                    }
                    if ("6".equals(s)) {
                        Tasks.exe6();
                        System.err.println("5：【图片】 处理完成");
                    }
                    if ("q".equals(s)) {
                        flag = false;
                        Tasks.E.shutdownNow();
                    }

                    System.err.println();
                    System.err.println();
                    System.err.println();
                    System.err.println("电脑要爆炸了！！！！！！！");
                    long millis = 500;
                    Thread.sleep(millis);
                    System.out.print(3);
                    Thread.sleep(millis);
                    System.out.print(2);
                    Thread.sleep(millis);
                    System.out.print(1);
                    Thread.sleep(millis);
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
