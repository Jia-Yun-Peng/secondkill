package com.jyp.kill.controller;


import com.jyp.kill.api.enums.StatusCode;
import com.jyp.kill.api.response.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    private String welcome(String name, ModelMap modelMap) {
        if (StringUtils.isBlank(name)) {
            name = "welcome";
        }
        modelMap.put("name",name);
        return "welcome";
    }

    //不跳转页面，只是js发起异步请求拿后端数据
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    @ResponseBody
    public String data(String name){
        if(StringUtils.isBlank(name)){
            name="welcome";
        }
        return name;
    }

    @RequestMapping(value = "/response",method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse response(String name){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        if (StringUtils.isBlank(name)){
            name="这是welcome!";
        }
        response.setData(name);
        return response;
    }

}
