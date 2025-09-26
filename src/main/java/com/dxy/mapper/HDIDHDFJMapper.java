package com.dxy.mapper;

import com.dxy.data.DPSPZL;
import com.dxy.data.HDIDHDFJ;
import org.apache.ibatis.annotations.Insert;

public interface HDIDHDFJMapper {

    @Insert("INSERT INTO 活动ID和活动类型分解(`活动ID`,`活动名称`,`活动大类`)VALUES(#{活动ID} ,#{活动名称} ,#{活动大类} )")
    int insert(HDIDHDFJ e);
}
