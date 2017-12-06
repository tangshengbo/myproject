package com.tangshengbo.design.flyweight.sign;

/**
 * Created by TangShengBo on 2017-10-22.
 */
public class SignInfoTest {

    public static void main(String[] args) {
        //初始化对象池
        for (int i = 0; i < 4; i++) {
            String subject = "科目" + i;
            //初始化地址
            for (int j = 0; j < 30; j++) {
                String key = subject + "考试地点" + j;
                SignInfoFactory.getSignInfo(key);
            }
        }
        SignInfo signInfo = SignInfoFactory.getSignInfo("科目3考试地点28");
        signInfo.setId("1");
        signInfo.setLocation("location");
        signInfo.setPostAddress("口口声声");
        signInfo.setSubject("看看书");
        System.out.println(SignInfoFactory.getSignInfo("科目3考试地点28").toString());
        System.out.println(signInfo.toString());
    }
}
