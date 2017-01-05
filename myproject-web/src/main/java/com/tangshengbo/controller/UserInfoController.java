package com.tangshengbo.controller;


import com.tangshengbo.model.UserInfo;
import com.tangshengbo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2016/11/24.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {

        logger.info("login param {}", model);
        String loginStatus = (String) request.getSession().getAttribute(com.tangshengbo.utils.Constants.RANDOM_LOGIN_KEY);
        if (loginStatus == null) {
            logger.info("登录失败>>>>>>>>>>>>>>>>>>>>>>>>");
            return "redirect:/login.jsp";
        }
        String result;
        if (userInfoService.login(userInfo)) {
            result = "登录成功 欢迎你" + userInfo.getUserName();
            model.addAttribute("loginInfo", result);
            logger.info("登录成功>>>>>>>>>>>>>>>>>>>>>>>>");
            request.getSession().setAttribute(com.tangshengbo.utils.Constants.RANDOM_LOGIN_KEY, "success");
            return "index";
        } else {
            logger.info("登录失败>>>>>>>>>>>>>>>>>>>>>>>>");
            return "redirect:/login.jsp";
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(Model model, UserInfo userInfo, HttpServletRequest request) {

        logger.info("register param [{}]", userInfo);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        System.out.println(userDetails.toString());
        if (userInfoService.register(userInfo)) {
            logger.info("注册成功>>>>>>>>>>>>>>>>>>>>>>>>");
            request.getSession().setAttribute(com.tangshengbo.utils.Constants.RANDOM_LOGIN_KEY, "success");
            return  new ModelAndView("redirect:/login.jsp");
        }

        return new ModelAndView("redirect:/register.jsp");
    }


}



