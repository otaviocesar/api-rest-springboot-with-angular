package br.com.otavio.delivery.model;

import org.springframework.stereotype.Component;

@Component
public class Recipient {

	private Long id;

	private Long idMessage;

	private Long idDeliveryJob;

	private String address;
	
	private RecipientType type;
	
	private String addressTO;
	
	private RecipientType typeTO;
	
	private String addressCC;
	
	private RecipientType typeCC;
	
	private String addressBCC;
	
	private RecipientType typeBCC;

	public Recipient() {
	}

	public Recipient(
		Long id,
		Long idMessage,
		Long idDeliveryJob,
		String address,
		RecipientType type
	) {
		this.id = id;
		this.idMessage = idMessage;
		this.idDeliveryJob = idDeliveryJob;
		this.address = address;
		this.type = type;
	}

	public Recipient(
		Long idMessage,
		Long idDeliveryJob,
		String address,
		RecipientType type
	) {
		this.idMessage = idMessage;
		this.idDeliveryJob = idDeliveryJob;
		this.address = address;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public Long getIdDeliveryJob() {
		return idDeliveryJob;
	}

	public void setIdDeliveryJob(Long idDeliveryJob) {
		this.idDeliveryJob = idDeliveryJob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public RecipientType getType() {
		return type;
	}

	public void setType(RecipientType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipient other = (Recipient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getAddressTO() {
		return addressTO;
	}

	public void setAddressTO(String addressTO) {
		this.addressTO = addressTO;
	}

	public String getAddressCC() {
		return addressCC;
	}

	public void setAddressCC(String addressCC) {
		this.addressCC = addressCC;
	}
	
	public String getAddressBCC() {
		return addressBCC;
	}

	public void setAddressBCC(String addressBCC) {
		this.addressBCC = addressBCC;
	}
	
	public RecipientType getTypeTO() {
		return typeTO;
	}

	public void setTypeTO(RecipientType typeTO) {
		this.typeTO = typeTO;
	}
	
	public RecipientType getTypeCC() {
		return typeCC;
	}

	public void setTypeCC(RecipientType typeCC) {
		this.typeCC = typeCC;
	}

	public RecipientType getTypeBCC() {
		return typeBCC;
	}

	public void setTypeBCC(RecipientType typeBCC) {
		this.typeBCC = typeBCC;
	}
}
