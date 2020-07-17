package br.com.otavio.delivery.model;

public enum TemplateStatus {

	INACTIVE(0L, "Inativo"),
	ACTIVE(1L, "Ativo"),
	DELETED(2L, "Removido");

	private Long code;

	private String description;

	private TemplateStatus(Long code, String description) {
		this.code = code;
		this.description = description;
	}

	public Long getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static TemplateStatus valueOf(Long code) {
		for (TemplateStatus us : TemplateStatus.values()) {
			if (us.code.equals(code)) {
				return us;
			}
		}
		throw new IllegalArgumentException(String.format("Invalid code: %d", code));
	}
}
