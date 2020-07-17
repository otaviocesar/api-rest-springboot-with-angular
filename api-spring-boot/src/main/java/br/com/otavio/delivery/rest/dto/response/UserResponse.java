package br.com.otavio.delivery.rest.dto.response;

import br.com.otavio.delivery.model.User;

public class UserResponse {
	
	private Long id;

	private String name;

	private String mail;

	private String address;
	
	private UserResponse(Long id, String name, String mail, String address) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.address = address;
	}
	
	public static UserResponse transformToResponseDTO(User user) {
		return new UserResponse(
			user.getId(), user.getName(), user.getMail(), user.getAddress()
		);
	}
	
	public Long getId() {
		return id;
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
