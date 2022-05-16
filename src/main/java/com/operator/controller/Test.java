package com.operator.controller;

import java.math.BigDecimal;

public class Test {
	
	public static void main(String[] args) {
		BigDecimal big10 = new BigDecimal(10);
		BigDecimal big20 = new BigDecimal(20);
		System.out.println(big10.compareTo(big20) <= -1);
	}

}
