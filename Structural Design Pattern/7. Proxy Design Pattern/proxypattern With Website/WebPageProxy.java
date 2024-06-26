package com.proxypattern;

import java.util.ArrayList;
import java.util.List;

public class WebPageProxy implements WebPage{
	
	private WebPage webPage;
	
	
	public WebPageProxy() {
		webPage=new WebPageImpl();
	}
	
	private static List<String> blockedList;
	
	//Initializing and adding some blocked url's to the list
	static {
		blockedList=new ArrayList<>();
		blockedList.add("block1.com");
		blockedList.add("block2.com");
		blockedList.add("block3.com");
		blockedList.add("block4.com");
		
	}

	@Override
	public void renderPage(String url) throws Exception {
		if(blockedList.contains(url)) {
			throw new Exception("the url "+url+" you are requested is blocked ");
		}
		else {
			webPage.renderPage(url);
		}
			
		
	}

}
