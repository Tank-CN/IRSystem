package com.tank.controller.api;

import com.bs.util.ResultCode;
import com.tank.manage.BasADBannerManage;
import com.tank.manage.BasADItemManage;
import com.tank.manage.BasBusinessManage;
import com.tank.manage.NewsManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/index")
public class IndexController extends ApiBaseController{


    @Autowired
    BasADBannerManage basADBannerManage;

    @Autowired
    BasADItemManage basADItemManage;

    @Autowired
    NewsManage newsManage;

    @Autowired
    BasBusinessManage basBusinessManage;

    @RequestMapping(value = "")
    @ResponseBody
    public Map index() {
       Map<String,Object> resMap=new HashMap<>();
        Map<String,Object> dataMap=new HashMap<>();
        //banner
        dataMap.put("adBannerVos",basADBannerManage.list(1,6));
        //广告栏
        dataMap.put("adItemVos",basADItemManage.list(1,6));
        //头条
        dataMap.put("newsVo",newsManage.getHotestNews());
        //热门商家
        dataMap.put("businessVos",basBusinessManage.listByType(null,null,1,8));
        resMap.put("data", dataMap);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }




}
