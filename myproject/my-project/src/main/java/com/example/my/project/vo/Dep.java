package com.example.my.project.vo;

import javax.persistence.Id;

/**
 * Created by Covet on 2018/12/5.
 */
public class Dep {
    // 解决识别不了主键的问题
    @Id
    private Long uuid;
    private String name;
    private String tele;

    public Dep() {
        super();
       //  System.out.println("调用构造方法");
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
