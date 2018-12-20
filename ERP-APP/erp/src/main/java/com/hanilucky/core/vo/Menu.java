package com.hanilucky.core.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Menu {
	private String menuid;

	private String menuname;

	private String icon;

	private String url;

	private String pid;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Menu> menus; // 下级菜单

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid == null ? null : menuid.trim();
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname == null ? null : menuname.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

}