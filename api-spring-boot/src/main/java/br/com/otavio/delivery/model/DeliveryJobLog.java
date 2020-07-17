package br.com.otavio.delivery.model;

import java.time.LocalDateTime;

public class DeliveryJobLog {
	
	private Long id;
	
	private Long idDeliveryJob;
	
	private LocalDateTime sendDate;
	
	private String error;
	
	public DeliveryJobLog() {
	}
	
	public DeliveryJobLog(
		Long id,
		Long idDeliveryJob,
		LocalDateTime sendDate,
		String error
	) {
		this.id = id;
		this.idDeliveryJob = idDeliveryJob;
		this.sendDate = sendDate;
		this.error = error;
	}
	
	public DeliveryJobLog(
		Long idDeliveryJob,
		LocalDateTime sendDate,
		String error
	) {
		this.idDeliveryJob = idDeliveryJob;
		this.sendDate = sendDate;
		this.error = error;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdDeliveryJob() {
		return idDeliveryJob;
	}
	
	public void setIdDeliveryJob(Long idDeliveryJob) {
		this.idDeliveryJob = idDeliveryJob;
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
	
	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
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
		DeliveryJobLog other = (DeliveryJobLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
