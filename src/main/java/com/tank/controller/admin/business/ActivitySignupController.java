package com.tank.controller.admin.business;

import com.bs.util.CommonUtils;
import com.bs.util.HttpPostUploadUtil;
import com.bs.util.ResultCode;
import com.tank.controller.admin.AdminBaseController;
import com.tank.manage.ActivitySignupManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 活动报名管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/business")
public class ActivitySignupController extends AdminBaseController {

    @Autowired
    ActivitySignupManage activitySignupManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;


    @RequestMapping(value = "activitysignup")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activitysignup");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "activitysignup_user")
    public ModelAndView activitysignup_user(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activitysignup_user");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }


    @RequestMapping(value = "activitysignup/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", activitySignupManage.getSignCounts(page, length));
        regMsg.put("total", activitySignupManage.countSignCounts());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }



}
