package com.proxypattern;

public class Main {

	public static void main(String[] args) {
		WebPage wp=new WebPageProxy();
		try {
//			wp.renderPage("abc.com");
//			System.out.println("process successfully completed");

			wp.renderPage("block1.com");
			System.out.println("process successfully completed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
