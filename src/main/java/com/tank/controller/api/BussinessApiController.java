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


    @RequestMapping(value = "search")
    @ResponseBody
    public Map<String, Object> search(String key, @RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("data", basBusinessManage.search(key, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }



    @RequestMapping(value = "detail")
    @ResponseBody
    public Map<String, Object> detail(Long id, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(id)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (!CommonUtils.isNull(uid)) {
            dataMap.put("isCollect", basBusinessManage.isCollect(uid, id));
        }
        dataMap.put("replyVos",basBusinessManage.listReplyByBid(id,1,10));
        resMap.put("data", dataMap);
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
     * 取消收藏
     *
     * @param bid
     * @param request
     * @return
     */
    @RequestMapping(value = "uncollect")
    @ResponseBody
    public Map<String, Object> uncollect(Long bid, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(bid)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basBusinessManage.uncollect(getUid(request), bid));
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
     * @param request
     * @return
     */
    @RequestMapping(value = "reply")
    @ResponseBody
    public Map<String, Object> reply(BusinessReply vo, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        vo.setCreatedate(new Date());
        vo.setUid(uid);
        Long id = null;
        if ((id = basBusinessManage.reply(vo)) > 0) {
            resMap.put("data", id);
            resMap.put("code", ResultCode.SUCCESS);
        } else {
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }


    /**
     * 删除商家评论
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "unreply")
    @ResponseBody
    public Map<String, Object> unreply(Long id, HttpServletRequest request) {
        Long uid = getUid(request);
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(id) ) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        if ((basBusinessManage.unreply(id))) {
            resMap.put("code", ResultCode.SUCCESS);
        } else {
            resMap.put("code", ResultCode.ERROR);
        }
        return resMap;
    }


    /**
     * 商家评论的列表
     *
     * @param pageno
     * @param pagesize
     * @param request
     * @return
     */
    @RequestMapping(value = "replylist")
    @ResponseBody
    public Map<String, Object> replylist(Long id,@RequestParam(value = "pageno", defaultValue = "1") Integer pageno, @RequestParam(value = "pagesize", defaultValue = "20") Integer pagesize, HttpServletRequest request) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        if (CommonUtils.isNull(id)) {
            resMap.put("code", ResultCode.PARAMETERS_EMPTY);
            resMap.put("msg", "传入参数不能为空");
            return resMap;
        }
        resMap.put("data", basBusinessManage.listReplyByBid(id, pageno, pagesize));
        resMap.put("code", ResultCode.SUCCESS);
        return resMap;
    }

}
