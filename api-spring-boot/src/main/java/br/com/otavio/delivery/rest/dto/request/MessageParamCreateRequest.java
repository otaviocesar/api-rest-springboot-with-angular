package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.MessageParam;

public class MessageParamCreateRequest {
	
	private String name;
	
	private String value;

	private String author;
	
	public MessageParamCreateRequest(String name, String value, String author) {
		this.name = name;
		this.value = value;
		this.author = author;
	}
	
	public MessageParam transformToNewMessageParam() {
		return new MessageParam(
			null,
			name,
			value,
			author
		);
	}

	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}

	public String getAuthor() {
		return author;
	}
}
