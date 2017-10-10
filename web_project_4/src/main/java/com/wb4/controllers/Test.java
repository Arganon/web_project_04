package com.wb4.controllers;

import java.sql.Connection;
import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		ArrayList<Connection> lc = new ArrayList<Connection>(60);
		ConnectionPoolController cp = ConnectionPoolController.getInstance();

		
		
		for (int i = 0; i < 60; ++i) {
			lc.add(cp.getConnection());
			System.out.println(cp.getSize());
		}
		
		
	}
}
