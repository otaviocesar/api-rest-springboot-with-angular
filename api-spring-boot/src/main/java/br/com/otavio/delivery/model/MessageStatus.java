package br.com.otavio.delivery.model;

public enum MessageStatus {
	
	BUILDING(0L, "Building"),
	READY(1L, "Ready");
	
	private Long code;
	
	private String description;
	
	private MessageStatus(Long code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Long getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static MessageStatus valueOf(Long code) {
		for (MessageStatus us : MessageStatus.values()) {
			if (us.code.equals(code)) {
				return us;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid code: %d", code));
	}
}
