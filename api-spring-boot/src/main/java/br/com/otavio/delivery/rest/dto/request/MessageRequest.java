package br.com.otavio.delivery.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.MessageStatus;

public class MessageRequest {
	
	@JsonProperty("id-sender")
	private Long idSender;

	@JsonProperty("id-template")
	private Long idTemplate;

	@JsonProperty("date")
	private LocalDateTime sendDate;

	private LocalDateTime schedule;
	
	private MessageStatus status;
	
	public MessageRequest(Long idSender, Long idTemplate, LocalDateTime sendDate, LocalDateTime schedule,
		MessageStatus status) {
		this.idSender = idSender;
		this.idTemplate = idTemplate;
		this.sendDate = sendDate;
		this.schedule = schedule;
		this.status = status;
	}
	
	public Message transformToMessage() {
		return new Message(idSender, idTemplate, sendDate, schedule, status);
	}
	
	public static Message transformToMessage(Long idSender, Long idTemplate, LocalDateTime sendDate,
		LocalDateTime schedule, MessageStatus status) {
		return new Message(
			idSender, idTemplate, sendDate, schedule, status
		);
	}

	public void setIdSender(Long idSender) {
		this.idSender = idSender;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}

	public void setSchedule(LocalDateTime schedule) {
		this.schedule = schedule;
	}
	
	public void setStatus(MessageStatus status) {
		this.status = status;
	}
}
