package com.dxy;

import com.dxy.data.*;
import com.dxy.mapper.*;
import com.dxy.util.ExcelUtil;
import com.dxy.util.FileHelper;
import com.dxy.util.MyBatisH2Util;
import com.dxy.util.VersionCtlUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TasksH2 {
    public static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public static void init() throws Exception {
        List<Class> tabclss = Arrays.asList(
                User.class,
                DPSPZL.class,
                YXHDBMJL.class,
                HDIDHDFJ.class
        );
        List<Class> mapperclss = Arrays.asList(
                UserMapper.class,
                YXHDBMJLMapper.class,
                HDIDHDFJMapper.class,
                RetMapper.class,
                DPSPZLMapper.class
        );
        // 自动创建表
        MyBatisH2Util.createTables(tabclss, mapperclss);

    }

    public static void exe1() throws Exception {
        exe("216tibao226");
    }

    public static void exe2() throws Exception {
        exe("226jiancha");
    }

    public static void exe3() throws Exception {
        exe("216he226douzai");
    }

    public static void exe(String c) throws Exception {

        System.out.println(System.getProperty("user.dir"));
        //region
        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), c);
        FileHelper.listOnlyFilesByOneDeep(workspace, fileList);

        boolean hasDD = false;
        boolean hasFJ = false;
        boolean hasYXHD = false;
        List<DPSPZL> 店铺商品资料 = new CopyOnWriteArrayList<>();
        List<YXHDBMJL> 营销活动报名记录 = new CopyOnWriteArrayList<>();
        List<HDIDHDFJ> 活动ID活动类型分解 = new CopyOnWriteArrayList<>();

        List<FutureTask<List<DPSPZL>>> fp店铺商品资料 = new ArrayList<>();
        List<FutureTask<List<YXHDBMJL>>> fp营销活动报名记录 = new ArrayList<>();
        List<FutureTask<List<HDIDHDFJ>>> fp活动ID活动类型分解 = new ArrayList<>();
        for (File file : fileList) {
            String fn = file.getName();
            if (fn.contains("多多店铺商品资料")) {
                hasDD = true;
                FutureTask fp = new FutureTask<>(() -> {
                    System.out.println("开始读取 " + fn + " " + "的数据");
                    List<DPSPZL> list = ExcelUtil.read(file.getAbsolutePath(), DPSPZL.class);
                    System.out.println(fn + " 的数据读取完毕");
                    return list;
                });
                E.submit(fp);
                fp店铺商品资料.add(fp);
            } else if (fn.contains("活动ID和活动类型分解")) {
                hasFJ = true;

                FutureTask fp = new FutureTask<>(() -> {
                    System.out.println("开始读取 " + fn + " " + "的数据");
                    List<HDIDHDFJ> list = ExcelUtil.read(file.getAbsolutePath(), HDIDHDFJ.class);
                    System.out.println(fn + " 的数据读取完毕");
                    return list;
                });
                E.submit(fp);
                fp活动ID活动类型分解.add(fp);
            } else if (fn.contains("营销活动")) {
                hasYXHD = true;

                FutureTask fp = new FutureTask<>(() -> {
                    System.out.println("开始读取 " + fn + " " + "的数据");
                    List<YXHDBMJL> list = ExcelUtil.read(file.getAbsolutePath(), YXHDBMJL.class);
                    System.out.println(fn + " 的数据读取完毕");
                    return list;
                });
                E.submit(fp);
                fp营销活动报名记录.add(fp);
            }
        }
        if (hasDD && hasFJ && hasYXHD) {
        } else {
            System.out.println("文件不全，请检查无误后继续");
            return;
        }
        //endregion

        for (FutureTask<List<DPSPZL>> dd : fp店铺商品资料) {
            dd.get().forEach(d -> {
                d.set商品ID(d.get商品ID1());
                店铺商品资料.add(d);
            });
        }
        List<String> ctemp = Arrays.asList("百亿满减专区", "百亿秒杀", "百亿长期216折扣", "百亿自主降价", "月卡专享价7月", "日常百亿入口", "百亿短期22折扣");
        for (FutureTask<List<HDIDHDFJ>> dd : fp活动ID活动类型分解) {
            dd.get().forEach(d -> {
                if (ctemp.contains(d.get活动大类()))
                    活动ID活动类型分解.add(d);
            });
        }
        for (FutureTask<List<YXHDBMJL>> dd : fp营销活动报名记录) {
            dd.get().forEach(d -> {
                营销活动报名记录.add(d);
            });
        }

        List<FJSJ> fjsjList = null;
        List<RETSJSC> retsjscs = null;
        try (SqlSession session = MyBatisH2Util.getSqlSessionFactory().openSession()) {
            YXHDBMJLMapper yxm = session.getMapper(YXHDBMJLMapper.class);
            HDIDHDFJMapper hdmp = session.getMapper(HDIDHDFJMapper.class);
            DPSPZLMapper mapper = session.getMapper(DPSPZLMapper.class);
            RetMapper rerMapper = session.getMapper(RetMapper.class);

            for (DPSPZL d : 店铺商品资料) {
                mapper.insert(d);
            }
            for (HDIDHDFJ d : 活动ID活动类型分解) {
                hdmp.insert(d);
            }
            for (YXHDBMJL d : 营销活动报名记录) {
                yxm.insert(d);
            }

            fjsjList = rerMapper.selectFJSJ();
            fjsjList.forEach(d -> {
                d.set商品ID1(d.get商品ID());
                d.set活动ID1(d.get活动ID());
            });

            retsjscs = rerMapper.selectRETSJSC();
            retsjscs.forEach(d -> {
                d.set商品ID1(d.get商品ID());
            });
            session.commit();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号_");
        File out = new File(workspace, "out");
        out.mkdirs();

        String outFilename = simpleDateFormat.format(new Date()) + "最终数据.xlsx";
        File outfp = new File(out, outFilename);
        ExcelUtil.writeByBytes(RETSJSC.class, outfp.getAbsolutePath(), retsjscs, null, new NumberFormatStrategy("#,##0.00", RETSJSC.class));

        Map<String, List<FJSJ>> groupedItems = fjsjList.stream()
                .collect(Collectors.groupingBy(FJSJ::get活动大类));

        groupedItems.forEach((category, items) -> {
            System.out.println("类别: " + category);
            String oFilename = simpleDateFormat.format(new Date()) + category + "_分解数据.xlsx";
            File ofp = new File(out, oFilename);
//        ExcelUtil.writeByBytes(FJSJ.class, ofp.getAbsolutePath(), fjsjList, RedCellStyle.getRedCellStyle(), new NumberFormatStrategy("#,##0.00", TCBJB.class));
            ExcelUtil.writeByBytes(FJSJ.class, ofp.getAbsolutePath(), items, null, new NumberFormatStrategy("#,##0.00", FJSJ.class));

        });

        System.out.println("end");
    }

}
