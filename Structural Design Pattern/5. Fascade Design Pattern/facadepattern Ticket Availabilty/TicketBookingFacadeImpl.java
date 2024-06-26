package com.facadepattern;

public class TicketBookingFacadeImpl implements TicketBookingFacade {
	
	private TicketSystem ticketSystem;
	private PaymentSystem paymentSystem;
	private InvoiceSystem invoiceSystem;
	private NotificationSystem notificationSystem;
	
	
	public TicketBookingFacadeImpl() {
		this.ticketSystem=new TicketSystem();
		this.paymentSystem=new PaymentSystem();
		this.invoiceSystem=new InvoiceSystem();
		this.notificationSystem=new NotificationSystem();
		
	}
	

	@Override
	public void bookTicket(User user) {
		
		if(ticketSystem.checkTicketAvailability(user.getNoOfTickets())) {
		paymentSystem.paymentStatus(user.getId());
		invoiceSystem.generateInvoice(user.getId());
		notificationSystem.sendNotification(user.getEmail());
		}
		else
		{
			System.out.println(user.getNoOfTickets()+" tickets are not available");
		}
		
	}

}
