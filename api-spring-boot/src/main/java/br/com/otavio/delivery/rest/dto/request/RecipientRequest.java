package br.com.otavio.delivery.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.model.RecipientType;

public class RecipientRequest {

	@JsonProperty("id-message")
	private Long idMessage;

	@JsonProperty("id-delivery-job")
	private Long idDeliveryJob;

	private String address;

	private RecipientType type;
	
	public RecipientRequest(Long idMessage, Long idDeliveryJob, String address, RecipientType type) {
		this.idMessage = idMessage;
		this.idDeliveryJob = idDeliveryJob;
		this.address = address;
		this.type = type;
	}
	
	public Recipient transformToRecipient() {
		return new Recipient(idMessage, idDeliveryJob, address, type);
	}
	
	public static Recipient transformToRecipient(Long idMessage, Long idDeliveryJob, String address,
		RecipientType type) {
		return new Recipient(
			idMessage, idDeliveryJob, address, type
		);
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public void setIdDeliveryJob(Long idDeliveryJob) {
		this.idDeliveryJob = idDeliveryJob;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setType(RecipientType type) {
		this.type = type;
	}
}
