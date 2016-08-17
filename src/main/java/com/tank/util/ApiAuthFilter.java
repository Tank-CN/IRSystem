package com.tank.util;

import com.bs.util.RequestUtils;
import com.bs.util.ResponseUtils;
import com.bs.util.ResultCode;
import com.bs.util.encryption.DESUtils;
import com.bs.util.encryption.MD5Utils;
import com.tank.Constants;
import com.tank.manage.UserManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 判断授权
 *
 * @author Administrator
 */
@Component("apiFilter")
public class ApiAuthFilter extends OncePerRequestFilter {


    @Autowired
    UserManage userManage;

    //时间戳超时时间
    private final static long timeout = 30 * 60 * 1000;

    //不需要验证的url
    String[] unAuthUrls = {};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info(request.getMethod() + "    " + request.getRequestURI());
        Map map = request.getParameterMap();
        // 测试
        if ("1".equals(RequestUtils.getValue(request, "t"))) {
            filterChain.doFilter(request, response);
            return;
        }
        //测试阶段放开Get和Post的验证
//		if (!"POST".equals(request.getMethod())) {
//			ResponseUtils.response(ResultCode.METHOD_MUST_POST, response);
//			return;
//		}
        //判断公共参数是否存在
        if (RequestUtils.isEmpty(request, "sign", "timestamp", "sn", "device", "token")) {
            // 公共参数缺失
            ResponseUtils.response(ResultCode.PARAMETERS_MISSING, response);
            return;
        } else {
            //判断时间戳有效期-30分钟
            String timestamp = RequestUtils.getValue(request, "timestamp");
            long currentTimes = System.currentTimeMillis();
            if (Math.abs(currentTimes - Long.valueOf(timestamp)) > timeout) {
                //超时提示
                ResponseUtils.response(ResultCode.REQCODE_TIME_EXPIRED, response);
                return;
            }
            // 开始验证
            //判断签名sign是否正确
            if (isAuthUrl(request.getRequestURI()) && !RequestUtils.getValue(request, "sign").equals(MD5Utils.getMD5(getSign(request)))) {
                //签名不正确
                ResponseUtils.response(ResultCode.SIGN_ERROR, response);
                return;
            }


            String sn = RequestUtils.getValue(request, "sn");
            String[] snArry = new DESUtils(Constants.DES_KEY).decryptStr(sn).split(";");
            if (null != snArry && snArry.length == 4) {
                request.setAttribute("id", snArry[0]);
                filterChain.doFilter(request, response);
                return;
            } else {
                ResponseUtils.response(ResultCode.SIGN_ERROR, response);
                return;
            }
        }
    }


    public String getSign(HttpServletRequest request) {
        StringBuffer sb = new StringBuffer();
        Map map = request.getParameterMap();
        List keys = new ArrayList(map.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            //去除sign
            if (!"sign".equals(keys.get(i).toString())) {
                sb.append(((String[]) map.get(keys.get(i)))[0]);
            }
        }
        return sb.toString();
    }

    /**
     * 需要验证  返回true
     * 不需要验证  返回false
     *
     * @param url
     * @return
     */
    public boolean isAuthUrl(String url) {
        for (int i = 0; i < unAuthUrls.length; i++) {
            if (url.equals(unAuthUrls[i])) {
                return false;
            }
        }
        return true;
    }
}
