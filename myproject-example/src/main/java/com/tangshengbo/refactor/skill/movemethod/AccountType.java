package com.tangshengbo.refactor.skill.movemethod;

/**
 * Created by TangShengBo on 2017-04-11.
 */
public class AccountType {

    private boolean type;

    public boolean isType() {
        return type;
    }

    private int rate;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double overdraftCharge(Account account) {
        double result;
        if (isType()) {
            result = 10 * account.getDaysOverDrawn();
            return result;
        } else {
            result = 20 * account.getDaysOverDrawn() + account.getBalance() ;
            return result;
        }
    }
}
