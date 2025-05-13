package com.dxy;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dxy.data.*;
import com.dxy.util.*;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tasks {

    public static boolean ISLOG = true;
    static File filePanhuo = null;
    static File fileERP = null;
    static File bb = null;
    static File dd = null;
    static boolean canUse = true;

    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    static DecimalFormat decimalFormat4 = new DecimalFormat("#.####");
    public static final ExecutorService E = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    public static void exe5() throws ExecutionException, InterruptedException {
        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), "WK5");
        FileHelper.listOnlyFilesByOneDeep(workspace, fileList);
        if (fileList.size() == 0) {
            System.out.println("请先查看WK5里是否有要处理的文件");
            return;
        }
        List<Product> products = new ArrayList<>();
        List<MData> mDatas = new ArrayList<>();
        List<DGJ> dgjs = new ArrayList<>();

        List<TCBJB> rets = new ArrayList<>();
        List<String> ids = new ArrayList<>();

        fileList.forEach(file -> {
            String fn = file.getName();
            if (fn.contains("商品列表")) {
                List<Product> ds = ExcelUtil.read(file.getAbsolutePath(), Product.class);
                products.addAll(ds);
            } else if (fn.contains("待改价信息")) {
                // 读取 Excel 文件，跳过第一行（表头）
                List<DGJ> ds = EasyExcel.read(file)
                        .head(DGJ.class)
                        .headRowNumber(2) // 设置从第2行开始读取（索引从0开始）
                        .sheet()
                        .doReadSync();
                dgjs.addAll(ds);
            } else if (fn.contains("多多店铺商品资料")) {
                List<MData> ds = ExcelUtil.read(file.getAbsolutePath(), MData.class);
                mDatas.addAll(ds);
            }
        });
        if (products.size() == 0 || mDatas.size() == 0 || dgjs.size() == 0) {
            System.out.println("数据缺失，请检查无误后继续");
            return;
        }
        Map<String, Product> mapProduct = products.parallelStream().collect(Collectors.toMap(a -> a.getF1(), a -> a, (item1, item2) -> item1));
        Map<String, MData> mapMData = mDatas.parallelStream().collect(Collectors.toMap(a -> a.getF5(), a -> a, (item1, item2) -> item1));

        for (int i = 0; i < dgjs.size(); i++) {
            DGJ dgj = dgjs.get(i);

            String productId = dgj.getF1();
            if (productId == null || productId.trim().length() == 0) {
                continue;
            }
            if (ids.contains(productId)) {
                continue;
            }
            Product p = mapProduct.get(productId);
            MData m = mapMData.get(productId);


            TCBJB tcbjb = new TCBJB();
            try {
                tcbjb.setF1(productId);
                if (m != null) {
                    tcbjb.setF9(m.getF11());
                    tcbjb.setF10(m.getF12());
                    tcbjb.setF11(new BigDecimal(m.getF13()));
                    tcbjb.setF12(m.getF26());
                    tcbjb.setF13(m.getF37());
                    tcbjb.setF14(m.getF38());
                    tcbjb.setF41(m.getF14());
                    tcbjb.setF42(m.getF15());
                    tcbjb.setF43(m.getF18());
                    tcbjb.setF44(m.getF20());
                    tcbjb.setF45(m.getF21());
                    tcbjb.setF46(m.getF22());
                    tcbjb.setF47(m.getF23());
                    tcbjb.setF48(m.getF24());
                    tcbjb.setF49(m.getF25());
                    tcbjb.setF50(m.getF25x1());
                    tcbjb.setF51(m.getF35());
                    tcbjb.setF52(m.getF36());

                    tcbjb.setF53(m.getF27());
                    tcbjb.setF54(m.getF28());
                    tcbjb.setF55(m.getF29());
                    tcbjb.setF56(m.getF30());
                    tcbjb.setF57(m.getF31());
                    tcbjb.setF58(m.getF32());
                    tcbjb.setF59(m.getF33());
                    tcbjb.setF60(m.getF34());
                }
                if (p != null) {
                    tcbjb.setF0(p.getF0());
                    tcbjb.setF2(p.getF2());
                    tcbjb.setF3(p.getF3());
                    tcbjb.setF4("https://mobile.yangkeduo.com/goods.html?goods_id=" + productId);
                    tcbjb.setF5(productId);
                    tcbjb.setF6(TypeUtil.parseInt(p.getF13()));
                    tcbjb.setF7(TypeUtil.parseInt(p.getF14()));
                    tcbjb.setF8(TypeUtil.parseInt(p.getF15()));
                    tcbjb.setF26(p.getF5());
                    tcbjb.setF27(p.getF8());
//                    tcbjb.setF10(p.getF19());
                    tcbjb.setF51(p.getF18());
                    tcbjb.setF53(p.getF6());
                    tcbjb.setF54(p.getF7());
                    tcbjb.setF55(p.getF9());
                    tcbjb.setF56(p.getF10());
                    tcbjb.setF57(p.getF11());
                    tcbjb.setF58(p.getF12());
                    tcbjb.setF59(p.getF16());
                    tcbjb.setF60(p.getF17());
                }
                tcbjb.setF24(dgj.getF13());
                tcbjb.setF25(dgj.getF12());
                tcbjb.setF29(dgj.getF9());//当前单件提报价(元)
                String dqdjzk = dgj.getF10();
                if (dqdjzk != null) {
                    dqdjzk = dqdjzk.replace("2件", "").replace("折", "");
                }
                tcbjb.setF30(dqdjzk);//当前多件折扣
                tcbjb.setF31(dgj.getF3());//建议单件提报价(元)
                tcbjb.setF32(dgj.getF4());//降价后多件折扣
                //建议单件提报价(元)减去当前单件提报价(元)
                double f33 = TypeUtil.parseDouble(dgj.getF3()) - TypeUtil.parseDouble(dgj.getF9());
                tcbjb.setF33(decimalFormat.format(f33));
                //降价后多件折扣减去当前多件折扣
                double f34 = TypeUtil.parseDouble(dgj.getF4()) - TypeUtil.parseDouble(dqdjzk);
                tcbjb.setF34(decimalFormat.format(f34));
                //建议单件提报价(元)减去成本
                String chengbeng = "";
                if (m != null) {
                    chengbeng = m.getF13();
                } else {
//                    System.out.println("没有找到成本=>" + productId);
                    continue;
                }
                double f35 = TypeUtil.parseDouble(dgj.getF3()) - TypeUtil.parseDouble(chengbeng);
                tcbjb.setF35(decimalFormat.format(f35));
                //建议单件提报价(元)毛利/建议单件提报价(元)
                tcbjb.setF36(decimalFormat.format(f35 / TypeUtil.parseDouble(dgj.getF3())));
                //建议单件提报价(元)*降价后多件折扣*0.1
                double f37 = TypeUtil.parseDouble(dgj.getF3()) * TypeUtil.parseDouble(dgj.getF4()) * 0.1;
                tcbjb.setF37(decimalFormat.format(f37));
                //降价后多件折扣价减去成本
                double f38 = f37 - TypeUtil.parseDouble(chengbeng);
                tcbjb.setF38(decimalFormat.format(f38));
                //降价后多件折扣价毛利/降价后多件折扣价
                tcbjb.setF39(decimalFormat.format(f38 / f37));
                //用大于0和小于0等于0来作为判定是否有百亿标条件
                //"这个是用当天下午活动价减去百亿基础价，大于0单元格红色表示，没有百亿标。
                //如果用当天下午活动价减去百亿基础价，小于0或者等于0单元格用绿色表示。有百亿标"
//                double f40 = TypeUtil.parseDouble(m.getF10()) - TypeUtil.parseDouble(m.getF18());
                String byjcj = "";
                if (m != null) {
                    byjcj = m.getF18();
                } else {
//                    System.out.println("没有找到百亿基础价=>" + productId);
                    continue;
                }
                String hdj = "";
                if (p != null) {
                    hdj = p.getF8();
                } else {
//                    System.out.println("没有找到活动价=>" + productId);
                    continue;
                }
                double f40 = TypeUtil.parseDouble(hdj) - TypeUtil.parseDouble(byjcj);
                tcbjb.setF40(decimalFormat.format(f40));
                String f28 = "有百亿标";
                if (f40 > 0) {
                    f28 = "没有百亿标";//红色
                }
                tcbjb.setF28(f28);
            } catch (Exception e) {
                e.printStackTrace();
            }

            rets.add(tcbjb);
            ids.add(productId);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
        String outFilename = simpleDateFormat.format(new Date()) + "弹窗比价表.xlsx";
        File outfp = new File(workspace, outFilename);
//        ExcelUtil.writeByBytes(TCBJB.class, outfp.getAbsolutePath(), rets, RedCellStyle.getRedCellStyle(),new ImageWriteHandler());
//        ExcelUtil.writeByBytes(TCBJB.class, outfp.getAbsolutePath(), rets, RedCellStyle.getRedCellStyle(),null);
        ExcelUtil.writeByBytes(TCBJB.class, outfp.getAbsolutePath(), rets, RedCellStyle.getRedCellStyle(), new NumberFormatStrategy("#,##0.00", TCBJB.class));
        String s = "";
    }

    public static void exe4() throws ExecutionException, InterruptedException {
        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), "workspace4");
        FileHelper.listOnlyFilesByOneDeep(workspace, fileList);
        if (fileList.size() == 0) {
            System.out.println("请先查看workspace4里是否有要处理的文件");
            return;
        }
        List<BztgGjc> rets = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        fileList.forEach(file -> {
            try {
                String jsonstr = FileHelper.readFileContent(file);
                JSONObject jsonObject = new JSONObject(jsonstr);
                JSONArray jarr = jsonObject.optJSONObject("result").optJSONArray("keywords");
                for (int i1 = 0; i1 < jarr.length(); i1++) {
                    JSONObject jo = jarr.optJSONObject(i1);
                    String word = jo.optString("word");
                    if (!keys.contains(word)) {
                        BztgGjc bztgGjc = new BztgGjc();
                        bztgGjc.setF0(word);
                        bztgGjc.setF1(jo.optString("heat"));
                        bztgGjc.setF2(jo.optString("trend"));
                        bztgGjc.setF3(jo.optString("compete"));
                        bztgGjc.setF4(jo.optString("ctr"));

                        bztgGjc.setF5(String.valueOf(TypeUtil.parseFloat(jo.optString("avgBid")) / 1000f));
                        bztgGjc.setF6(String.valueOf(TypeUtil.parseFloat(jo.optString("suggestBid")) / 1000f));
                        rets.add(bztgGjc);
                        keys.add(word);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("处理失败" + file.getAbsolutePath());
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
        String outFilename = simpleDateFormat.format(new Date()) + "标准推广-关键词-添加关键词列表.xlsx";
        File outfp = new File(workspace, outFilename);
        ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), rets);
    }

    public static void exe3() throws ExecutionException, InterruptedException {

        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), "workspace3");
//        File workspace = new File("E:/", "workspace");
        FileHelper.listOnlyFilesByOneDeep(workspace, fileList);
        fileList.forEach(file -> {
            String fn = file.getName();
            if (fn.contains("订单")) {
                dd = file;
            } else if (fn.startsWith("销售报表")) {
                bb = file;
            }
        });

        if (dd == null || bb == null) {
            System.out.println("文件不全，请检查无误后继续");
            return;
        }


//        dd = new File(workspace, "订单_2023-10-20_09-00-03.10291624.10825900_1更改 (1).xlsx");
        FutureTask<List<DINGDAN>> futureTask = new FutureTask<>(() -> {
            System.out.println("开始读取" + dd.getName() + "的数据");
            List<DINGDAN> list = ExcelUtil.read(dd.getAbsolutePath(), DINGDAN.class);
            list = list.parallelStream().filter(dingdan -> {
                try {
                    boolean flag = dingdan.getF5() == null || dingdan.getF5().isEmpty();
                    if (dingdan.getF8() != null) {
                        try {
                            dingdan.setF8(decimalFormat.format(TypeUtil.parseDouble(dingdan.getF8())));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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


//            list = list.parallelStream().filter(dingdan -> {
//                try {
//                    boolean flag = (dingdan.getF1() != null && dingdan.getF1().contains("成功退款"));
//                    if (flag) {
//                        return false;
//                    } else {
//                        return true;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return true;
//            }).collect(Collectors.toList());
            System.out.println(dd.getName() + "的数据读取完毕");
            return list;
        });

        FutureTask<Map<String, Pair>> futureTask1 = new FutureTask<>(() -> {
            List<DINGDAN> list = futureTask.get();
            Map<String, List<DINGDAN>> mDingdanhao = list.parallelStream().collect(Collectors.groupingBy(DINGDAN::getF4));
            mDingdanhao.keySet().stream().forEach(s -> {
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

            //region 统计
            try {
                Map<String, List<DINGDAN>> mgroup = list.parallelStream()
                        .collect(Collectors.groupingBy(DINGDAN::getF1));
                Map<String, Double> mss = new HashMap<>();
                Map<String, Double> mprice = new HashMap<>();
                double sum = 0;
                for (String s : mgroup.keySet()) {
                    List<DINGDAN> ss = mgroup.get(s);
                    DoubleSummaryStatistics statsPrice = ss.parallelStream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF8())).summaryStatistics();
                    mss.put(s, Double.parseDouble(decimalFormat.format(statsPrice.getSum())));
                    mprice.put(s, Double.parseDouble(decimalFormat.format(statsPrice.getSum())));
                    sum += statsPrice.getSum();
                }
                sum = Double.parseDouble(decimalFormat.format(sum));
                double finalSum = sum;
                Map<String, String> ssRet = mss.entrySet().stream().map(new Function<Map.Entry<String, Double>, Map.Entry<String, Double>>() {
                    @Override
                    public Map.Entry<String, Double> apply(Map.Entry<String, Double> stringDoubleEntry) {
                        String k = stringDoubleEntry.getKey();
                        Double d = stringDoubleEntry.getValue();
                        stringDoubleEntry.setValue(d / finalSum);
                        return stringDoubleEntry;
                    }
                }).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        stringDoubleEntry -> decimalFormat4.format(100f * stringDoubleEntry.getValue()) + "%",
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));

                StringBuilder sb = new StringBuilder();
                sb.append("============\r\n");
                ssRet.entrySet().stream().forEach(new Consumer<Map.Entry<String, String>>() {
                    @Override
                    public void accept(Map.Entry<String, String> stringStringEntry) {
                        sb.append(stringStringEntry.getKey() + "\t" + mprice.get(stringStringEntry.getKey()) + "\t" + stringStringEntry.getValue() + "\r\n");
                    }
                });

                sb.append("总计：" + decimalFormat.format(sum));
                sb.append("\r\n============\r\n");
                System.err.println(sb);
                String s = "";
            } catch (Exception e) {
                e.printStackTrace();
            }
            //endregion

            Map<String, Pair> m = new HashMap<>();
            Map<String, Map<String, List<DINGDAN>>> mgroup = list.parallelStream()
                    .collect(Collectors.groupingBy(DINGDAN::getF5, Collectors.groupingBy(DINGDAN::getF1)));
            mgroup.keySet().stream().forEach(s -> {
                Map<String, List<DINGDAN>> mstatus = mgroup.get(s);
                List<DINGDAN> ss = new ArrayList<>();
                mstatus.values().forEach(dingdans -> {
                    dingdans.forEach(dd1 -> {
                        boolean flag = (dd1.getF1() != null && (dd1.getF1().contains("成功退款") || dd1.getF1().contains("等待退款")));
                        if (!flag) {
                            ss.add(dd1);
                        }
                    });
                });
                IntSummaryStatistics statsCnt = ss.parallelStream().mapToInt((x) -> TypeUtil.parseInt(x.getF9())).summaryStatistics();
                DoubleSummaryStatistics statsPrice = ss.parallelStream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF8())).summaryStatistics();

                m.put(s, new Pair<Double, Long>(statsPrice.getSum(), statsCnt.getSum()));
            });

            return m;
        });

//        File bb = new File(workspace, "销售报表输出格式xlsx.xlsx");
        FutureTask<List<XSBBTGSJ>> futureTask2 = new FutureTask<>(() -> {
            System.out.println("开始读取" + bb.getName() + "的数据");
            List<XSBBTGSJ> list = ExcelUtil.read(bb.getAbsolutePath(), XSBBTGSJ.class);
            System.out.println(bb.getName() + "的数据读取完毕");

            Map<String, Pair> m = futureTask1.get();

            //region 草稿
            List<A> caogao = new ArrayList<>();
            m.keySet().stream().forEach(s -> {
                A a = new A();
                a.setF0(s);
                Pair<Double, Long> p = m.get(s);
                try {
                    a.setF1(decimalFormat.format(p.getKey()));
                    a.setF2(decimalFormat.format(p.getValue()));
//                a.setF1(p.getKey().toString());
//                a.setF2(p.getValue().toString());
                    caogao.add(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
            String outFilename = simpleDateFormat.format(new Date()) + "草稿.xlsx";
            File outfp = new File(workspace, outFilename);
            ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), caogao);
            //endregion 草稿

            List<XSBBTGSJ> newList = list.parallelStream().filter(xsbb -> {
                boolean flag = m.containsKey(xsbb.getF9());
                return flag;
            }).collect(Collectors.toList());

            Map<String, QZTG> mapTGQ = new HashMap<>();
            Map<String, BZTG> mapTGB = new HashMap<>();
            try {
                //todo 合并数据推广
                fileList.forEach(file -> {
                    String fn = file.getName();
                    if (fn.startsWith("全站推广_账户_分级详情_商品")) {
                        List<QZTG> tmp = ExcelUtil.read(file.getAbsolutePath(), QZTG.class);
                        tmp.forEach(new Consumer<QZTG>() {
                            @Override
                            public void accept(QZTG qztg) {
                                mapTGQ.put(qztg.getF1(), qztg);
                            }
                        });
                    } else if (fn.startsWith("标准推广_账户_分级详情_商品")) {
                        List<BZTG> tmp = ExcelUtil.read(file.getAbsolutePath(), BZTG.class);
                        tmp.forEach(new Consumer<BZTG>() {
                            @Override
                            public void accept(BZTG bztg) {
                                mapTGB.put(bztg.getF1(), bztg);
                            }
                        });
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            newList.forEach(xsbb -> {
                Pair<Double, Long> p = m.get(xsbb.getF9());
                try {
                    xsbb.setF3(xsbb.getF9());
                    if (p.getValue() != 0) {
                        xsbb.setF13(decimalFormat.format(p.getValue()));
                    }
                    if (p.getKey() != 0) {
                        xsbb.setF14(decimalFormat.format(p.getKey()));
                    }
                    xsbb.setF15(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF13()) * TypeUtil.parseFloat(xsbb.getF21())));
                    xsbb.setF16(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF14()) - TypeUtil.parseFloat(xsbb.getF15())));

                    if (p.getValue() != 0) {
                        xsbb.setF17(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF16()) / TypeUtil.parseFloat(xsbb.getF13())));
                        xsbb.setF18(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF14()) / TypeUtil.parseFloat(xsbb.getF13())));
                    }
                    if (p.getKey() != 0) {
                        xsbb.setF19(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF16()) / TypeUtil.parseFloat(xsbb.getF14())));
                    }
                    xsbb.setF20(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF18()) - TypeUtil.parseFloat(xsbb.getF8())));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    //todo 合并数据推广
                    QZTG qztg = mapTGQ.get(xsbb.getF9());
                    BZTG bztg = mapTGB.get(xsbb.getF9());
                    if (qztg != null) {
                        xsbb.setQ(qztg.getF6());//全站总花费(元)
                        xsbb.setR(qztg.getF9());//全站实际投产比
                        xsbb.setS(qztg.getF10());//全站成交笔数
                        xsbb.setT(qztg.getF11());//全站每笔成交花费(元)
                        xsbb.setU(qztg.getF12());//全站每笔成交金额(元)
                        xsbb.setV(qztg.getF15());//全站直接成交笔数
                        xsbb.setW(qztg.getF16());//全站间接成交笔数
                    } else {
                        xsbb.setQ("找不到");//全站总花费(元)
                        xsbb.setR("找不到");//全站实际投产比
                        xsbb.setS("找不到");//全站成交笔数
                        xsbb.setT("找不到");//全站每笔成交花费(元)
                        xsbb.setU("找不到");//全站每笔成交金额(元)
                        xsbb.setV("找不到");//全站直接成交笔数
                        xsbb.setW("找不到");//全站间接成交笔数
                    }
                    if (bztg != null) {
                        xsbb.setX(bztg.getF8());//标准总花费(元)
                        xsbb.setY(bztg.getF13());//标准实际投产比
                        xsbb.setZ(bztg.getF11());//标准成交笔数
                        xsbb.setAa(bztg.getF10());//标准每笔成交花费(元)
                        xsbb.setAb(bztg.getF28());//标准直接成交笔数
                        xsbb.setAc(bztg.getF29());//标准间接成交笔数
                        xsbb.setAd(bztg.getF14());//标准每笔成交金额(元)
                    } else {
                        xsbb.setX("找不到");//标准总花费(元)
                        xsbb.setY("找不到");//标准实际投产比
                        xsbb.setZ("找不到");//标准成交笔数
                        xsbb.setAa("找不到");//标准每笔成交花费(元)
                        xsbb.setAb("找不到");//标准直接成交笔数
                        xsbb.setAc("找不到");//标准间接成交笔数
                        xsbb.setAd("找不到");//标准每笔成交金额(元)
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            return newList;
        });

        E.submit(futureTask);
        E.submit(futureTask1);
        E.submit(futureTask2);


        List<XSBBTGSJ> rets = new ArrayList<>();
        rets.addAll(futureTask2.get());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");

