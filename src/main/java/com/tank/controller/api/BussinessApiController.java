package com.tank.controller.api;

import com.bs.util.CommonUtils;
import com.bs.util.ResultCode;
import com.tank.manage.BasBusinessManage;
import com.tank.model.BusinessReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/auth/business")
public class BussinessApiController extends ApiBaseController {

    @Autowired
    BasBusinessManage basBusinessManage;


    @RequestMapping(value = "list")
    @ResponseBody
    public Map<String, Object> list(Long typeid, Long typeiid, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("list", basBusinessManage.listByType(typeid, typeiid, pageno, pagesize));
        //只要第一次请求时才带上类别
        if (pageno == 1) {
            dataMap.put("types", basBusinessManage.listType(typeid));
        }
        resMap.put("data", dataMap);
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    @RequestMapping(value = "detail")
    @ResponseBody
    public Map<String, Object> detail(Long iid, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(iid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basBusinessManage.getById(iid));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }

    /**
     * 收藏
     *
     * @param bid
     * @param request
     * @return
     */
    @RequestMapping(value = "collect")
    @ResponseBody
    public Map<String, Object> collect(Long bid, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(bid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basBusinessManage.collect(getUid(request), bid));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 收藏的商家列表
     *
     * @param pageno
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping(value = "collectlist")
    @ResponseBody
    public Map<String, Object> collectlist(@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(uid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basBusinessManage.listByCollect(uid, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }


    /**
     * 给商家评论
     *
     * @param bid
     * @param content
     * @param request
     * @return
     */
    @RequestMapping(value = "reply")
    @ResponseBody
    public Map<String, Object> reply(Long bid, String content, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(bid) || CommonUtils.isNull(content)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        BusinessReply vo = new BusinessReply();
        vo.setCreatedate(new Date());
        vo.setBid(bid);
        vo.setUid(uid);
        vo.setContent(content);
        Long id = null;
        if ((id = basBusinessManage.reply(vo)) > 0) {
            resMap.put("data", id);
            resMap.put("code", ResultCode.SUCCESS);
        } else {
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }


}
