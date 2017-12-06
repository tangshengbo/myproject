package com.tangshengbo.design.template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tangshengbo on 2017/3/23.
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //记录访问日志
        //进行权限判断
        /*if(具有权限)
        {
            try
            {
                doService(request,response);
            }
            catch(Excetpion e)
            {
                //记录异常信息
            }
        }*/
    }
    //注意访问权限定义成protected，显得既专业，又严谨，因为它是专门给子类用的
    protected abstract void doService(HttpServletRequest request, HttpServletResponse response);

}
