package com.lab.ui.beans;

import java.util.Date;

public class TestJasper 
{

	public static void main(String[] args) 
	{
		 Date today = new Date();
		 System.out.println(today);
		 System.out.println(today.getTime());
		 System.out.println(today.toLocaleString());
		 today = new Date(170,0, 1);
		 System.out.println(today);
		 System.out.println(today.getTime());

	}

}
