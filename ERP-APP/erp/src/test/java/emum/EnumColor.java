package emum;

/** 
 * @Description: 颜色枚举类，所有枚举类默认继承public abstract class Enum
 */
public enum EnumColor {
	RED(1, "红色"), GREEN(2, "绿色"), BLANK(3, "白色"), YELLO(4, "黄色"), BLUE("蓝色"), BLACK("黑色");

	private int code;
	private String color;

	// 构造函数
	private EnumColor(int code, String color) {
		this.code = code;
		this.color = color;
	}

	private EnumColor(String color) {
		this.color = color;
	}

	// 枚举属性的getter方法
	public int getCode() {
		return code;
	}

	public String getColor() {
		return color;
	}

	// 重写toString()方法
	@Override
	public String toString() {
		return "[code:" + this.code + ",color:" + this.color + "]";
	}

	// 提供静态方法，通过code获取color的值
	public static String colorOf(int code) {
		// EnumColor.values()获取枚举所有实例对象，还有通过名称获取方式EnumColor.valueOf("RED")、EnumColor.valueOf(EnumColor.class,
		// "RED")
		// 遍历枚举所有实例对象
		for (EnumColor color : EnumColor.values()) {
			if (color.getCode() == code) {
				return color.getColor();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		// 通过静态方式直接获取枚举实例对象，打印实例对象自动调用toString()方法
		System.out.println(EnumColor.RED);

		// 获取实例对象之后，获取相关属性信息
		System.out.println(EnumColor.GREEN.getCode());
		System.out.println(EnumColor.GREEN.getColor());

		// 通过valueOf()方法获取枚举实例对象，并获取对象相关信息
		System.out.println(EnumColor.valueOf("BLANK").getCode());
		System.out.println(EnumColor.valueOf(EnumColor.class, "BLANK").getColor());

		// 通过自定义的静态方法colorOf(int code)获取color信息
		// YELLO(4,"黄色")
		System.out.println(EnumColor.colorOf(4));

	}

}
