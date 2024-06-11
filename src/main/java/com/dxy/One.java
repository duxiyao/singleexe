package com.dxy;

import com.dxy.data.*;
import com.dxy.util.ExcelUtil;
import com.dxy.util.TypeUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.dxy.Tasks.ISLOG;

public class One {
    public static final String PH = "盘货表";
    public static final String ERP = "ERP";
    public static final String SHOP1 = "强人满汾专卖店";
    public static final String SHOP2 = "强人女鞋旗舰店";
    public static final String SHOP3 = "强人旗舰店";
    public static final String SHOP4 = "强人舒尚专卖店";
    public static final String SHOP5 = "舒尚鞋类专营店";

    private String name;
    private File fileProduct;//商品管理
    private File fileGj;//营销工具
    private File fileHd;//营销活动
    private ExecutorService E;
    private FutureTask<List<Product>> fp;
    private FutureTask<List<YXGJ>> fgj;
    private FutureTask<List<YXHD>> fhd;
    private FutureTask<List<YXHD>[]> fbydm;

    public One(String name, ExecutorService e) {
        this.name = name;
        this.E = e;
    }

    public void filter(File file) {
        String fn = file.getName();
        if (!fn.contains(name)) {
            return;
        }
        if (fn.contains("商品管理")) {
            fileProduct = file;
        } else if (fn.contains("营销工具")) {
            fileGj = file;
        } else if (fn.contains("营销活动")) {
            fileHd = file;
        }
    }

    public boolean canUse() {
        return fileProduct != null && fileGj != null && fileHd != null;
    }

