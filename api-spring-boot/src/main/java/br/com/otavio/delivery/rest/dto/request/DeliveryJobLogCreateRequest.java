package br.com.otavio.delivery.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.DeliveryJobLog;

public class DeliveryJobLogCreateRequest {

	@JsonProperty("send-date")
	private LocalDateTime sendDate;
	
	private String error;
	
	public DeliveryJobLogCreateRequest(LocalDateTime sendDate, String error) {
		this.sendDate = sendDate;
		this.error = error;
	}
	
	public DeliveryJobLog transformToNewDeliveryJobLog() {
		return new DeliveryJobLog(
			null,
			sendDate,
			error
		);
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
	
	public String getError() {
		return error;
	}
}
