package br.com.otavio.delivery.model;

public class User {
	
	private Long id;
	
	private String name;
	
	private String mail;
	
	private String address;
	
	public User() {
	}
	
	public User(
		Long id,
		String name,
		String mail,
		String address
	) {
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.address = address;
	}
	
	public User(
		String name,
		String mail,
		String address
	) {
		this.name = name;
		this.mail = mail;
		this.address = address;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
