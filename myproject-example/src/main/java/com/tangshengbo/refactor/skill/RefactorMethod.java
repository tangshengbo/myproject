package com.tangshengbo.refactor.skill;

import java.util.Arrays;
import java.util.List;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class RefactorMethod {

    public static void main(String[] args) {
        String[] peoples = new String[]{"Tang"};
        checkSecurity(peoples);

    }


    /**
     * Separate Query from Modifier(将查询函数和修改函数分离)
     * 一个函数只做一件事，无副作用
     * @param peoples
     */
    public static void checkSecurity(String[] peoples) {
        String foundPeople = foundPeople(peoples);
        System.out.println(foundPeople);
        sendAlert(peoples);

    }

    /**
     * Substitute（替换算法）
     * 将某个算法替换成另一个更清晰的算法
     * @param peoples
     */
    private static void sendAlert(String[] peoples) {
        List<String> peopleList = Arrays.asList(peoples);
        if (peopleList.contains("Tang")) {
            System.out.println("Tang");
            return;
        }
        return;
    }

    public static String foundPeople(String[] peoples) {
        for (String people : peoples) {
            if ("Don".equals(people)) {
                return "alert:Don";
            }
            if ("Tang".equals(people)) {
                return "alert:Tang";
            }
        }
        return "";
    }
}
