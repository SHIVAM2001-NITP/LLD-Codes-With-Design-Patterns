package com.chainofresponsibilityWithATM;

public class Rupees50Dispenser extends DispenseChain{

	private DispenseChain chain;
	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.chain=nextChain;
		
	}

	@Override
	public void dispense(Currency currency) {
		//long currency.getAmount()=currency.getcurrency.getAmount()();
		if(currency.getAmount()>=50) {
			long num=currency.getAmount()/50;
			long remainder=currency.getAmount()%50;
			System.out.println("Dispensing "+num+" ₹50 rupees notes");
			if(remainder!=0) {
				this.chain.dispense(new Currency(remainder));
			}
			
			
		}
		
		else{
			this.chain.dispense(currency);
		}
	}

}
