package com.productoutofstock.Observer;
import com.productoutofstock.Observable.Product;
public class MobileAlertObserverImpl implements Customer {
    String userName;
    Product observable;
   public MobileAlertObserverImpl(String userName,Product observable){
       this.userName=userName;
       this.observable=observable;
   }
    @Override
    public void updated() {
       sendMsgOnMobile(userName,"Product is in stock Hurry Up!");
   }
    public void sendMsgOnMobile(String userName,String message){
            System.out.println("Message Sent to:" +userName);
            //send the Actual mail to the User

    }
}
