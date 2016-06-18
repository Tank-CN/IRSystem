package com.tank.controller.admin.sys;

import com.alibaba.fastjson.JSONObject;
import com.bs.util.ResultCode;
import com.tank.controller.admin.AdminBaseController;
import com.tank.manage.SysAppVersionManage;
import com.tank.model.Admin;
import com.tank.model.SysAppVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * App跟新模块
 * 
 */
@Controller
@RequestMapping(value = "/admin/sys")
public class SysAppVersionController extends AdminBaseController {

	@Autowired
	private SysAppVersionManage sysAppVersionManage;

	@RequestMapping(value = "appversion")
	public String index() {
		return "admin/sys/appversion";
	}

	@RequestMapping(value = "appversion/addview")
	public String addView() {
		return "admin/sys/appversion_add";
	}

	@RequestMapping(value = "appversion/updateview/{id}", method = RequestMethod.GET)
	public ModelAndView updateView(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView(
				"admin/sys/appversion_update");
		modelAndView.addObject("id", id);
		return modelAndView;
	}

	@RequestMapping(value = "appversion/list", method = RequestMethod.POST)
	@ResponseBody
	public Map list(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "length", defaultValue = "20") Integer length,
			ServletRequest servletRequest) {
		Map regMsg = new HashMap();
		regMsg.put("code", ResultCode.SUCCESS);
		regMsg.put("data", sysAppVersionManage.list(page, length));
		regMsg.put("total", sysAppVersionManage.count());
		return regMsg;
	}

	@RequestMapping(value = "appversion/save", method = RequestMethod.POST)
	@ResponseBody
	public Boolean save(SysAppVersion appVersion,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws IOException {
		Admin admin=getAdmin(request);
		if (file != null) {
			String store = "/upload/apk";
			String path = request.getSession().getServletContext()
					.getRealPath(store);
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());
			if (!suffix.toLowerCase().equals("apk")) {
				return false;
			}
			fileName = appVersion.getAppcode() + appVersion.getAppversion()
					+ "." + suffix;
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			appVersion.setAppurl(store + fileName);
		}
		appVersion.setCreatedate(new Date());
		appVersion.setCreateuser(admin.getId());
		return sysAppVersionManage.save(appVersion) == 1;
	}

	@RequestMapping(value = "appversion/detail/{id}", method = RequestMethod.POST)
	@ResponseBody
	public SysAppVersion getById(@PathVariable("id") Long id) {
		return sysAppVersionManage.getById(id);
	}

	@RequestMapping(value = "appversion/update", method = RequestMethod.POST)
	public void update(SysAppVersion appVersion,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,HttpServletResponse response) throws IOException {
		Admin admin=getAdmin(request);
		JSONObject map=new JSONObject();
		if (file != null) {
			String store = "/upload/apk/";
			String path = request.getSession().getServletContext()
					.getRealPath(store);
			String fileName = file.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());
			if (!suffix.toLowerCase().equals("apk")) {
				map.put("code", "0");
				response.getWriter().println(map.toJSONString());
			}
			fileName = appVersion.getAppcode() + appVersion.getAppversion()
					+ "." + suffix;
			File targetFile = new File(path, fileName);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			appVersion.setAppurl(store + fileName);
		}
		appVersion.setModifydate(new Date());
		appVersion.setModifyuser(admin.getId());
		map.put("code", sysAppVersionManage.update(appVersion));
		response.getWriter().println(map.toJSONString());
	}

	@RequestMapping(value = "appversion/del/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(@PathVariable("id") Long id) {
		return sysAppVersionManage.delete(id) == 1;
	}
}
