package com.productoutofstock.Observable;

import com.productoutofstock.Observer.Customer;

public interface Product {
	
	void add(Customer observer);
	void remove(Customer observer);
	void notifyCustomer();

	void setStockCount(int newStockAdded);

	public int getStockCount();

}
