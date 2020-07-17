package br.com.otavio.delivery.model;

public enum TemplateType {

	TEXT(0L, "Text"),
	HTML(1L, "Html");
	
	private Long code;

	private String description;

	private TemplateType(Long code, String description) {
		this.code = code;
		this.description = description;
	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static TemplateType valueOf(Long code) {
		for (TemplateType us : TemplateType.values()) {
			if (us.code.equals(code)) {
				return us;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid code: %d", code));
	}
}
