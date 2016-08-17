package com.tank.controller.api;

import com.bs.util.ResultCode;
import com.tank.manage.ActivityManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/activity")
public class ActivityApiController extends ApiBaseController {


    @Autowired
    ActivityManage activityManage;


    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", activityManage.listAll(pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    @RequestMapping(value = "listbyuid")
    @ResponseBody
    public Map<String, Object> list(Long uid,@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", activityManage.list(pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }

}
