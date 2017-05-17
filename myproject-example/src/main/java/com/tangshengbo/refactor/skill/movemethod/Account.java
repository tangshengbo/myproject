package com.tangshengbo.refactor.skill.movemethod;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class Account {

    private AccountType accountType;

    private int daysOverDrawn;

  /*  private int rate;*/


    public Account(AccountType accountType, int daysOverDrawn) {
        this.accountType = accountType;
        this.daysOverDrawn = daysOverDrawn;
    }

    public Account() {
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getDaysOverDrawn() {
        return daysOverDrawn;
    }

    public void setDaysOverDrawn(int daysOverDrawn) {
        this.daysOverDrawn = daysOverDrawn;
    }

    public double overdraftCharge() {
       return accountType.overdraftCharge(this);
    }

    public double getBalance() {
        return 10 * 10 * this.accountType.getRate();
    }

  /*  public int getRate() {
        return this.accountType.getRate();
    }

    public void setRate(int rate) {
        this.accountType.setRate(rate);
    }*/
}
