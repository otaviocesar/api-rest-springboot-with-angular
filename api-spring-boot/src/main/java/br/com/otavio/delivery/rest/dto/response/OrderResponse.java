package br.com.otavio.delivery.rest.dto.response;

import br.com.otavio.delivery.model.Order;

public class OrderResponse {

	private Long id;
	
	private Long idCart;

	private Long idProduct;

	private OrderResponse(Long id, Long idCart, Long idProduct) {
		this.id = id;
		this.idCart = idCart;
		this.idProduct = idProduct;
	}

	public static OrderResponse transformToResponseDTO(Order order) {
		return new OrderResponse(
			order.getId(), order.getIdCart(), order.getIdProduct()
		);
	}

	public Long getId() {
		return id;
	}
	
	public Long getIdCart() {
		return idCart;
	}
	
	public Long getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
}
