package com.facadepattern;

public class TicketSystem {
	
	private static final int TOTAL_TICKETS=50;
	
	
	public boolean checkTicketAvailability(int ticketsNeeded) {
		int ticketsLeft=TOTAL_TICKETS-ticketsNeeded;
		if(ticketsLeft<=0) {
			return false;
		}
		return true;
	}
	

}
