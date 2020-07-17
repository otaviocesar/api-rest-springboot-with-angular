package br.com.otavio.delivery.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Message {
	
	private Long id;
	
	private Long idSender;
	
	private Long idTemplate;
	
	private LocalDateTime sendDate;
	
	private LocalDateTime schedule;
	
	private String sendDateFormat;
	
	private String scheduleFormat;
	
	private MessageStatus status;
	
	public Message() {
	}
	
	public Message(
		Long id,
		Long idSender,
		Long idTemplate,
		LocalDateTime sendDate,
		LocalDateTime schedule,
		MessageStatus status
	) {
		this.id = id;
		this.idSender = idSender;
		this.idTemplate = idTemplate;
		this.sendDate = sendDate;
		this.schedule = schedule;
		this.status = status;
	}
	
	public Message(
		Long idSender,
		Long idTemplate,
		LocalDateTime sendDate,
		LocalDateTime schedule,
		MessageStatus status
	) {
		this.idSender = idSender;
		this.idTemplate = idTemplate;
		this.sendDate = sendDate;
		this.schedule = schedule;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdSender() {
		return idSender;
	}
	
	public void setIdSender(Long idSender) {
		this.idSender = idSender;
	}
	
	public Long getIdTemplate() {
		return idTemplate;
	}
	
	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}
	
	public LocalDateTime getSendDate() {
		return sendDate;
	}
	
	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}
	
	public String getSendDateFormat() {
		return sendDateFormat;
	}
	
	public void setSendDateFormat(String sendDateFormat) {
		this.sendDateFormat = sendDateFormat;
	}
	
	public LocalDateTime getSchedule() {
		return schedule;
	}
	
	public void setSchedule(LocalDateTime schedule) {
		this.schedule = schedule;
	}
	
	public String getScheduleFormat() {
		return scheduleFormat;
	}
	
	public void setScheduleFormat(String scheduleFormat) {
		this.scheduleFormat = scheduleFormat;
	}
	
	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
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
		Message other = (Message) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