//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -1);
//        Date d = cal.getTime();

        Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);

        String outFilename = simpleDateFormat.format(d) + "销售报表加上推广数据输出.xlsx";
        File outfp = new File(workspace, outFilename);
//        ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), rets);


        DoubleSummaryStatistics statsPrice = rets.parallelStream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF14())).summaryStatistics();
        double sum = statsPrice.getSum();
        Map<String, Double> m = rets.parallelStream().collect(Collectors.groupingBy(XSBBTGSJ::getF33, Collectors.summingDouble(XSBBTGSJ::getDoubleF14)));

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        //对m的value进行降序排列
        m.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(e -> {
                    //判断占比大于等于15%才放进sortedMap
                    if (e.getValue() / sum >= 0.15) {
                        sortedMap.put(e.getKey(), e.getValue());
                    }
                });

        List<List<XSBBTGSJ>> sheetDatas = new LinkedList<>();
        sheetDatas.add(rets);
        sortedMap.keySet().forEach(s -> sheetDatas.add(rets.parallelStream().filter(xsbb -> s.equals(xsbb.getF33())).collect(Collectors.toList())));

        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(outfp.getAbsolutePath()).build();
            for (int i = 0; i < sheetDatas.size(); i++) {
                List<XSBBTGSJ> data = sheetDatas.get(i);
                String sheetName = i + "";
                if (i > 0) {
                    try {
                        sheetName = i + "_" + data.get(0).getF33();
                    } catch (Exception e) {
                    }
                }
                WriteSheet writeSheet = EasyExcel.writerSheet(i, sheetName).head(XSBBTGSJ.class).build();
                excelWriter.write(data, writeSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

        E.submit(() -> {
            try {
                VersionCtlUtil.up(outfp.getAbsolutePath());
            } catch (Exception e) {
            }
        });
//        E.shutdown();

        bb = null;
        dd = null;
        System.out.println("end");

    }

    public static void exe2() throws ExecutionException, InterruptedException {

        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), "workspace2");
