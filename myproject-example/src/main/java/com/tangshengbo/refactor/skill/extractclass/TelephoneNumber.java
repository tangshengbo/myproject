package com.tangshengbo.refactor.skill.extractclass;

/**
 * Created by TangShengBo on 2017-03-28.
 */
public class TelephoneNumber {

    private String officeAreaCode;

    private String officeNumber;

    public TelephoneNumber(String officeAreaCode, String officeNumber) {
        this.officeAreaCode = officeAreaCode;
        this.officeNumber = officeNumber;
    }

    public TelephoneNumber() {
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getOfficeAreaCode() {
        return officeAreaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.officeAreaCode = officeAreaCode;
    }

    public String getTelephoneNumber() {
        return this.getOfficeAreaCode() + "-" + this.getOfficeNumber();
    }
}
