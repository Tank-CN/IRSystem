package com.tank.controller.admin.business;

import com.bs.util.CommonUtils;
import com.tank.controller.admin.AdminBaseController;
import com.tank.manage.DynamicManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态管理
 *
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin/business")
public class DynamicController extends AdminBaseController {

    @Autowired
    DynamicManage    dynamicManage;




    @RequestMapping(value = "dynamic")
    public ModelAndView basOrganization(String currentpage) {
        ModelAndView modelAndView = new ModelAndView("admin/business/dynamic");
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
    @RequestMapping(value = "dynamic/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(String content,String nickname, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "length", defaultValue = "20") Integer length, Model model, HttpServletRequest request) {
        Map<String, Object> regMsg = new HashMap<String, Object>();
//        List<Activity> list = activityManage.list(title, page, length);
//        if (null != list && list.size() > 0) {
//            for (Activity activity : list) {
//                activity.setContent(null);
//            }
//            regMsg.put("data", list);
//            regMsg.put("total", activityManage.count(title));
//            regMsg.put("code", ResultCode.SUCCESS);
//        } else {
//            regMsg.put("data", null);
//            regMsg.put("total", 0);
//            regMsg.put("code", ResultCode.SUCCESS);
//        }

        return regMsg;
    }


//    @RequestMapping(value = "activity/delete/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public Boolean delete(@PathVariable("id") Long id) {
//        return activityManage.delete(id) == 1;
//
//    }
//
//    @RequestMapping(value = "activity/detail/{id}", method = RequestMethod.POST)
//    @ResponseBody
//    public Activity detail(@PathVariable("id") Long id) {
//        return activityManage.getById(id);
//    }

}
