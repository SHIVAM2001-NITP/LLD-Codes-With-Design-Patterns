package com.youtubeandsubscriber.Observable;

import com.youtubeandsubscriber.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Channel implements YoutubeChannel {
	String name;
	public Channel(String name) {
		this.name=name;
	}
	List<Observer> subscribers=new ArrayList<>();

	@Override
	public void subscribe(Observer sub) {
	subscribers.add(sub);
	System.out.println("subscriber subscribed to "+name+" channel...! ");
		
	}

	@Override
	public void unSubscribe(Observer sub) {
		subscribers.remove(sub);
		System.out.println("subscriber UnSubscribed from "+ name+"...! ");
	}

	@Override
	public void notifySubscriber(String reason) {
		for(Observer sub: subscribers) {
			sub.Notified(reason,this.name);
		}
		
	}
	
	

}
