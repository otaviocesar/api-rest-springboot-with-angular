package br.com.otavio.delivery.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.MessageParam;

public class MessageParamRequest {

	@JsonProperty("id-message")
	private Long idMessage;

	private String name;

	private String value;
	
	private String author;
	
	public MessageParamRequest(Long idMessage, String name, String value, String author) {
		this.idMessage = idMessage;
		this.name = name;
		this.value = value;
		this.author = author;
	}
	
	public MessageParam transformToMessageParam() {
		return new MessageParam(idMessage, name, value, author);
	}
	
	public static MessageParam transformToMessageParam(Long idMessage, String name, String value, String author) {
		return new MessageParam(
			idMessage, name, value, author
		);
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
}
