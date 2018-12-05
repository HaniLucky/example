package com.example.my.project.vo;

/**
 * Created by Covet on 2018/12/5.
 */
public class Dep {
    private Long uuid;
    private String name;
    private String tele;

    public Dep() {
        super();
    }

    public Dep(Long uuid, String name, String tele) {
        this.uuid = uuid;
        this.name = name;
        this.tele = tele;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
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
