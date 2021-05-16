package com.domain;

public class ProvinceTopBean {
    public double currentConfirmedCount_percentage;
    public double confirmedCount_percentage;
    public int currentConfirmedCount;
    public int confirmedCount;
    public String provinceName;

    public void setCurrentConfirmedCount_percentage(double currentConfirmedCount_percentage) {
        this.currentConfirmedCount_percentage = currentConfirmedCount_percentage;
    }

    public void setConfirmedCount_percentage(double confirmedCount_percentage) {
        this.confirmedCount_percentage = confirmedCount_percentage;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
