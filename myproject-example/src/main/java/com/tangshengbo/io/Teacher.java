package com.tangshengbo.io;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by TangShengBo on 2018/11/4
 */
public class Teacher implements InitializingBean {

    private String TeacherID;

    private  String TezcherName;

    @Override
    public String toString() {
        return "唐雨伦";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化---------------------Teacher");
    }
}
