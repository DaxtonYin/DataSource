package com.domain;

public class CovidBean {
    private String provinceName;   //省份名称
    private String provinceShortName;  //省份简称
    private String cityName;
    private String currentConfirmedCount;  //当前确诊人数
    private String confirmedCount;    //累计确诊人数
    private String suspectedCount;   //疑似病例人数
    private String curedCount;      //治愈人数
    private String deadCount;       //死亡人数
    private String locationId;      //位置Id
    private String pid;             //位置Id
    private String statisticsData;  //统计数据
    private String cities;          //下属城市

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(String currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public String getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(String confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public String getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(String suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public String getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(String curedCount) {
        this.curedCount = curedCount;
    }

    public String getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(String deadCount) {
        this.deadCount = deadCount;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getStatisticsData() {
        return statisticsData;
    }

    public void setStatisticsData(String statisticsData) {
        this.statisticsData = statisticsData;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "CovidBean{" +
                "provinceName='" + provinceName + '\'' +
                ", provinceShortName='" + provinceShortName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", currentConfirmedCount='" + currentConfirmedCount + '\'' +
                ", confirmedCount='" + confirmedCount + '\'' +
                ", suspectedCount='" + suspectedCount + '\'' +
                ", curedCount='" + curedCount + '\'' +
                ", deadCount='" + deadCount + '\'' +
                ", locationId='" + locationId + '\'' +
                ", pid='" + pid + '\'' +
                ", statisticsData='" + statisticsData + '\'' +
                ", cities='" + cities + '\'' +
                '}';
    }
}
