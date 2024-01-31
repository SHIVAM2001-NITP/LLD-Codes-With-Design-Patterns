package com.productoutofstock;

import com.productoutofstock.Observable.Iphone;
import com.productoutofstock.Observable.Product;
import com.productoutofstock.Observer.Customer;
import com.productoutofstock.Observer.EmailAlertObserverImpl;
public class Stock {
	public static void main(String[] args) {
		Product iphone=new Iphone();

		Customer customer1=new EmailAlertObserverImpl("pavan@gmail.com",iphone);
		Customer customer2=new EmailAlertObserverImpl("shivam@gmail.com",iphone);
		Customer customer3=new EmailAlertObserverImpl("shivam Kumar",iphone);

		iphone.add(customer1);
		iphone.add(customer2);
		iphone.add(customer3);

		iphone.setStockCount(10);
	}

}
