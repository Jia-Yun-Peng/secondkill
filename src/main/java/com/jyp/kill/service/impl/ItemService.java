package com.jyp.kill.service.impl;

import com.jyp.kill.dao.ItemKillMapper;
import com.jyp.kill.domain.ItemKill;
import com.jyp.kill.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:展示商品
 * @author: jia
 * @date: 2020/2/29 9:37
 */
@Service
public class ItemService implements IItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemKillMapper itemKillMapper;


    /**
     * 获取待秒杀商品列表
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<ItemKill> getKillItems() throws Exception {
        return itemKillMapper.selectAll();
    }

    /**
     * 获取秒杀详情
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ItemKill getKillDetail(Integer id) throws Exception {
        ItemKill entity = itemKillMapper.selectById(id);
        if (entity == null) {
            throw new Exception("获取秒杀详情-待秒杀商品记录不存在");
        }
        return entity;
    }
}



















