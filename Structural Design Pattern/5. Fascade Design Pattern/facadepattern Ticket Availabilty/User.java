package com.facadepattern;

public class User {
	
	private String id;
	private String name;
	private String email;
	private int noOfTickets;
	
	
	public User() {
		super();
	}
	public User(String id, String name, String email,int noOfTickets ) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.noOfTickets=noOfTickets;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

}
