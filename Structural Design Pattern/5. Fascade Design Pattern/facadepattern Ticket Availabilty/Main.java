package com.facadepattern;

public class Main {

	public static void main(String[] args) {
		
		
		/*
		 * If we don't make use of Facade Design Pattern Client has to deal with the all
		 * the classes and methods that requires for ticket booking
		 */
		 
		 
		 
		/*
		 * User user=new User("121","kavan", "kavan@gmail.com",2); TicketSystem ts=new
		 * TicketSystem(); boolean ticketAvailability =
		 * ts.checkTicketAvailability(user.getNoOfTickets()); if(ticketAvailability) {
		 * PaymentSystem ps=new PaymentSystem(); ps.paymentStatus(user.getId());
		 * InvoiceSystem is=new InvoiceSystem(); is.generateInvoice(user.getId());
		 * NotificationSystem ns= new NotificationSystem();
		 * ns.sendNotification(user.getEmail()); }
		 */
		
		
		
		/*
		 * By making use of Facade Design pattern we can reduce the client work ,just by
		 * making the client to call a method in Facade class which take care of the
		 * required steps to book tickets
		 */
		
		  User user2=new User("121","kavan", "kavan@gmail.com",52); TicketBookingFacade
		  ticketBooking=new TicketBookingFacadeImpl(); ticketBooking.bookTicket(user2);
		 
		 
		
	}

}
