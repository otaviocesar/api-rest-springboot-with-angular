package br.com.otavio.delivery.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.Message;

public class MessageCreateRequest {
	
	@JsonProperty("id-sender")
	private Long idSender;
	
	@JsonProperty("id-template")
	private Long idTemplate;
	
	@JsonProperty("date")
	private LocalDateTime sendDate;
	
	private LocalDateTime schedule;
	
	public MessageCreateRequest(Long idSender, Long idTemplate, LocalDateTime sendDate, LocalDateTime schedule) {
		this.idSender = idSender;
		this.idTemplate = idTemplate;
		this.sendDate = sendDate;
		this.schedule = schedule;
	}
	
	public Message transformToNewMessage() {
		return new Message(
			null,
			idSender,
			idTemplate,
			sendDate,
			schedule,
			null
		);
	}

	public Long getIdSender() {
		return idSender;
	}
	
	public Long getIdTemplate() {
		return idTemplate;
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
	
	public LocalDateTime getSchedule() {
		return schedule;
	}
}
