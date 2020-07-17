package br.com.otavio.delivery.model;

import java.time.LocalDateTime;

public class DeliveryJob {
	
	private Long id;
	
	private DeliveryJobStatus status;
	
	private LocalDateTime sendDate;
	
	public DeliveryJob() {
	}
	
	public DeliveryJob(
		Long id,
		DeliveryJobStatus status,
		LocalDateTime sendDate
	) {
		this.id = id;
		this.status = status;
		this.sendDate = sendDate;
	}
	
	public DeliveryJob(
		DeliveryJobStatus status,
		LocalDateTime sendDate
	) {
		this.status = status;
		this.sendDate = sendDate;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public DeliveryJobStatus getStatus() {
		return status;
	}
	
	public void setStatus(DeliveryJobStatus status) {
		this.status = status;
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
	
	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
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
		DeliveryJob other = (DeliveryJob) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
