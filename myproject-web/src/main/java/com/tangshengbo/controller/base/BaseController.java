/*
package com.tangshengbo.controller.base;


import com.tangshengbo.commons.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.*;


*/
/**
 * create by tangshengbo
 *//*

public abstract class BaseController {
    private Logger log = Logger.getLogger(this.getClass());
    protected ModelAndView getModelAndView(String viewName) {
        return new ModelAndView(viewName).addObject("basePath", getBasePath());
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getResponse() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public String getBasePath() {
        String path = this.getRequest().getContextPath();
        String basePath = this.getRequest().getScheme() + "://"
                + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort()
                + path + "/";
        return basePath;
    }

    public HttpSession getSession() {
        return getRequest().getSession();
    }



    public abstract Map<String,Object> initParams(Map<String, Object> params);

    public Map<String, Object> initBaseParams(Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "orderId":
                case "supplierId":
                case "targetId":
                case "metaProductId":
                    if (entry.getValue()!=null&&!StringUtils.isEmpty((String)entry.getValue())&& !"0".equals((String)entry.getValue())) {
                        continue;
                    }
                    break;
                default:
                    result.put(entry.getKey(), entry.getValue());
            }
        }
        initParams(result);
        return result;
    }

    public void sendAjaxResultByJson(String json) {
        this.getResponse().setContentType("application/json;charset=UTF-8");
        this.getResponse().setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = this.getResponse().getWriter();
            out.write(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
/**
     * 把HttpServletRequest中的参数转换为Map，转换后的参数值类型为String，request中的参数名称与Map中的Key一致
     *
     * @param map
     *            目标Map
     * @param key
     *            Map中的key
     * @param request
     *            HttpServletRequest
     *//*

    protected void extractRequestParam(Map<String, Object> map, String key, HttpServletRequest request) {
        this.extractRequestParam(map, key, key, String.class, request);
    }

    */
/**
     * 把HttpServletRequest中的参数转换为Map，转换后的参数值类型为String，request中的参数名称与Map中的Key一致
     *
     * @param map
     *            目标Map
     * @param key
     *            Map中的key
     * @param cls
     *            参数值的类型
     * @param request
     *            HttpServletRequest
     *//*

    protected void extractRequestParam(Map<String, Object> map, String key, Class<?> cls, HttpServletRequest request) {
        this.extractRequestParam(map, key, key, cls, request);
    }

    */
/**
     * 把HttpServletRequest中的参数转换为Map
     *
     * @param map
     *            目标Map
     * @param key
     *            Map中的key
     * @param request
     *            HttpServletRequest
     * @param paramName
     *            request中的参数名
     * @param cls
     *            参数值的类型
     *//*

    protected void extractRequestParam(Map<String, Object> map, String key, String paramName, Class<?> cls, HttpServletRequest request) {
        String[] vals = request.getParameterValues(paramName);
        if (vals == null || vals.length == 0) {
            log.debug(paramName + "'s value is null");
            return;
        }
        if (vals.length == 1) {
           */
/* if (StringUtil.isEmptyString(vals[0])) {
                log.debug(paramName + "'s value is an empty string");
                return;
            }*//*

            Object o_val = null;
            String val = vals[0].trim();
            if (Date.class.getName().equals(cls.getName())) {
                o_val = DateUtil.stringToDate(val, "yyyy-MM-dd");
            } else if (Long.class.getName().equals(cls.getName())) {
                o_val = Long.parseLong(val);
            } else if (Integer.class.getName().equals(cls.getName())) {
                o_val = Integer.parseInt(val);
            } else if (Float.class.getName().equals(cls.getName())) {
                o_val = Float.parseFloat(val);
            } else if (String.class.getName().equals(cls.getName())) {
                o_val = val;
            } else if (List.class.getName().equals(cls.getName())) {
                List<Object> list = new ArrayList<Object>();
                list.add(val);
                o_val = list;
            } else {
                throw new RuntimeException("Does not support " + cls + ",please add it by yourself!!");
            }
            map.put(key, o_val);
        } else if (vals.length > 1) {
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < vals.length; i++) {
                if ("".equals(vals[i])) {
                    log.debug(paramName + "[" + i + "]'s value is an empty string");
                    continue;
                }
                Object o_val = null;
                String val = vals[i].trim();
                o_val = val;
                list.add(o_val);
            }
            map.put(key, list);
        }

    }

}
*/
