package com.tank.controller;

import com.bs.util.DateUtils;
import com.tank.manage.BasBusinessManage;
import com.tank.manage.NewsManage;
import com.tank.model.BasBusiness;
import com.tank.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/view")
public class ViewController {


    @Autowired
    NewsManage newsManage;

    @Autowired
    BasBusinessManage basBusinessManage;

    @RequestMapping(value = "newsdetail/{id}")
    public ModelAndView newsdetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("h5/newsdetail");
        News vo=newsManage.getById(id);
        modelAndView.addObject("vo", vo);
        modelAndView.addObject("time", DateUtils.dateFormate(vo.getCreatedate(),"yyyy-MM-dd"));
        return modelAndView;
    }


    @RequestMapping(value = "businessdetail/{id}")
    public ModelAndView businessdetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("h5/businessdetail");
        BasBusiness vo=basBusinessManage.getById(id);
        modelAndView.addObject("vo", vo);
        modelAndView.addObject("time", DateUtils.dateFormate(vo.getCreatedate(),"yyyy-MM-dd"));
        return modelAndView;
    }
}
