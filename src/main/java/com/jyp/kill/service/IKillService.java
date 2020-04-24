package com.jyp.kill.service;

/**
 * Description:
 * @author: Dell
 * @date: 2020/2/28 10:13
 */
public interface IKillService {

    Boolean killItem(Integer killId, Integer userId) throws Exception;

    Boolean killItemV2(Integer killId, Integer userId) throws Exception;

    Boolean killItemV3(Integer killId, Integer userId) throws Exception;

    Boolean killItemV4(Integer killId, Integer userId) throws Exception;

    Boolean killItemV5(Integer killId, Integer userId) throws Exception;
}
