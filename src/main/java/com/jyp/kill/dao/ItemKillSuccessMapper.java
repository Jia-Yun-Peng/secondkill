package com.jyp.kill.dao;

import com.jyp.kill.domain.ItemKillSuccess;
import com.jyp.kill.dto.KillSuccessUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemKillSuccessMapper {
    int deleteByPrimaryKey(String code);

    int insert(ItemKillSuccess record);

    int insertSelective(ItemKillSuccess record);

    ItemKillSuccess selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(ItemKillSuccess record);

    int updateByPrimaryKey(ItemKillSuccess record);

    int countByKillUserId(@Param("killId") Integer killId, @Param("userId") Integer userId);

    KillSuccessUserInfo selectByCode(@Param("code") String code);

    int expireOrder(@Param("code") String code);

    List<ItemKillSuccess> selectExpireOrders();
}