package br.com.otavio.delivery.rest.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.DeliveryJob;
import br.com.otavio.delivery.model.DeliveryJobStatus;

public class DeliveryJobResponse {
	
	private Long id;
	
	private DeliveryJobStatus status;
	
	@JsonProperty("send-date")
	private LocalDateTime sendDate;
	
	private DeliveryJobResponse(Long id, DeliveryJobStatus status, LocalDateTime sendDate) {
		this.id = id;
		this.status = status;
		this.sendDate = sendDate;
	}
	
	public static DeliveryJobResponse transformToResponseDTO(DeliveryJob deliveryJob) {
		return new DeliveryJobResponse(
			deliveryJob.getId(), deliveryJob.getStatus(), deliveryJob.getSendDate()
		);
	}
	
	public Long getId() {
		return id;
	}
	
	public DeliveryJobStatus getStatus() {
		return status;
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
}