//        File workspace = new File("E:/", "workspace");
        FileHelper.listOnlyFilesByOneDeep(workspace, fileList);
        fileList.forEach(file -> {
            String fn = file.getName();
            if (fn.contains("订单")) {
                dd = file;
            } else if (fn.startsWith("销售报表")) {
                bb = file;
            }
        });

        if (dd == null || bb == null) {
            System.out.println("文件不全，请检查无误后继续");
            return;
        }


//        dd = new File(workspace, "订单_2023-10-20_09-00-03.10291624.10825900_1更改 (1).xlsx");
        FutureTask<List<DINGDAN>> futureTask = new FutureTask<>(() -> {
            System.out.println("开始读取" + dd.getName() + "的数据");
            List<DINGDAN> list = ExcelUtil.read(dd.getAbsolutePath(), DINGDAN.class);
            list = list.parallelStream().filter(dingdan -> {
                try {
                    boolean flag = dingdan.getF5() == null || dingdan.getF5().isEmpty();
                    if (dingdan.getF8() != null) {
                        try {
                            dingdan.setF8(decimalFormat.format(TypeUtil.parseDouble(dingdan.getF8())));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
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


//            list = list.parallelStream().filter(dingdan -> {
//                try {
//                    boolean flag = (dingdan.getF1() != null && dingdan.getF1().contains("成功退款"));
//                    if (flag) {
//                        return false;
//                    } else {
//                        return true;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return true;
//            }).collect(Collectors.toList());
            System.out.println(dd.getName() + "的数据读取完毕");
            return list;
        });

        FutureTask<Map<String, Pair>> futureTask1 = new FutureTask<>(() -> {
            List<DINGDAN> list = futureTask.get();
            Map<String, List<DINGDAN>> mDingdanhao = list.parallelStream().collect(Collectors.groupingBy(DINGDAN::getF4));
            mDingdanhao.keySet().stream().forEach(s -> {
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

            //region 统计
            try {
                Map<String, List<DINGDAN>> mgroup = list.parallelStream()
                        .collect(Collectors.groupingBy(DINGDAN::getF1));
                Map<String, Double> mss = new HashMap<>();
                Map<String, Double> mprice = new HashMap<>();
                double sum = 0;
                for (String s : mgroup.keySet()) {
                    List<DINGDAN> ss = mgroup.get(s);
                    DoubleSummaryStatistics statsPrice = ss.parallelStream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF8())).summaryStatistics();
                    mss.put(s, Double.parseDouble(decimalFormat.format(statsPrice.getSum())));
                    mprice.put(s, Double.parseDouble(decimalFormat.format(statsPrice.getSum())));
                    sum += statsPrice.getSum();
                }
                sum = Double.parseDouble(decimalFormat.format(sum));
                double finalSum = sum;
                Map<String, String> ssRet = mss.entrySet().stream().map(new Function<Map.Entry<String, Double>, Map.Entry<String, Double>>() {
                    @Override
                    public Map.Entry<String, Double> apply(Map.Entry<String, Double> stringDoubleEntry) {
                        String k = stringDoubleEntry.getKey();
                        Double d = stringDoubleEntry.getValue();
                        stringDoubleEntry.setValue(d / finalSum);
                        return stringDoubleEntry;
                    }
                }).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        stringDoubleEntry -> decimalFormat4.format(100f * stringDoubleEntry.getValue()) + "%",
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));

                StringBuilder sb = new StringBuilder();
                sb.append("============\r\n");
                ssRet.entrySet().stream().forEach(new Consumer<Map.Entry<String, String>>() {
                    @Override
                    public void accept(Map.Entry<String, String> stringStringEntry) {
                        sb.append(stringStringEntry.getKey() + "\t" + mprice.get(stringStringEntry.getKey()) + "\t" + stringStringEntry.getValue() + "\r\n");
                    }
                });

                sb.append("总计：" + decimalFormat.format(sum));
                sb.append("\r\n============\r\n");
                System.err.println(sb);
                String s = "";
            } catch (Exception e) {
                e.printStackTrace();
            }
            //endregion

            Map<String, Pair> m = new HashMap<>();
            Map<String, Map<String, List<DINGDAN>>> mgroup = list.parallelStream()
                    .collect(Collectors.groupingBy(DINGDAN::getF5, Collectors.groupingBy(DINGDAN::getF1)));
            mgroup.keySet().stream().forEach(s -> {
                Map<String, List<DINGDAN>> mstatus = mgroup.get(s);
                List<DINGDAN> ss = new ArrayList<>();
                mstatus.values().forEach(dingdans -> {
                    dingdans.forEach(dd1 -> {
                        boolean flag = (dd1.getF1() != null && (dd1.getF1().contains("成功退款") || dd1.getF1().contains("等待退款")));
                        if (!flag) {
                            ss.add(dd1);
                        }
                    });
                });
                IntSummaryStatistics statsCnt = ss.parallelStream().mapToInt((x) -> TypeUtil.parseInt(x.getF9())).summaryStatistics();
                DoubleSummaryStatistics statsPrice = ss.parallelStream().mapToDouble((x) -> TypeUtil.parseDouble(x.getF8())).summaryStatistics();

                m.put(s, new Pair<Double, Long>(statsPrice.getSum(), statsCnt.getSum()));
            });

            return m;
        });

//        File bb = new File(workspace, "销售报表输出格式xlsx.xlsx");
        FutureTask<List<XSBB>> futureTask2 = new FutureTask<>(() -> {
            System.out.println("开始读取" + bb.getName() + "的数据");
            List<XSBB> list = ExcelUtil.read(bb.getAbsolutePath(), XSBB.class);
            System.out.println(bb.getName() + "的数据读取完毕");

            Map<String, Pair> m = futureTask1.get();

            //region 草稿
            List<A> caogao = new ArrayList<>();
            m.keySet().stream().forEach(s -> {
                A a = new A();
                a.setF0(s);
                Pair<Double, Long> p = m.get(s);
                try {
                    a.setF1(decimalFormat.format(p.getKey()));
                    a.setF2(decimalFormat.format(p.getValue()));
//                a.setF1(p.getKey().toString());
//                a.setF2(p.getValue().toString());
                    caogao.add(a);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
            String outFilename = simpleDateFormat.format(new Date()) + "草稿.xlsx";
            File outfp = new File(workspace, outFilename);
            ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), caogao);
            //endregion 草稿

            List<XSBB> newList = list.parallelStream().filter(xsbb -> {
                boolean flag = m.containsKey(xsbb.getF9());
                return flag;
            }).collect(Collectors.toList());
            newList.forEach(xsbb -> {
                Pair<Double, Long> p = m.get(xsbb.getF9());
                try {
                    xsbb.setF3(xsbb.getF9());
                    if (p.getValue() != 0) {
                        xsbb.setF13(decimalFormat.format(p.getValue()));
                    }
                    if (p.getKey() != 0) {
                        xsbb.setF14(decimalFormat.format(p.getKey()));
                    }
                    xsbb.setF15(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF13()) * TypeUtil.parseFloat(xsbb.getF21())));
                    xsbb.setF16(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF14()) - TypeUtil.parseFloat(xsbb.getF15())));

                    if (p.getValue() != 0) {
                        xsbb.setF17(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF16()) / TypeUtil.parseFloat(xsbb.getF13())));
                        xsbb.setF18(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF14()) / TypeUtil.parseFloat(xsbb.getF13())));
                    }
                    if (p.getKey() != 0) {
                        xsbb.setF19(decimalFormat.format(TypeUtil.parseFloat(xsbb.getF16()) / TypeUtil.parseFloat(xsbb.getF14())));
                    }
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


        List<XSBB> rets = new ArrayList<>();
        rets.addAll(futureTask2.get());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");

//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -1);
//        Date d = cal.getTime();

        Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);

        String outFilename = simpleDateFormat.format(d) + "销售报表输出.xlsx";
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

        E.submit(() -> {
            try {
                VersionCtlUtil.up(outfp.getAbsolutePath());
            } catch (Exception e) {
            }
        });
