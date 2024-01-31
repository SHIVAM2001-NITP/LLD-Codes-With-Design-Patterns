package com.chainofresponsibilityWithATM;

public abstract class DispenseChain {
	
	
	public abstract void setNextChain(DispenseChain next);
	
	public abstract void dispense(Currency currency);
	

}
