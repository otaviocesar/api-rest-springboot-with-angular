package br.com.otavio.delivery.model;

public class Order {
	
	private Long id;
	
	private Long idCart;
	
	private Long idProduct;
	
	public Order() {
	}
	
	public Order(
		Long id,
		Long idCart,
		Long idProduct
	) {
		this.id = id;
		this.idCart = idCart;
		this.idProduct = idProduct;
	}
	
	public Order(
		Long idCart,
		Long idProduct
	) {
		this.idCart = idCart;
		this.idProduct = idProduct;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	public Long getIdCart() {
		return idCart;
	}
	
	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
}
