package com.example.my.project.vo;

/**
 * Created by Covet on 2018/12/5.
 */
public class Dep {
    private String uuid;
    private String name;
    private String tele;

    public Dep() {
        super();
    }

    public Dep(String uuid, String name, String tele) {
        this.uuid = uuid;
        this.name = name;
        this.tele = tele;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }
}
