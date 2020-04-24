package com.jyp.kill.controller;

import com.jyp.kill.api.enums.StatusCode;
import com.jyp.kill.api.response.BaseResponse;
import com.jyp.kill.dao.ItemKillSuccessMapper;
import com.jyp.kill.dto.KillDto;
import com.jyp.kill.dto.KillSuccessUserInfo;
import com.jyp.kill.service.IKillService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Description:秒杀商品操作
 *
 * @author: jia
 * @date: 2020/2/28 17:33
 */
@Controller
public class KillController {

    private static final Logger log = LoggerFactory.getLogger(KillController.class);

    private static final String prefix = "kill";

    @Autowired
    private IKillService killService;

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    /***
     * 商品秒杀核心业务逻辑 未优化 出现超卖、重复购买现象
     * @param dto
     * @param result
     * @return
     */
    @RequestMapping(value = prefix + "/execute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse execute(@RequestBody @Validated KillDto dto, BindingResult result) {
        if (result.hasErrors() || dto.getKillId() <= 0) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            Boolean res = killService.killItem(dto.getKillId(), dto.getUserId());
            if (!res) {
                return new BaseResponse(StatusCode.Fail.getCode(), "商品已抢购完毕或者不在抢购时间段");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    /**
     * 查看订单详情
     *
     * @return
     */
    @RequestMapping(value = prefix + "/record/detail/{orderNo}", method = RequestMethod.GET)
    public String killRecordDetail(@PathVariable String orderNo, ModelMap modelMap) {
        if (StringUtils.isBlank(orderNo)) {
            return "error";
        }
        KillSuccessUserInfo info = itemKillSuccessMapper.selectByCode(orderNo);
        if (info == null) {
            return "error";
        }
        modelMap.put("info", info);
        return "killRecord";
    }

    //抢购成功跳转页面
    @RequestMapping(value = prefix + "/execute/success", method = RequestMethod.GET)
    public String executeSuccess() {
        return "executeSuccess";
    }

    //抢购失败跳转页面
    @RequestMapping(value = prefix + "/execute/fail", method = RequestMethod.GET)
    public String executeFail() {
        return "executeFail";
    }

    /**
     * Description:商品秒杀核心业务逻辑-用于jmeter压力测试 post路径 127.0.0.1：8080/kill/execute/mysql
     * MySQL优化 解决超卖，但仍然出现重复购买
     * @author: jia
     * @date: 2020/2/29 10:55
     */
    @RequestMapping(value = prefix + "/execute/mysql", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse executeMysql(@RequestBody @Validated KillDto dto, BindingResult result) {
        if (result.hasErrors() || dto.getKillId() <= 0) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //不加分布式锁的前提
            Boolean res = killService.killItemV2(dto.getKillId(), dto.getUserId());
            if (!res) {
                return new BaseResponse(StatusCode.Fail.getCode(), "不加分布式锁-商品已抢购完毕或者不在抢购时间段");
            }

            //基于Redis的分布式锁进行控制
            /*Boolean res=killService.killItemV3(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redis的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }*/

            //基于Redisson的分布式锁进行控制
            /*Boolean res=killService.killItemV4(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redisson的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }*/

            //基于ZooKeeper的分布式锁进行控制
//            Boolean res=killService.killItemV5(dto.getKillId(),dto.getUserId());
//            if (!res){
//                return new BaseResponse(StatusCode.Fail.getCode(),"基于ZooKeeper的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
//            }

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }


    @RequestMapping(value = prefix + "/execute/redis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public BaseResponse executeRedis(@RequestBody @Validated KillDto dto, BindingResult result) {
        if (result.hasErrors() || dto.getKillId() <= 0) {
            return new BaseResponse(StatusCode.InvalidParams);
        }
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            //基于Redis的分布式锁进行控制
            Boolean res=killService.killItemV3(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redis的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }

            //基于Redisson的分布式锁进行控制
            /*Boolean res=killService.killItemV4(dto.getKillId(),dto.getUserId());
            if (!res){
                return new BaseResponse(StatusCode.Fail.getCode(),"基于Redisson的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
            }*/

            //基于ZooKeeper的分布式锁进行控制
//            Boolean res=killService.killItemV5(dto.getKillId(),dto.getUserId());
//            if (!res){
//                return new BaseResponse(StatusCode.Fail.getCode(),"基于ZooKeeper的分布式锁进行控制-哈哈~商品已抢购完毕或者不在抢购时间段哦!");
//            }

        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }
}
