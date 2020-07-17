package br.com.otavio.delivery.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.DeliveryJob;
import br.com.otavio.delivery.model.DeliveryJobStatus;

public class DeliveryJobRequest {

	private DeliveryJobStatus status;

	@JsonProperty("send-date")
	private LocalDateTime sendDate;
	
	public DeliveryJobRequest(DeliveryJobStatus status, LocalDateTime sendDate) {
		this.status = status;
		this.sendDate = sendDate;
	}
	
	public DeliveryJob transformToDeliveryJob() {
		return new DeliveryJob(status, sendDate);
	}
	
	public static DeliveryJob transformToDeliveryJob(DeliveryJobStatus status,
		LocalDateTime sendDate) {
		return new DeliveryJob(
			status, sendDate
		);
	}
	
	public void setStatus(DeliveryJobStatus status) {
		this.status = status;
	}

	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}
}
