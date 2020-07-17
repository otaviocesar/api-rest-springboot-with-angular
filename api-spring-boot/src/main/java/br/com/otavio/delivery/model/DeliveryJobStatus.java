package br.com.otavio.delivery.model;

public enum DeliveryJobStatus {

	NEW(0L, "New"),
	RUNNING(1L, "Running"),
	ERROR(2L, "Error"),
	FINISHED(3L, "Finished");

	private Long code;

	private String description;

	private DeliveryJobStatus(Long code, String description) {
		this.code = code;
		this.description = description;
	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static DeliveryJobStatus valueOf(Long code) {
		for (DeliveryJobStatus us : DeliveryJobStatus.values()) {
			if (us.code.equals(code)) {
				return us;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid code: %d", code));
	}
}
