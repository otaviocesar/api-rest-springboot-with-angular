package br.com.otavio.delivery.rest.dto.response;

import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.model.TemplateStatus;
import br.com.otavio.delivery.model.TemplateType;

public class TemplateResponse {

	private Long id;
	
	private String content;

	private TemplateType type;

	private String name;

	private String description;
	
	private TemplateStatus status;

	private TemplateResponse(Long id, String content, TemplateType type, String name, String description,
		TemplateStatus status) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.name = name;
		this.description = description;
		this.status = status;
	}

	public static TemplateResponse transformToResponseDTO(Template template) {
		return new TemplateResponse(
			template.getId(), template.getContent(), template.getType(), template.getName(), template.getDescription(),
			template.getStatus()
		);
	}

	public Long getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}

	public TemplateType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public TemplateStatus getStatus() {
		return status;
	}
}
