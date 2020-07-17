package br.com.otavio.delivery.model;

public class Attachment {
	
	private Long id;
	
	private byte[] content;
	
	private String fileName;
	
	private String typeFile;
	
	private Long messageId;
	
	public Attachment() {
	}
	
	public Attachment(Long id,
		byte[] content,
		String fileName,
		String typeFile) {
		this.id = id;
		this.content = content;
		this.fileName = fileName;
		this.typeFile = typeFile;
	}
	
	public Attachment(byte[] content,
		String fileName,
		String typeFile) {
		this.content = content;
		this.fileName = fileName;
		this.typeFile = typeFile;
	}
	
	public Attachment(byte[] content,
		String fileName,
		String typeFile,
		Long messageId) {
		this.content = content;
		this.fileName = fileName;
		this.typeFile = typeFile;
		this.messageId = messageId;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public byte[] getContent() {
		return content;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}
	
	public String getTypeFile() {
		return typeFile;
	}
	
	public Long getMessageId() {
		return messageId;
	}
	
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
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
		Attachment other = (Attachment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}
