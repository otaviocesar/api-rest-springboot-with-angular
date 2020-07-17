package br.com.otavio.delivery.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.DeliveryJobLog;

public class DeliveryJobLogRequest {
	
	@JsonProperty("id-delivery-job")
	private Long idDeliveryJob;
	
	@JsonProperty("send-date")
	private LocalDateTime sendDate;
	
	private String error;

	public DeliveryJobLogRequest(Long idDeliveryJob, LocalDateTime sendDate, String error) {
		this.idDeliveryJob = idDeliveryJob;
		this.sendDate = sendDate;
		this.error = error;
	}

	public DeliveryJobLog transformToDeliveryJobLog() {
		return new DeliveryJobLog(idDeliveryJob, sendDate, error);
	}

	public static DeliveryJobLog transformToDeliveryJobLog(Long idDeliveryJob, LocalDateTime sendDate,
		String error) {
		return new DeliveryJobLog(
			idDeliveryJob, sendDate, error
		);
	}
	
	public void setIdDeliveryJob(Long idDeliveryJob) {
		this.idDeliveryJob = idDeliveryJob;
	}
	
	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}
	
	public void setError(String error) {
		this.error = error;
	}
}
