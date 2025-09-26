package com.dxy.mapper;

import com.dxy.data.HDIDHDFJ;
import com.dxy.data.YXHDBMJL;
import org.apache.ibatis.annotations.Insert;

public interface YXHDBMJLMapper {
    @Insert("INSERT INTO 营销活动_报名记录(`活动名称`,`活动ID`,`商品名称`,`商品ID`,`商品图片`,`类目名称`,`提交时间`,`活动价`,`活动库存`,`活动时间`,`活动状态`,`累计销量`,`库存`,`失效原因`)VALUES(#{活动名称} ,#{活动ID} ,#{商品名称} ,#{商品ID} ,#{商品图片} ,#{类目名称} ,#{提交时间} ,#{活动价} ,#{活动库存} ,#{活动时间} ,#{活动状态} ,#{累计销量} ,#{库存} ,#{失效原因}  )")
    int insert(YXHDBMJL e);
}
