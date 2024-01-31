package com.youtubeandsubscriber.Observer;

import com.youtubeandsubscriber.Observer.Observer;

public class Subscriber implements Observer {
	
	String name;
	
	
	public String getName() {
		return name;
	}
	public Subscriber(String name) {
		this.name=name;
	}
	@Override
	public void Notified(String reason,String channelName) {
		System.out.println("from "+channelName+" : -->  Hey "+this.name+" "+reason);
		
	}
	
	

}
