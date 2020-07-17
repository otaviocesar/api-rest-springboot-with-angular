package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Sender;

public class SenderCreateRequest {
	
	private String name;
	
	public SenderCreateRequest() {
	}

	public SenderCreateRequest(String name) {
		this.name = name;
	}
	
	public Sender transformToNewSender() {
		return new Sender(
			null,
			name
		);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
