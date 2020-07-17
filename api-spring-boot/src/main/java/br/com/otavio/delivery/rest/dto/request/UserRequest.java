package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.User;

public class UserRequest {
	
	private String name;

	private String mail;

	private String address;
	
	public UserRequest(String name, String mail, String address) {
		this.name = name;
		this.mail = mail;
		this.address = address;
	}
	
	public User transformToUser() {
		return new User(name, mail, address);
	}
	
	public static User transformToUser(String name, String mail, String address) {
		return new User(
			name,
			mail,
			address
		);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
