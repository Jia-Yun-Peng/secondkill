package com.jyp.kill.service;

import com.jyp.kill.domain.ItemKill;

import java.util.List;

/**
 * Description:
 * @author: Dell
 * @date: 2020/2/28 10:13
 */
public interface IItemService {

    List<ItemKill> getKillItems() throws Exception;

    ItemKill getKillDetail(Integer id) throws Exception;
}
