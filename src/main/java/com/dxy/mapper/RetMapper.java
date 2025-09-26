package com.dxy.mapper;

import com.dxy.data.FJSJ;
import com.dxy.data.RETSJSC;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RetMapper {
    /**
     *

     select 商品名称 ,商品ID ,商品ID ,a.活动ID ,a.活动ID,活动大类 ,b.活动名称 ,商品图片 ,类目名称 ,提交时间 ,活动价 ,活动库存 ,活动时间 ,活动状态 ,累计销量 ,库存,失效原因    from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID

     WITH temp AS (
     select 商品名称 ,商品ID ,a.活动ID,活动大类 ,b.活动名称 ,商品图片 ,类目名称 ,提交时间 ,活动价 ,活动库存 ,活动时间 ,活动状态 ,累计销量 ,库存,失效原因    from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID order by 活动大类
     )
     SELECT * FROM temp ;

     * 营销活动分解完以后==========这个是输出文件
     */
    @Select("select 商品名称 ,商品ID ,a.活动ID,活动大类 ,b.活动名称 ,商品图片 ,类目名称 ,提交时间 ,活动价 ,活动库存 ,活动时间 ,活动状态 ,累计销量 ,库存,失效原因    from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID order by 活动大类")
    List<FJSJ> selectFJSJ();


    /*


     WITH 分解完的数据 AS (
     select 商品名称 ,商品ID ,a.活动ID,活动大类 ,b.活动名称 ,商品图片 ,类目名称 ,提交时间 ,活动价 ,活动库存 ,活动时间 ,活动状态 ,累计销量 ,库存,失效原因    from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID
     )
     SELECT * FROM 分解完的数据 limit 5 ;

      WITH 有百亿基础价的 AS (
     SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加'  )
     SELECT * FROM 有百亿基础价的 limit 5 ;

     SELECT * FROM 有百亿基础价的 a inner join 分解完的数据 b on a.商品ID=b.商品ID limit 5 ;


     SELECT * FROM (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a
      left join
      (select 商品名称 ,商品ID ,a.活动ID,活动大类 ,b.活动名称 ,商品图片 ,类目名称 ,提交时间 ,活动价 ,活动库存 ,活动时间 ,活动状态 ,累计销量 ,库存,失效原因    from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID) b
      on a.商品ID=b.商品ID limit 5 ;





    SELECT a.商品ID,
     max(CASE WHEN (CAST(a._1件实收最低场景商家预估实收价格 AS NUMERIC) - CAST(a.百亿基础价 AS NUMERIC))   = 0 THEN '只有百亿基础价，没有叠加其他促销' ELSE NULL END) as 是否有百亿促销,
     max(CASE WHEN b.活动大类 = '日常百亿入口' THEN FORMATDATETIME(PARSEDATETIME(b.提交时间, 'yyyy-MM-dd HH:mm:ss'), 'yyyy-MM-dd') ELSE NULL END) as 百亿基础价提报时间,
     max(CASE WHEN b.活动大类 = '百亿满减专区' THEN b.活动名称 ELSE NULL END) as 百亿满减专区,
     max(CASE WHEN b.活动大类 = '百亿自主降价' THEN b.活动价 ELSE NULL END) as 百亿自主降价自主降价,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' THEN b.活动名称 ELSE NULL END) as 百亿长期216折扣活动名字,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8.5%' THEN '0.85'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8%' THEN '0.8'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%长期%' THEN '0.9'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%9.5%' THEN '0.95'
      ELSE NULL END) as 百亿长期216折扣活动力度,

     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动名称 ELSE NULL END) as _226品牌限时折扣名称,

     max(CASE WHEN b.活动大类 = '百亿短期22折扣' AND CAST(a.库存 AS NUMERIC)<=10 THEN '0.95'
              WHEN b.活动大类 = '百亿短期22折扣' AND CAST(a.库存 AS NUMERIC)>10 AND CAST(a.库存 AS NUMERIC)<30 AND (b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8%' )  THEN '0.9'
              WHEN b.活动大类 = '百亿短期22折扣' AND b.活动大类 = '百亿长期216折扣'  THEN 'all'
     ELSE NULL END) as _226品牌限时折扣力度,

     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动价 ELSE NULL END) as _226品牌限时折扣活动价,

     max(CASE WHEN b.活动大类 = '百亿秒杀' THEN b.活动价 ELSE NULL END) as 百亿秒杀,
     max(CASE WHEN b.活动大类 = '月卡专享价7月' THEN b.活动名称 ELSE NULL END) as 月卡专享价7月,
     FROM (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a
      left join
      (select 商品ID ,a.活动ID,活动大类 ,b.活动名称 ,b.提交时间,b.活动价     from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID) b
      on a.商品ID=b.商品ID
        group by a.商品ID





    SELECT 商品ID,百亿长期216折扣活动名字,_226品牌限时折扣名称,
    (CASE WHEN _226品牌限时折扣名称 is not NULL AND CAST(库存 AS NUMERIC)<=10  THEN '0.95'
          WHEN _226品牌限时折扣名称 is not NULL AND  CAST(库存 AS NUMERIC)>10 AND CAST(库存 AS NUMERIC)<30 AND 百亿长期216折扣活动名字 like '%8%' THEN '0.9'
          WHEN _226品牌限时折扣名称 is not NULL AND 百亿长期216折扣活动名字 is not NULL THEN 百亿长期216折扣活动力度
     ELSE NULL END) as lidu,
    from(
    SELECT a.商品ID,a.库存,
     max(CASE WHEN (CAST(a._1件实收最低场景商家预估实收价格 AS NUMERIC) - CAST(a.百亿基础价 AS NUMERIC))   = 0 THEN '只有百亿基础价，没有叠加其他促销' ELSE NULL END) as 是否有百亿促销,
     max(CASE WHEN b.活动大类 = '日常百亿入口' THEN FORMATDATETIME(PARSEDATETIME(b.提交时间, 'yyyy-MM-dd HH:mm:ss'), 'yyyy-MM-dd') ELSE NULL END) as 百亿基础价提报时间,
     max(CASE WHEN b.活动大类 = '百亿满减专区' THEN b.活动名称 ELSE NULL END) as 百亿满减专区,
     max(CASE WHEN b.活动大类 = '百亿自主降价' THEN b.活动价 ELSE NULL END) as 百亿自主降价自主降价,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' THEN b.活动名称 ELSE NULL END) as 百亿长期216折扣活动名字,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8.5%' THEN '0.85'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8%' THEN '0.8'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%长期%' THEN '0.9'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%9.5%' THEN '0.95'
      ELSE NULL END) as 百亿长期216折扣活动力度,

     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动名称 ELSE NULL END) as _226品牌限时折扣名称,
     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动价 ELSE NULL END) as _226品牌限时折扣活动价,

     max(CASE WHEN b.活动大类 = '百亿秒杀' THEN b.活动价 ELSE NULL END) as 百亿秒杀,
     max(CASE WHEN b.活动大类 = '月卡专享价7月' THEN b.活动名称 ELSE NULL END) as 月卡专享价7月,
     FROM (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a
      left join
      (select 商品ID ,a.活动ID,活动大类 ,b.活动名称 ,b.提交时间,b.活动价     from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID) b
      on a.商品ID=b.商品ID
        group by a.商品ID,a.库存
    )





    SELECT 商品ID,是否有百亿促销,百亿基础价提报时间,百亿满减专区,百亿自主降价自主降价,百亿长期216折扣活动名字,百亿长期216折扣活动力度,_226品牌限时折扣名称,
    (CASE WHEN _226品牌限时折扣名称 is not NULL AND CAST(库存 AS NUMERIC)<=10  THEN '0.95'
          WHEN _226品牌限时折扣名称 is not NULL AND  CAST(库存 AS NUMERIC)>10 AND CAST(库存 AS NUMERIC)<30 AND 百亿长期216折扣活动名字 like '%8%' THEN '0.9'
          WHEN _226品牌限时折扣名称 is not NULL AND 百亿长期216折扣活动名字 is not NULL THEN 百亿长期216折扣活动力度
     ELSE NULL END) as _226品牌限时折扣力度,_226品牌限时折扣活动价,百亿秒杀,月卡专享价7月
    from(
    SELECT a.商品ID,a.库存,
     max(CASE WHEN (CAST(a._1件实收最低场景商家预估实收价格 AS NUMERIC) - CAST(a.百亿基础价 AS NUMERIC))   = 0 THEN '只有百亿基础价，没有叠加其他促销' ELSE NULL END) as 是否有百亿促销,
     max(CASE WHEN b.活动大类 = '日常百亿入口' THEN FORMATDATETIME(PARSEDATETIME(b.提交时间, 'yyyy-MM-dd HH:mm:ss'), 'yyyy-MM-dd') ELSE NULL END) as 百亿基础价提报时间,
     max(CASE WHEN b.活动大类 = '百亿满减专区' THEN b.活动名称 ELSE NULL END) as 百亿满减专区,
     max(CASE WHEN b.活动大类 = '百亿自主降价' THEN b.活动价 ELSE NULL END) as 百亿自主降价自主降价,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' THEN b.活动名称 ELSE NULL END) as 百亿长期216折扣活动名字,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8.5%' THEN '0.85'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8%' THEN '0.8'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%长期%' THEN '0.9'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%9.5%' THEN '0.95'
      ELSE NULL END) as 百亿长期216折扣活动力度,

     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动名称 ELSE NULL END) as _226品牌限时折扣名称,
     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动价 ELSE NULL END) as _226品牌限时折扣活动价,
     max(CASE WHEN b.活动大类 = '百亿秒杀' THEN b.活动价 ELSE NULL END) as 百亿秒杀,
     max(CASE WHEN b.活动大类 = '月卡专享价7月' THEN b.活动名称 ELSE NULL END) as 月卡专享价7月,
     FROM (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a
      left join
      (select 商品ID ,a.活动ID,活动大类 ,b.活动名称 ,b.提交时间,b.活动价     from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID) b
      on a.商品ID=b.商品ID
        group by a.商品ID,a.库存
    )




    select 商品名称 ,a.商品ID ,店铺名称 ,商品编码 ,商品链接 ,a.商品ID1 ,库存 ,累计销量, _30日销量 ,资源位, 活动价 ,ERP编码 ,图片, 成本, 全网价格_持续更新_ ,百亿资源位失效, 百亿基础价 ,新客立减_元_ ,
    是否有百亿促销,百亿基础价提报时间,百亿满减专区,百亿自主降价自主降价,百亿长期216折扣活动名字,百亿长期216折扣活动力度,_226品牌限时折扣名称,_226品牌限时折扣力度,_226品牌限时折扣活动价,百亿秒杀,月卡专享价7月,
    细分属性, 属性分类, 男女分类 ,新款_动销款_清仓款, 季节 ,供应商 ,_24年款式店铺定位 ,满两件折扣 ,活动ID ,惊喜券 ,商品立减券 ,类目名称 ,类目全称 ,拼团价 ,单买价, 参考价 ,收藏数 ,创建时间 ,商品状态, _1件实收最低场景商家预估实收价格, _2件实收最低场景商家预估实收价格, 券前价, 价格类型 ,商家出资常规优惠额度, 商家出资常规优惠 ,是否预售
    from (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a left join
    (
    SELECT 商品ID,是否有百亿促销,百亿基础价提报时间,百亿满减专区,百亿自主降价自主降价,百亿长期216折扣活动名字,百亿长期216折扣活动力度,_226品牌限时折扣名称,
    (CASE WHEN _226品牌限时折扣名称 is not NULL AND CAST(库存 AS NUMERIC)<=10  THEN '0.95'
          WHEN _226品牌限时折扣名称 is not NULL AND  CAST(库存 AS NUMERIC)>10 AND CAST(库存 AS NUMERIC)<30 AND 百亿长期216折扣活动名字 like '%8%' THEN '0.9'
          WHEN _226品牌限时折扣名称 is not NULL AND 百亿长期216折扣活动名字 is not NULL THEN 百亿长期216折扣活动力度
     ELSE NULL END) as _226品牌限时折扣力度,_226品牌限时折扣活动价,百亿秒杀,月卡专享价7月
    from(
    SELECT a.商品ID,a.库存,
     max(CASE WHEN (CAST(a._1件实收最低场景商家预估实收价格 AS NUMERIC) - CAST(a.百亿基础价 AS NUMERIC))   = 0 THEN '只有百亿基础价，没有叠加其他促销' ELSE NULL END) as 是否有百亿促销,
     max(CASE WHEN b.活动大类 = '日常百亿入口' THEN FORMATDATETIME(PARSEDATETIME(b.提交时间, 'yyyy-MM-dd HH:mm:ss'), 'yyyy-MM-dd') ELSE NULL END) as 百亿基础价提报时间,
     max(CASE WHEN b.活动大类 = '百亿满减专区' THEN b.活动名称 ELSE NULL END) as 百亿满减专区,
     max(CASE WHEN b.活动大类 = '百亿自主降价' THEN b.活动价 ELSE NULL END) as 百亿自主降价自主降价,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' THEN b.活动名称 ELSE NULL END) as 百亿长期216折扣活动名字,
     max(CASE WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8.5%' THEN '0.85'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8%' THEN '0.8'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%长期%' THEN '0.9'
              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%9.5%' THEN '0.95'
      ELSE NULL END) as 百亿长期216折扣活动力度,

     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动名称 ELSE NULL END) as _226品牌限时折扣名称,
     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动价 ELSE NULL END) as _226品牌限时折扣活动价,
     max(CASE WHEN b.活动大类 = '百亿秒杀' THEN b.活动价 ELSE NULL END) as 百亿秒杀,
     max(CASE WHEN b.活动大类 = '月卡专享价7月' THEN b.活动名称 ELSE NULL END) as 月卡专享价7月,
     FROM (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a
      left join
      (select 商品ID ,a.活动ID,活动大类 ,b.活动名称 ,b.提交时间,b.活动价     from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID) b
      on a.商品ID=b.商品ID
        group by a.商品ID,a.库存
    )
    ) b  on a.商品ID=b.商品ID

    * */
    @Select("select 商品名称 ,a.商品ID ,店铺名称 ,商品编码 ,商品链接 ,a.商品ID1 ,库存 ,累计销量, _30日销量 ,资源位, 活动价 ,ERP编码 ,图片, 成本, 全网价格_持续更新_ ,百亿资源位失效, 百亿基础价 ,新客立减_元_ ,\n" +
            "    是否有百亿促销,百亿基础价提报时间,百亿满减专区,百亿自主降价自主降价,百亿长期216折扣活动名字,百亿长期216折扣活动力度,_226品牌限时折扣名称,_226品牌限时折扣力度,_226品牌限时折扣活动价,百亿秒杀,月卡专享价7月,\n" +
            "    细分属性, 属性分类, 男女分类 ,新款_动销款_清仓款, 季节 ,供应商 ,_24年款式店铺定位 ,满两件折扣 ,活动ID ,惊喜券 ,商品立减券 ,类目名称 ,类目全称 ,拼团价 ,单买价, 参考价 ,收藏数 ,创建时间 ,商品状态, _1件实收最低场景商家预估实收价格, _2件实收最低场景商家预估实收价格, 券前价, 价格类型 ,商家出资常规优惠额度, 商家出资常规优惠 ,是否预售\n" +
            "    from (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a left join\n" +
            "    (\n" +
            "    SELECT 商品ID,是否有百亿促销,百亿基础价提报时间,百亿满减专区,百亿自主降价自主降价,百亿长期216折扣活动名字,百亿长期216折扣活动力度,_226品牌限时折扣名称,\n" +
            "    (CASE WHEN _226品牌限时折扣名称 is not NULL AND CAST(库存 AS NUMERIC)<=10  THEN '0.95'\n" +
            "          WHEN _226品牌限时折扣名称 is not NULL AND  CAST(库存 AS NUMERIC)>10 AND CAST(库存 AS NUMERIC)<30 AND 百亿长期216折扣活动名字 like '%8%' THEN '0.9'\n" +
            "          WHEN _226品牌限时折扣名称 is not NULL AND 百亿长期216折扣活动名字 is not NULL THEN 百亿长期216折扣活动力度\n" +
            "     ELSE NULL END) as _226品牌限时折扣力度,_226品牌限时折扣活动价,百亿秒杀,月卡专享价7月\n" +
            "    from(\n" +
            "    SELECT a.商品ID,a.库存,\n" +
            "     max(CASE WHEN (CAST(a._1件实收最低场景商家预估实收价格 AS NUMERIC) - CAST(a.百亿基础价 AS NUMERIC))   = 0 THEN '只有百亿基础价，没有叠加其他促销' ELSE NULL END) as 是否有百亿促销,\n" +
            "     max(CASE WHEN b.活动大类 = '日常百亿入口' THEN FORMATDATETIME(PARSEDATETIME(b.提交时间, 'yyyy-MM-dd HH:mm:ss'), 'yyyy-MM-dd') ELSE NULL END) as 百亿基础价提报时间,\n" +
            "     max(CASE WHEN b.活动大类 = '百亿满减专区' THEN b.活动名称 ELSE NULL END) as 百亿满减专区,\n" +
            "     max(CASE WHEN b.活动大类 = '百亿自主降价' THEN b.活动价 ELSE NULL END) as 百亿自主降价自主降价,\n" +
            "     max(CASE WHEN b.活动大类 = '百亿长期216折扣' THEN b.活动名称 ELSE NULL END) as 百亿长期216折扣活动名字,\n" +
            "     max(CASE WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8.5%' THEN '0.85'\n" +
            "              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%8%' THEN '0.8'\n" +
            "              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%长期%' THEN '0.9'\n" +
            "              WHEN b.活动大类 = '百亿长期216折扣' and b.活动名称 like '%9.5%' THEN '0.95'\n" +
            "      ELSE NULL END) as 百亿长期216折扣活动力度,\n" +
            "    \n" +
            "     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动名称 ELSE NULL END) as _226品牌限时折扣名称,\n" +
            "     max(CASE WHEN b.活动大类 = '百亿短期22折扣' THEN b.活动价 ELSE NULL END) as _226品牌限时折扣活动价,\n" +
            "     max(CASE WHEN b.活动大类 = '百亿秒杀' THEN b.活动价 ELSE NULL END) as 百亿秒杀,\n" +
            "     max(CASE WHEN b.活动大类 = '月卡专享价7月' THEN b.活动名称 ELSE NULL END) as 月卡专享价7月,\n" +
            "     FROM (SELECT * FROM 多多店铺商品资料 where 百亿基础价 !='没参加') a\n" +
            "      left join\n" +
            "      (select 商品ID ,a.活动ID,活动大类 ,b.活动名称 ,b.提交时间,b.活动价     from 活动ID和活动类型分解 a inner join  营销活动_报名记录 b on a.活动ID=b.活动ID) b\n" +
            "      on a.商品ID=b.商品ID\n" +
            "        group by a.商品ID,a.库存\n" +
            "    )\n" +
            "    ) b  on a.商品ID=b.商品ID")
    List<RETSJSC> selectRETSJSC();
}


