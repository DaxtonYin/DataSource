package com.domain;

public class WorldBean {
    private String Name;   //名称
    private String value; //累计确诊人数

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
