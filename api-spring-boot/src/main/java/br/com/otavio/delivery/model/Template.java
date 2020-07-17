package br.com.otavio.delivery.model;

import org.springframework.stereotype.Component;

@Component
public class Template {
	
	private Long id;
	
	private String content;
	
	private TemplateType type;
	
	private String name;
	
	private String description;
	
	private TemplateStatus status;
	
	public Template() {
	}
	
	public Template(
		Long id,
		String content,
		TemplateType type,
		String name,
		String description,
		TemplateStatus status
	) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public Template(
		String content,
		TemplateType type,
		String name,
		String description,
		TemplateStatus status
	) {
		this.content = content;
		this.type = type;
		this.name = name;
		this.description = description;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public TemplateType getType() {
		return type;
	}
	
	public void setType(TemplateType type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public TemplateStatus getStatus() {
		return status;
	}
	
	public void setStatus(TemplateStatus status) {
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
		Template other = (Template) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
