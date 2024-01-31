package com.youtubeandsubscriber;

import com.youtubeandsubscriber.Observable.Channel;
import com.youtubeandsubscriber.Observable.YoutubeChannel;
import com.youtubeandsubscriber.Observer.Observer;
import com.youtubeandsubscriber.Observer.Subscriber;

public class Main {
public static void main(String[] args) {
	YoutubeChannel channel1=new Channel("learn java programming");
	Observer sub1= new Subscriber("pavan");
	Observer sub2= new Subscriber("kavan");
	channel1.subscribe(sub1);
	channel1.subscribe(sub2);
	//yt.unSubscribe(sub1);
	channel1.notifySubscriber("reached 10k subs");
	
	Observer sub3= new Subscriber("kavannn");
	channel1.subscribe(sub3);
	channel1.unSubscribe(sub2);
	channel1.notifySubscriber("new video uploaded");
	YoutubeChannel channel2 = new Channel("leran design patterns");
	channel2.subscribe(sub2);
	channel2.notifySubscriber("one year anniversary");
	channel2.subscribe(sub3);
	channel2.notifySubscriber("new short released");
}
}
