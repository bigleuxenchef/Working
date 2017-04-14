package com.sample;

public  final class MyFacts {

	public long getA() {
		return a;
	}
	public void setA(long a) {
		this.a = a;
	}
	public long getB() {
		return b;
	}
	public void setB(long b) {
		this.b = b;
	}
	public long getC() {
		return c;
	}
	public void setC(long c) {
		this.c = c;
	}
	public long getK() {
		return k;
	}
	public void setK(long k) {
		this.k = k;
	}
	
	public void swapAB() {
		long temp;
		temp = a;
		a = b;
		b = temp;
		
	}
	
	public long a = 10;
	public long b = 9;
	public long c;
	public long k=0;

}