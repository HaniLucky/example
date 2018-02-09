package com.xxx.covet.pojo;

import java.util.Date;

public class User {
    private Integer cId;

    private String cName;

    private String cSex;

    private String cBirthday;

    private Date cCreateTime;

    private Date cModifyTime;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName == null ? null : cName.trim();
    }

    public String getcSex() {
        return cSex;
    }

    public void setcSex(String cSex) {
        this.cSex = cSex == null ? null : cSex.trim();
    }

    public String getcBirthday() {
        return cBirthday;
    }

    public void setcBirthday(String cBirthday) {
        this.cBirthday = cBirthday == null ? null : cBirthday.trim();
    }

    public Date getcCreateTime() {
        return cCreateTime;
    }

    public void setcCreateTime(Date cCreateTime) {
        this.cCreateTime = cCreateTime;
    }

    public Date getcModifyTime() {
        return cModifyTime;
    }

    public void setcModifyTime(Date cModifyTime) {
        this.cModifyTime = cModifyTime;
    }

	@Override
	public String toString() {
		return "User [cId=" + cId + ", cName=" + cName + ", cSex=" + cSex + ", cBirthday=" + cBirthday
				+ ", cCreateTime=" + cCreateTime + ", cModifyTime=" + cModifyTime + "]";
	}
    
    
}