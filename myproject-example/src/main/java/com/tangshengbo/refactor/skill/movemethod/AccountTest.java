package com.tangshengbo.refactor.skill.movemethod;

/**
 * Created by TangShengBo on 2017-04-11.
 */
public class AccountTest {

    public static void main(String[] args) {
        AccountType accountType = new AccountType();
        accountType.setType(false);
        accountType.setRate(30);

        Account account = new Account(accountType, 10);
//       account.setRate(20);
        System.out.println("奶奶");

        System.out.println(account.overdraftCharge());
        System.out.println(accountType.overdraftCharge(account));
        int arg = 20;
        triple(arg);
        System.out.println(arg);
    }

    public static void triple(int arg) {
        arg = arg * 3;
        System.out.println(arg);
    }

}
