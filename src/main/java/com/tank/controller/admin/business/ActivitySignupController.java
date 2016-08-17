package com.tank.controller.admin.business;

import com.alibaba.fastjson.JSONObject;
import com.bs.util.CommonUtils;
import com.bs.util.HttpPostUploadUtil;
import com.bs.util.ResultCode;
import com.tank.controller.admin.AdminBaseController;
import com.tank.manage.ActivitySignupManage;
import com.tank.model.ActivitySignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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


    @RequestMapping(value = "activitysignup/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, Model model, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", activitySignupManage.list(page, length));
        regMsg.put("total", activitySignupManage.count());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }

    @RequestMapping(value = "activitysignup/listbaid", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> listbaid(Long aid,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, Model model, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", activitySignupManage.listByAid(aid,page, length));
        regMsg.put("total", activitySignupManage.countByAid(aid));
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "activitysignup/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/activitysignup_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "activitysignup/addView")
    public String addView() {
        return "admin/business/activitysignup_add";
    }

    @RequestMapping(value = "activitysignup/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(ActivitySignup activity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject map = new JSONObject();
        if (activity.getId() != null) {
            map.put("code", activitySignupManage.update(activity));
            map.put("data", activity.getId());
            response.getWriter().println(map.toJSONString());
        } else {
            activity.setCreatedate(new Date());
            map.put("code", ResultCode.SUCCESS);
            map.put("data", activitySignupManage.insertBackId(activity));
            response.getWriter().println(map.toJSONString());
        }
    }


    @RequestMapping(value = "activitysignup/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return activitySignupManage.delete(id) == 1;
    }

    @RequestMapping(value = "activitysignup/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ActivitySignup detail(@PathVariable("id") Long id) {
        return activitySignupManage.getById(id);
    }

}
