package com.productoutofstock.Observable;
import com.productoutofstock.Observer.Customer;
import java.util.ArrayList;
import java.util.List;
public class Iphone implements Product {
	List<Customer> customers = new ArrayList<>();
	private int iphoneInStock=0;
	@Override
	public void add(Customer observer) {
		//System.out.println("customer is opted to be notified");
		customers.add(observer);
	}
	@Override
	public void remove(Customer observer) {
		System.out.println("customer is opted out from being notified");
		customers.remove(observer);
	}

	@Override
	public void notifyCustomer() {
		for (Customer observer : customers) {
			observer.updated();
		}
	}

	@Override
	public void setStockCount(int newStockAdded) {
		if(iphoneInStock==0){
			notifyCustomer();
		}
		iphoneInStock=iphoneInStock+newStockAdded;
	}

	@Override
	public int getStockCount() {
		return iphoneInStock;
	}
}
