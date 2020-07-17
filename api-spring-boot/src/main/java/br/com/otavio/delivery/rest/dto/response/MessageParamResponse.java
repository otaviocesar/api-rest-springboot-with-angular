package br.com.otavio.delivery.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.MessageParam;

public class MessageParamResponse {

	private Long id;

	@JsonProperty("id-message")
	private Long idMessage;

	private String name;

	private String value;

	private String author;

	private MessageParamResponse(Long id, Long idMessage, String name, String value, String author) {
		this.id = id;
		this.idMessage = idMessage;
		this.name = name;
		this.value = value;
		this.author = author;
	}

	public static MessageParamResponse transformToResponseDTO(MessageParam messageParam, Long messageId) {
		return new MessageParamResponse(
			messageParam.getId(), messageId, messageParam.getName(), messageParam.getValue(),
			messageParam.getAuthor()
		);
	}

	public Long getId() {
		return id;
	}

	public Long getIdMessage() {
		return idMessage;
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
