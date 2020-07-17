package br.com.otavio.delivery.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.model.RecipientType;

public class RecipientCreateRequest {
	
	@JsonProperty("id-delivery-job")
	private Long idDeliveryJob;
	
	private String address;
	
	private RecipientType type;
	
	public RecipientCreateRequest(Long idDeliveryJob, String address, RecipientType type) {
		this.idDeliveryJob = idDeliveryJob;
		this.address = address;
		this.type = type;
	}
	
	public Recipient transformToNewRecipient() {
		return new Recipient(
			null,
			idDeliveryJob,
			address,
			type
		);
	}
	
	public Long getIdDeliveryJob() {
		return idDeliveryJob;
	}
	
	public String getAddress() {
		return address;
	}
	
	public RecipientType getType() {
		return type;
	}
}
