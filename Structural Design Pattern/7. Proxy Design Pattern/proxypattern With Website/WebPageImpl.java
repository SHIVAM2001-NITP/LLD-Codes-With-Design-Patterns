package com.proxypattern;

public class WebPageImpl implements WebPage {
	

	@Override
	public void renderPage(String url) throws Exception {
		
		System.out.println("url "+url +" is rendered successfully");
		
	}

}
