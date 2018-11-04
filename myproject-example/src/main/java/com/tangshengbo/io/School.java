package com.tangshengbo.io;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TangShengBo on 2018/11/4
 */
public class School implements InitializingBean {

    private  String  SchoolID;

    @Autowired
    private  Teacher teacher;

    @Override
    public String toString() {
        return teacher.toString();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化.............School");
    }
}
