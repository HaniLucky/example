package emum;

/**
 *   state 0 未审核
 * 		 1 已审核
 * 		 2 已确认
 * 		 3 已结束
 * @author Administrator
 *
 */
public enum StateEnum {
	/**
	 * 枚举的使用场景:代替常量类
	 * 1.枚举类型的构造方法只能用private 修饰
	 * 2.常用Api
	 * 		values()   获取所有实例
	 * 		valueOf()  根据实例名获取对象
	 */

	UNCHECKED("0", "未审核"), CHECKED("1", "已审核"), CONFIRMED("2", "已确认"), OVER("3", "已结束");

	private String code;
	private String value;

	// 枚举类的构造方法必须私有
	private StateEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}

	// 用过get方法取值
	public String code() {
		return code;
	}

	public String value() {
		return value;
	}

	public static void main(String[] args) {
		System.out.println(StateEnum.UNCHECKED.code());
		System.out.println(StateEnum.UNCHECKED.value());

		for (StateEnum string : StateEnum.values()) {
			System.err.println(string);
		}
	}

}
