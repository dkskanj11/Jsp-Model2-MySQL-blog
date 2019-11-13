package com.cos.util;

public class test {

	public static void main(String[] args) {
		byte b = 117;
		
		//byte 를 string 으로
		String s = Integer.toString(b);
		System.out.println(s);   
		
		// 00000000 00000000 00000000 00000001 = binary
		int i = 1;
		System.out.println(Integer.toBinaryString(i));
		
		
		// 11111111 11111111 11111111 10010110 = binary
		int  j = 150;
		System.out.println(Integer.toBinaryString(j));
		
		System.out.println(Integer.toBinaryString(j & 0xff));
	}
}
