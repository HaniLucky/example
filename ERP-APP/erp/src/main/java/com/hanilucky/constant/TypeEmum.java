package com.hanilucky.constant;

public enum TypeEmum {
	
	/**
	 * pur采购  purchase
	 * sell 销售
	 */
	PUR("1"),SELL("2");

	private String type;

	
	
	public String value() {
		return type;
	}


	// 构造函数 枚举的构造函数只能是私有
	private TypeEmum(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.type);
	}

}
