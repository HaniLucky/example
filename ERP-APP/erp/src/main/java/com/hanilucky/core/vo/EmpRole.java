package com.hanilucky.core.vo;

public class EmpRole {
	private Integer empuuid;

	private Integer roleuuid;

	public EmpRole() {
		super();
	}

	public EmpRole(Integer empuuid, Integer roleuuid) {
		super();
		this.empuuid = empuuid;
		this.roleuuid = roleuuid;
	}

	public Integer getEmpuuid() {
		return empuuid;
	}

	public void setEmpuuid(Integer empuuid) {
		this.empuuid = empuuid;
	}

	public Integer getRoleuuid() {
		return roleuuid;
	}

	public void setRoleuuid(Integer roleuuid) {
		this.roleuuid = roleuuid;
	}
}