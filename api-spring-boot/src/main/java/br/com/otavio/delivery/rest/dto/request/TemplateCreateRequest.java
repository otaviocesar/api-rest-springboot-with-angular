package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.model.TemplateStatus;
import br.com.otavio.delivery.model.TemplateType;

public class TemplateCreateRequest {
	
	private String content;
	
	private TemplateType type;
	
	private String name;
	
	private String description;

	private TemplateStatus status;
	
	public TemplateCreateRequest(String content, TemplateType type, String name, String description,
		TemplateStatus status) {
		this.content = content;
		this.type = type;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public Template transformToNewTemplate() {
		return new Template(
			null,
			content,
			type,
			name,
			description,
			status
		);
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
