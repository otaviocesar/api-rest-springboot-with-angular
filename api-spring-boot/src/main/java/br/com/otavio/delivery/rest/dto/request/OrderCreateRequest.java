package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Order;

public class OrderCreateRequest {
	
	private Long idCart;

	private Long idProduct;
	
	public OrderCreateRequest() {
	}

	public OrderCreateRequest(Long idCart, Long idProduct) {
		this.idCart = idCart;
		this.idProduct = idProduct;
	}
	
	public Order transformToNewOrder() {
		return new Order(
			null,
			idCart,
			idProduct
		);
	}

	public Long getCart() {
		return idCart;
	}
	
	public void setCart(Long idCart) {
		this.idCart = idCart;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
}
