package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.model.TemplateStatus;
import br.com.otavio.delivery.model.TemplateType;

public class TemplateRequest {

	private String content;

	private TemplateType type;

	private String name;

	private String description;
	
	private TemplateStatus status;
	
	public TemplateRequest(String content, TemplateType type, String name, String description, TemplateStatus status) {
		this.content = content;
		this.type = type;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public Template transformToTemplate() {
		return new Template(content, type, name, description, status);
	}
	
	public static Template transformToTemplate(String content, TemplateType type, String name,
		String description, TemplateStatus status) {
		return new Template(
			content, type, name, description, status
		);
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(TemplateStatus status) {
		this.status = status;
	}
}
