package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.User;

public class UserCreateRequest {

	private String name;

	private String mail;

	private String address;

	public UserCreateRequest() {
	}
	
	public UserCreateRequest(String name, String mail, String address) {
		this.name = name;
		this.mail = mail;
		this.address = address;
	}

	public User transformToNewUser() {
		return new User(
			null,
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
