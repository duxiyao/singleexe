package com.dxy;

import com.dxy.data.ERP;
import com.dxy.data.MData;
import com.dxy.data.PanHuo;
import com.dxy.util.ExcelUtil;
import com.dxy.util.FileHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class WorkerMain {
    public static boolean ISLOG = true;
    static File filePanhuo = null;
    static File fileERP = null;
    static boolean canUse = true;
    static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        //region
        List<One> ones = new ArrayList<>();
        ones.add(new One(One.SHOP1, E));
        ones.add(new One(One.SHOP2, E));
        ones.add(new One(One.SHOP3, E));
        ones.add(new One(One.SHOP4, E));
        ones.add(new One(One.SHOP5, E));

        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), "workspace");
        FileHelper.listOnlyFilesByOneDeep(workspace, fileList);

        fileList.forEach(file -> {
            String fn = file.getName();
            if (fn.contains(One.PH)) {
                filePanhuo = file;
            } else if (fn.contains(One.ERP)) {
                fileERP = file;
            } else {
                ones.forEach(a -> {
                    a.filter(file);
                });
            }
        });

        ones.forEach(a -> {
            if (!a.canUse()) {
                canUse = false;
            }
        });
        if (!canUse || filePanhuo == null || fileERP == null) {
            System.out.println("文件不全，请检查无误后继续");
            return;
        }
        //endregion

        FutureTask<List<PanHuo>> futureTask = new FutureTask<>(() -> {
            System.out.println("开始读取" + One.PH + "的数据");
            List<PanHuo> list = ExcelUtil.read(filePanhuo.getAbsolutePath(), PanHuo.class);
            System.out.println(One.PH + "的数据读取完毕");
            return list;
        });
        FutureTask<List<ERP>> ferp = new FutureTask<>(() -> {
            System.out.println("开始读取" + One.ERP + "的数据");
            List<ERP> list = ExcelUtil.read(fileERP.getAbsolutePath(), ERP.class);
            System.out.println(One.ERP + "的数据读取完毕");
            return list;
        });

        E.submit(futureTask);
        E.submit(ferp);

        List<Future<List<MData>>> list = new ArrayList<>();
        ones.forEach(a -> {
            try {
                a.read();
                list.add(a.get(futureTask.get(), ferp.get()));
            } catch (Exception e) {
                if (WorkerMain.ISLOG) {
                    e.printStackTrace();
                }
            }
        });

        List<MData> rets = new ArrayList<>();
        list.forEach(listFuture -> {
            try {
                rets.addAll(listFuture.get());
            } catch (Exception e) {
                if (WorkerMain.ISLOG) {
                    e.printStackTrace();
                }
            }
        });

//        List<MData> tmp = new ArrayList<>();
//        for (int i = 0; i < rets.size(); i++) {
//            tmp.add(rets.get(i));
//            if(i==5)
//                break;
//        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
        String outFilename = simpleDateFormat.format(new Date()) + "多多店铺商品资料.xlsx";
        File outfp = new File(workspace, outFilename);
        ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), rets);
        E.shutdownNow();
        System.out.println("end");
    }
}
