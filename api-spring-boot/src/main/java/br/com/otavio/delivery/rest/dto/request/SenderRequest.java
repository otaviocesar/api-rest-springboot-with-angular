package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Sender;

public class SenderRequest {

	private String name;

	public SenderRequest(String name) {
		this.name = name;
	}

	public Sender transformToSender() {
		return new Sender(name);
	}

	public static Sender transformToSender(String name) {
		return new Sender(
			name
		);
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
