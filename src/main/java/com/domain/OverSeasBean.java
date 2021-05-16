package com.domain;

public class OverSeasBean {
    public String addOverseasCount;   //新增境外输入
    public String totalOverseasCount; //总境外输入
    public String date;

    public OverSeasBean(String addOverseasCount, String totalOverseasCount, String date) {
        this.addOverseasCount = addOverseasCount;
        this.totalOverseasCount = totalOverseasCount;
        this.date = date;
    }

    public OverSeasBean() { }

    public void setAddOverseasCount(String addOverseasCount) {
        this.addOverseasCount = addOverseasCount;
    }

    public void setTotalOverseasCount(String totalOverseasCount) {
        this.totalOverseasCount = totalOverseasCount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddOverseasCount() {
        return addOverseasCount;
    }

    public String getTotalOverseasCount() {
        return totalOverseasCount;
    }

    public String getDate() {
        return date;
    }
}
