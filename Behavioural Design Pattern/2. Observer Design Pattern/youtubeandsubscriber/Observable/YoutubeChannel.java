package com.youtubeandsubscriber.Observable;

import com.youtubeandsubscriber.Observer.Observer;

public interface YoutubeChannel {
	
	void subscribe(Observer sub);
	void unSubscribe(Observer sub);
	void notifySubscriber(String reason);
	
	

}
