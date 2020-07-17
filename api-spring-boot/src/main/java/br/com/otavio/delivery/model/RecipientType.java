package br.com.otavio.delivery.model;

public enum RecipientType {

	TO(0L, "To"),
	CC(1L, "Cc"),
	BCC(2L, "Bcc");
	
	private Long code;

	private String description;

	private RecipientType(Long code, String description) {
		this.code = code;
		this.description = description;
	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static RecipientType valueOf(Long code) {
		for (RecipientType us : RecipientType.values()) {
			if (us.code.equals(code)) {
				return us;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid code: %d", code));
	}
}
