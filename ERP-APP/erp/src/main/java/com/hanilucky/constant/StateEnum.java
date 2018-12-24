package com.hanilucky.constant;

/**
 *   state 0 未审核
 * 		 1 已审核
 * 		 2 已确认
 * 		 3 已结束
 * @author Administrator
 *
 */
public enum StateEnum {
	
	UNCHECKED("0"), CHECKED("1"), CONFIRMED("2"), OVER("3");

	private String state;

	// 枚举类的构造方法必须私有
	private StateEnum(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return String.valueOf(state);
	}
	
	public String value() {
		return state;
	}
}
