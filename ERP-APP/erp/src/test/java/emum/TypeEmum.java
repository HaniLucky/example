package emum;

public enum TypeEmum {

	/**
	 * pur采购  purchase
	 * sell 销售
	 */
	PUR("1", "订单"), SELL("2", "销售单");

	private String code;

	private String value;

	/**
	 * get方法
	 * @return
	 */
	public String value() {
		return value;
	}

	public String code() {
		return code;
	}

	// 构造函数 枚举的构造函数只能是私有
	private TypeEmum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	

	public static void main(String[] args) {
		System.err.println(TypeEmum.SELL.code());
		System.err.println(TypeEmum.SELL.value());
		System.err.println(TypeEmum.SELL.toString());
		
	}

}
