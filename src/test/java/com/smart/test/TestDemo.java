package com.smart.test;

public class TestDemo {

	public static void main(String[] args) {
		B b = new B();
	}

}
class A{
	public A() {
		System.out.println("hello A");
	}
	{
		System.out.println("I'am A");
	}
	static{
		System.out.println("A test");
	}
}
class B extends A{
	public B() {
		System.out.println("hello B");
	}
	{
		System.out.println("I'am B");
	}
	static{
		System.out.println("B test");
	}
}