//        E.shutdown();

        bb = null;
        dd = null;
        System.out.println("end");

    }

    public static void exe1() throws Exception {

        System.out.println(System.getProperty("user.dir"));
        //region
        List<One> ones = new ArrayList<>();
        ones.add(new One(One.SHOP1, E));
        ones.add(new One(One.SHOP2, E));
        ones.add(new One(One.SHOP3, E));
        ones.add(new One(One.SHOP4, E));
        ones.add(new One(One.SHOP5, E));

        List<File> fileList = new ArrayList<>();
        File workspace = new File(System.getProperty("user.dir"), "workspace1");
//        File workspace = new File("e:\\1\\", "workspace");
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
                a.read(workspace);
                list.add(a.get(futureTask.get(), ferp.get()));
            } catch (Exception e) {
                if (ISLOG) {
                    e.printStackTrace();
                }
            }
        });

        List<MData> rets = new ArrayList<>();
        list.forEach(listFuture -> {
            try {
                rets.addAll(listFuture.get());
            } catch (Exception e) {
                if (ISLOG) {
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
        E.submit(() -> {
            try {
                VersionCtlUtil.up("");
            } catch (Exception e) {
            }
        });
//        E.shutdownNow();
        filePanhuo = null;
        fileERP = null;
        canUse = true;
        System.out.println("end");
    }
}
