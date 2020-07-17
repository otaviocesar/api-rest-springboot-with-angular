package br.com.otavio.delivery.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.DeliveryJob;

public class DeliveryJobCreateRequest {
	
	@JsonProperty("send-date")
	private LocalDateTime sendDate;
	
	public DeliveryJobCreateRequest(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}
	
	public DeliveryJob transformToNewDeliveryJob() {
		return new DeliveryJob(
			null,
			null,
			sendDate
		);
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
}
