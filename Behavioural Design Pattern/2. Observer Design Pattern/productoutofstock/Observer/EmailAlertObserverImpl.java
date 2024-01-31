package com.productoutofstock.Observer;

import com.productoutofstock.Observable.Product;
public class EmailAlertObserverImpl implements Customer{
    String emailId;
    Product observable;
    public EmailAlertObserverImpl(String emailId,Product observable){
        this.emailId=emailId;
        this.observable=observable;
    }
    @Override
    public void updated() {
        sendEmail(emailId,"Product is in stock Hurry Up!");
    }

    public void sendEmail(String emailId,String message){
        System.out.println("Mail Sent to:" +emailId);
        //send the Actual mail to the User
    }
}
