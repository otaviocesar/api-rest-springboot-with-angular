package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Order;

public class OrderRequest {

	private Long idCart;

	private Long idProduct;

	public OrderRequest(Long idCart, Long idProduct) {
		this.idCart = idCart;
		this.idProduct = idProduct;
	}

	public Order transformToOrder() {
		return new Order(idCart, idProduct);
	}

	public static Order transformToOrder(Long idCart, Long idProduct) {
		return new Order(
			idCart, idProduct
		);
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
