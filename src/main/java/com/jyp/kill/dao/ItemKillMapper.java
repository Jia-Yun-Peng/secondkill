package com.jyp.kill.dao;

import com.jyp.kill.domain.ItemKill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ItemKillMapper {

    List<ItemKill> selectAll();

    ItemKill selectById(@Param("id") Integer id);

    int updateKillItem(@Param("killId") Integer killId);

    //只查询大于零的
    ItemKill selectByIdV2(@Param("id") Integer id);
    //只对大于零的减一
    int updateKillItemV2(@Param("killId") Integer killId);
}