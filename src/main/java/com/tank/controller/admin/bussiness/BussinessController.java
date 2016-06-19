package com.tank.controller.admin.bussiness;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bs.util.CommonUtils;
import com.bs.util.HttpPostUploadUtil;
import com.bs.util.ResultCode;
import com.tank.controller.admin.AdminBaseController;
import com.tank.manage.BasBusinessManage;
import com.tank.model.Admin;
import com.tank.model.BasBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 机构管理（医院管理）
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/bussiness")
public class BussinessController extends AdminBaseController {

    @Autowired
    BasBusinessManage basBusinessManage;


    @Autowired
    HttpPostUploadUtil imageUploadService;

    @RequestMapping(value = "bussiness")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/bussiness");
        modelAndView.addObject("currentpage", CommonUtils.isNull(currentpage) ? "1" : currentpage);
        return modelAndView;
    }

    /**
     * 医院列表
     *
     * @param page
     * @param length
     * @param model
     * @return
     */
    @RequestMapping(value = "bussiness/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, Model model, HttpServletRequest request) {

        Map<String, Object> regMsg = new HashMap<String, Object>();
        regMsg.put("data", basBusinessManage.list(page, length));
        regMsg.put("total", basBusinessManage.count());
        regMsg.put("code", ResultCode.SUCCESS);
        return regMsg;
    }


    @RequestMapping(value = "bussiness/updateView")
    public ModelAndView updateView(Long id, String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/bas/bussiness_update");
        modelAndView.addObject("id", id);
        modelAndView.addObject("currentpage", currentpage);
        return modelAndView;
    }

    @RequestMapping(value = "bussiness/addView")
    public String addView() {
        return "admin/bas/bussiness_add";
    }

    @RequestMapping(value = "bussiness/saveorupdate", method = RequestMethod.POST)
    public void insertOrUpdate(BasBusiness organization, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin account = getAdmin(request);
        JSONObject map = new JSONObject();
        boolean flag = false;
        if (file != null) {
            String ret = imageUploadService.formUpload(file, "1200x800", "750x500");
            if (!CommonUtils.isNull(ret)) {
                Map jmap = JSON.parseObject(ret, Map.class);
                if ("1".equals(jmap.get("code").toString())) {
                    flag = true;
                    organization.setPicurl(imageUploadService.getNetServiceUrl() + jmap.get("url"));
                }
            }
        }
        if (flag || file == null) {
            if (organization.getId() != null) {
                organization.setLastmodifyuser(account.getId());
                organization.setLastmodifydate(new Date());
                map.put("code", basBusinessManage.update(organization));
                map.put("data", organization.getId());
                response.getWriter().println(map.toJSONString());
            } else {
                organization.setCreateuser(account.getId());
                organization.setCreatedate(new Date());
                map.put("code", ResultCode.SUCCESS);
                map.put("data", basBusinessManage.insertBackId(organization));
                response.getWriter().println(map.toJSONString());
            }
        } else {
            map.put("code", "0");
            response.getWriter().println(map.toJSONString());
        }
    }

    @RequestMapping(value = "bussiness/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(@PathVariable("id") Long id) {
        return basBusinessManage.delete(id) == 1;

    }

    @RequestMapping(value = "bussiness/detail/{id}", method = RequestMethod.POST)
    @ResponseBody
    public BasBusiness detail(@PathVariable("id") Long id) {
        return basBusinessManage.getById(id);
    }

}
