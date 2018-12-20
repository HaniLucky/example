package com.hanilucky.core.vo;

public class RoleMenu {
    private Integer roleuuid;

    private String menuuuid;
    
    

    public RoleMenu() {
		super();
	}

    
	public RoleMenu(Integer roleuuid, String menuuuid) {
		super();
		this.roleuuid = roleuuid;
		this.menuuuid = menuuuid;
	}


	public Integer getRoleuuid() {
        return roleuuid;
    }

    public void setRoleuuid(Integer roleuuid) {
        this.roleuuid = roleuuid;
    }

    public String getMenuuuid() {
        return menuuuid;
    }

    public void setMenuuuid(String menuuuid) {
        this.menuuuid = menuuuid == null ? null : menuuuid.trim();
    }
}