package com.cn.jee.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 父类的序列化与 Transient 关键字
 * 
 * @author 1002360
 * @version 2017年3月27日下午4:06:54 1002360 TODO
 */
public class SuperClassSerializable {

	public static void main(String[] args) {
		Child c = new Child();
		c.myTest2();
	}
}

class Parent {
	protected transient  int a = 1;
	protected transient  String s = "str";
}

class Child extends Parent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 初始时staticVar为5
	public static int staticVar = 5;

	private int b = 2;
	private String string = "string";

	/** 验证序列化并不保存静态变量 */
	@SuppressWarnings("static-access")
	public static void myTest1() {
		try {
			// 生成文件"result.obj",与pom.xml同级
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
			out.writeObject(new Child());
			out.close();

			// 序列化后修改为10
			Child.staticVar = 10;

			ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
			Child c = (Child) oin.readObject();
			oin.close();

			// 再读取，通过t.staticVar打印新的值 -> 10
			System.out.println(c.staticVar);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void myTest2() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("result.obj"));
			out.writeObject(new Child());
			out.close();

			ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
			Child c = (Child) oin.readObject();
			oin.close();

			System.out.println("父类a=" + super.a);
			System.out.println("父类s=" + super.s);
			System.out.println("子类b=" + c.b);
			System.out.println("子类string=" + c.string);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
