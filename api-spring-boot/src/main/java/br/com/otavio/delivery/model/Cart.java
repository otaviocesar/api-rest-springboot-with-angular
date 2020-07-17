package br.com.otavio.delivery.model;

public class Cart {
	
	private Long id;
	
	private Long idUser;
	
	public Cart() {
	}
	
	public Cart(
		Long id,
		Long idUser
	) {
		this.id = id;
		this.idUser = idUser;
	}
	
	public Cart(
		Long idUser
	) {
		this.idUser = idUser;
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
		Cart other = (Cart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
}
