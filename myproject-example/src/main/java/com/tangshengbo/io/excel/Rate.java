package com.tangshengbo.io.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author Tangshengbo
 * @date 2019/8/20
 */
public class Rate extends BaseRowModel {

    @ExcelProperty(value = "company", index = 0)
    private String company;

    @ExcelProperty(value = "detail", index = 3)
    private String detail;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
