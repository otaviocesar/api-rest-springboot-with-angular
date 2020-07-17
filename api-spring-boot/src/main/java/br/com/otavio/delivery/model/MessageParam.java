package br.com.otavio.delivery.model;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageParam {
	
	private Long id;
	
	private Long idMessage;
	
	private String name;
	
	private String value;
	
	private String author;
	
	private Map<String, Object> props;
	
	public MessageParam() {
	}
	
	public MessageParam(
		Long id,
		Long idMessage,
		String name,
		String value,
		String selector,
		String author,
		String local
	) {
		this.id = id;
		this.idMessage = idMessage;
		this.name = name;
		this.value = value;
		this.author = author;
	}
	
	public MessageParam(
		Long idMessage,
		String name,
		String value,
		String author
	) {
		this.idMessage = idMessage;
		this.name = name;
		this.value = value;
		this.author = author;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdMessage() {
		return idMessage;
	}
	
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
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
		MessageParam other = (MessageParam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Map<String, Object> getProps() {
		return props;
	}
	
	public void setProps(Map<String, Object> props) {
		this.props = props;
	}
}
