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
	}

}
