package br.com.otavio.delivery.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.model.RecipientType;

public class RecipientResponse {

	private Long id;
	
	@JsonProperty("id-message")
	private Long idMessage;

	@JsonProperty("id-delivery-job")
	private Long idDeliveryJob;

	private String address;

	private RecipientType type;

	private RecipientResponse(Long id, Long idMessage, Long idDeliveryJob, String address,
		RecipientType type) {
		this.id = id;
		this.idMessage = idMessage;
		this.idDeliveryJob = idDeliveryJob;
		this.address = address;
		this.type = type;
	}

	public static RecipientResponse transformToResponseDTO(Recipient recipient, Long messageId) {
		return new RecipientResponse(
			recipient.getId(), messageId, recipient.getIdDeliveryJob(), recipient.getAddress(),
			recipient.getType()
		);
	}

	public Long getId() {
		return id;
	}
	
	public Long getIdMessage() {
		return idMessage;
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
