package br.com.otavio.delivery.rest.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.MessageStatus;

public class MessageResponse {

	private Long id;

	@JsonProperty("id-sender")
	private Long idSender;

	@JsonProperty("id-template")
	private Long idTemplate;

	@JsonProperty("date")
	private LocalDateTime sendDate;

	private LocalDateTime schedule;

	private MessageStatus status;

	private MessageResponse(Long id, Long idSender, Long idTemplate, LocalDateTime sendDate, LocalDateTime schedule,
		MessageStatus status) {
		this.id = id;
		this.idSender = idSender;
		this.idTemplate = idTemplate;
		this.sendDate = sendDate;
		this.schedule = schedule;
		this.status = status;
	}

	public static MessageResponse transformToResponseDTO(Message message) {
		return new MessageResponse(
			message.getId(), message.getIdSender(), message.getIdTemplate(), message.getSendDate(),
			message.getSchedule(),
			message.getStatus()
		);
	}
	
	public static MessageResponse responseDTO(Message message) {
		return new MessageResponse(
			message.getId(), message.getIdSender(), message.getIdTemplate(), message.getSendDate(),
			message.getSchedule(),
			MessageStatus.BUILDING
		);
	}

	public Long getId() {
		return id;
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

	public MessageStatus getStatus() {
		return status;
	}
}
