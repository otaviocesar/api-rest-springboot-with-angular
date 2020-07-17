package br.com.otavio.delivery.rest.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.DeliveryJobLog;

public class DeliveryJobLogResponse {
	
	private Long id;

	@JsonProperty("id-delivery-job")
	private Long idDeliveryJob;
	
	@JsonProperty("send-date")
	private LocalDateTime sendDate;
	
	private String error;
	
	private DeliveryJobLogResponse(Long id, Long idDeliveryJob, LocalDateTime sendDate, String error) {
		this.id = id;
		this.idDeliveryJob = idDeliveryJob;
		this.sendDate = sendDate;
		this.error = error;
	}
	
	public static DeliveryJobLogResponse transformToResponseDTO(DeliveryJobLog deliveryJobLog, Long deliveryJobId) {
		return new DeliveryJobLogResponse(
			deliveryJobLog.getId(), deliveryJobId, deliveryJobLog.getSendDate(),
			deliveryJobLog.getError()
		);
	}
	
	public Long getId() {
		return id;
	}

	public Long getIdDeliveryJob() {
		return idDeliveryJob;
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
	
	public String getError() {
		return error;
	}
}
