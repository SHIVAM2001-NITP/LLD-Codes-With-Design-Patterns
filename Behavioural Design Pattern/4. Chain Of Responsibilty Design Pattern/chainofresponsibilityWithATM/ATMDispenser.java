package com.chainofresponsibilityWithATM;

import java.util.Scanner;

public class ATMDispenser {
	
	DispenseChain c1;
	
	public ATMDispenser() {
		this.c1=new Rupees50Dispenser();
		DispenseChain c2=new Rupees20Dispenser();
		DispenseChain c3=new Rupees10Dispenser();
		c1.setNextChain(c2);
		c2.setNextChain(c3);
	}

	public static void main(String[] args) {
		ATMDispenser atmDispenser=new ATMDispenser();
		
		
			long amount=0;
			System.out.println("enter amount to dispense");
			Scanner sc=new Scanner(System.in);
			amount=sc.nextLong();
			if(amount%10!=0) {
				System.out.println("amount should be mulitiple of 10s ");
				return;
			}
			atmDispenser.c1.dispense(new Currency(amount));


	}

}