    public void read(File workspace) {
        fp = new FutureTask<>(() -> {
            System.out.println("开始读取" + name + " 商品管理" + "的数据");
            List<Product> list = ExcelUtil.read(fileProduct.getAbsolutePath(), Product.class);
            System.out.println(name + " 商品管理 的数据读取完毕");
            return list;
        });
        fgj = new FutureTask<>(() -> {
            System.out.println("开始读取" + name + " 营销工具" + "的数据");
            List<YXGJ> list = ExcelUtil.read(fileGj.getAbsolutePath(), YXGJ.class);
            System.out.println(name + " 营销工具 的数据读取完毕");
            return list;
        });
        fhd = new FutureTask<>(() -> {
            System.out.println("开始读取" + name + " 营销活动" + "的数据");
            List<YXHD> list = ExcelUtil.read(fileHd.getAbsolutePath(), YXHD.class);
            System.out.println(name + " 营销活动 的数据读取完毕");
            return list;
        });

        fbydm = new FutureTask<>(() -> {
            List<YXHD>[] ret = new ArrayList[2];
            List<YXHD> list = fhd.get();
            System.out.println("开始过滤" + name + " 百亿断码的数据");
            List<YXHD> by = new ArrayList<>();
            List<YXHD> dm = new ArrayList<>();
            list.forEach(yxhd -> {
                if (yxhd != null) {
                    String name = yxhd.getF0();
//                    try {
//                        if (name.contains("【断码清仓百亿补贴】")) {
//                            if (!(name.contains("卷") || name.contains("降价") || name.contains("限量") || name.contains("限时"))) {
//                                dm.add(yxhd);
//                            }
//                        }
//                    } catch (Exception e) {
//                        if (ISLOG) {
//                            e.printStackTrace();
//                        }
//                    }

                    try {
                        if (name.contains("【百亿补贴】")) {
//                            if (!(name.contains("卷") || name.contains("降价") || name.contains("限量") || name.contains("限时"))) {
                            if (name.contains("百亿补贴报名入口") || name.contains("百亿补贴爆单-全品类召回") || name.contains("24h免审")) {
                                by.add(yxhd);
                            }
                        }
                    } catch (Exception e) {
                        if (ISLOG) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            ret[0] = by;
            ret[1] = dm;
            System.out.println(name + " 百亿断码 的数据过滤完毕");

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy年MM月dd号");
            String outFilename = simpleDateFormat.format(new Date()) + "百亿中间表.xlsx";
            File outfp = new File(workspace, outFilename);
            ExcelUtil.writeWithTemplate(outfp.getAbsolutePath(), by);
            return ret;
        });

        E.submit(fp);
        E.submit(fgj);
        E.submit(fhd);
        E.submit(fbydm);
    }

    public Future<List<MData>> get(List<PanHuo> panHuos, List<ERP> erps) throws ExecutionException, InterruptedException {
        return E.submit(() -> {
            List<MData> ret = new ArrayList<>();
            List<MData> o = null;
            try {

                List<Product> products = fp.get();
                List<YXGJ> yxgjs = fgj.get();
                List<YXHD>[] bydms = fbydm.get();
                List<YXHD> by = bydms[0];
                List<YXHD> dm = bydms[1];

                Map<String, ERP> erpMap = erps.parallelStream().collect(Collectors.toMap(erp -> erp.getF5(), erp -> erp, (item1, item2) -> item1));
                Map<String, PanHuo> erpPanHuoMap = panHuos.parallelStream().collect(Collectors.toMap(panHuo -> panHuo.getF0(), panHuo -> panHuo, (item1, item2) -> item1));
                Map<String, YXHD> byMap = by.parallelStream().collect(Collectors.toMap(a -> a.getF3(), a -> a, (item1, item2) -> item1));
                Map<String, YXHD> dmMap = dm.parallelStream().collect(Collectors.toMap(a -> a.getF3(), a -> a, (item1, item2) -> item1));
                Map<String, YXGJ> gjMap = yxgjs.parallelStream().collect(Collectors.toMap(a -> a.getF1(), a -> a, (item1, item2) -> item1));


                o = products.parallelStream().map(new Function<Product, MData>() {
                    //                List<MData> o = products.stream().map(new Function<Product, MData>() {
                    @Override
                    public MData apply(Product p) {
                        MData m = new MData();
                        m.setF0(p.getF0());
                        m.setF1(p.getF1());
                        m.setF2(p.getF2());
                        m.setF3(p.getF3());
                        m.setF4("https://mobile.yangkeduo.com/goods.html?goods_id=" + p.getF1());
                        m.setF5(p.getF1());
                        m.setF6(p.getF13());
                        m.setF7(p.getF14());
                        m.setF8(p.getF15());
                        m.setF9(p.getF5() == null ? "-" : p.getF5());
                        m.setF10(p.getF8());

                        ERP erp = erpMap.get(p.getF1());

                        if (erp != null) {
                            m.setF11(erp.getF7());
                        }
                        if (p.getF19() != null) {
                            m.setF12("<table><img src=\"" + p.getF19() + "\" height=45 width=45></table>");
//                            m.setF12(p.getF19());
//                            try {
//                                m.setF12(new URL(p.getF19()));
//                            } catch (MalformedURLException e) {
//                                e.printStackTrace();
//                            }
                        }

                        try {
                            PanHuo panHuo = erpPanHuoMap.get(m.getF11());

                            if (panHuo != null) {
                                m.setF13(panHuo.getF2());
                                m.setF14(panHuo.getF1());

                                m.setF20(panHuo.getF7());
                                m.setF21(panHuo.getF5());
                                m.setF22(panHuo.getF4());
                                m.setF23(panHuo.getF9());
                                m.setF24(panHuo.getF8());
                                m.setF25(panHuo.getF6());
                                m.setF25x1(panHuo.getF10());
                            } else {
                                m.setF13("没匹配到");
                                m.setF14("没匹配到");

                                m.setF20("没匹配到");
                                m.setF21("没匹配到");
                                m.setF22("没匹配到");
                                m.setF23("没匹配到");
                                m.setF24("没匹配到");
                                m.setF25("没匹配到");
                            }
                        } catch (Exception e) {
//                            if (WorkerMain.ISLOG) {
//                                e.printStackTrace();
//                            }
                        }

                        try {
                            YXHD by = byMap.get(m.getF1());
                            if (by != null) {
                                m.setF15(by.getF12());
                                m.setF18(by.getF6());
                                m.setF36(by.getF1());
                            }
                        } catch (Exception e) {
//                            if (WorkerMain.ISLOG) {
//                                e.printStackTrace();
//                            }
                        }

                        try {
                            YXHD dm = dmMap.get(m.getF1());
                            if (dm != null) {
                                m.setF16(dm.getF12());
                                m.setF19(dm.getF6());
                                m.setF36(dm.getF1());
                            }
                        } catch (Exception e) {
//                            if (WorkerMain.ISLOG) {
//                                e.printStackTrace();
//                            }
                        }

                        m.setF17(TypeUtil.maxDoublePrice(m.getF18(), m.getF19()));

                        try {
                            YXGJ yxgj = gjMap.get(m.getF1());
                            m.setF26(yxgj.getF4());

                        } catch (Exception e) {
//                            if (WorkerMain.ISLOG) {
//                                e.printStackTrace();
//                            }
                        }

                        try {

                        } catch (Exception e) {
                        }

                        m.setF27(p.getF6());
                        m.setF28(p.getF7());
                        m.setF29(p.getF9());
                        m.setF30(p.getF10());
                        m.setF31(p.getF11());
                        m.setF32(p.getF12());
                        m.setF33(p.getF16());
                        m.setF34(p.getF17());
                        m.setF35(p.getF18());

                        return m;
                    }
                }).collect(Collectors.toList());

            } catch (Exception e) {
                if (ISLOG) {
                    e.printStackTrace();
                }
            }

            if (o != null) {
                ret.addAll(o);
            }
            return ret;
        });
    }
}
