package com.domain;

public class WorldAddBean {
    private String Name;   //名称
    private String addConfirmCount; //新增确诊人数
    public String heal;      //各地累计治疗人数
    public String death;     //各地累计死亡人数

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddConfirmCount() {
        return addConfirmCount;
    }

    public void setAddConfirmCount(String addConfirmCount) {
        this.addConfirmCount = addConfirmCount;
    }

    public String getHeal() {
        return heal;
    }

    public void setHeal(String heal) {
        this.heal = heal;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }


}
