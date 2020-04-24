package com.jyp.kill.controller;/**
 * Created by Administrator on 2019/6/16.
 */

import com.jyp.kill.domain.ItemKill;
import com.jyp.kill.service.IItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description:商品展示
 * @author: jia
 * @date: 2020/2/28 10:16
 */
@Controller
public class ItemController {

    private static final Logger log= LoggerFactory.getLogger(ItemController.class);
    //请求路径前缀
    private static final String prefix="item";

    @Autowired
    private IItemService itemService;

    /**
     * 斜杠表示默认调用
     * 获取商品列表
     */
    @RequestMapping(value = {"/","/index",prefix+"/list",prefix+"/index.html"},method = RequestMethod.GET)
    public String list(ModelMap modelMap){
        try {
            //获取待秒杀商品列表
            List<ItemKill> list=itemService.getKillItems();
            modelMap.put("list",list);

            log.info("获取待秒杀商品列表的数据：{}",list);
        }catch (Exception e){
            log.error("获取待秒杀商品列表时发生异常：",e.fillInStackTrace());
            //重定向跳转至错误页面
            return "redirect:/base/error";
        }
        return "list";
    }

    /**
     * Description:获取商品详情
     * @author: jia
     * @date: 2020/2/28 15:57
     */
    @RequestMapping(value = prefix+"/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable Integer id,ModelMap modelMap){
        if (id==null || id<=0){
            return "redirect:/base/error";
        }
        try {
            ItemKill detail=itemService.getKillDetail(id);
            modelMap.put("detail",detail);
        }catch (Exception e){
            log.error("获取待秒杀商品的详情-发生异常：id={}",id,e.fillInStackTrace());
            return "redirect:/base/error";
        }
        return "info";
    }
}





























