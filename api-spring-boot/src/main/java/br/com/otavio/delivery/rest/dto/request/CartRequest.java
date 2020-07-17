package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Cart;

public class CartRequest {
	
	private Long idUser;
	
	public CartRequest(Long idUser) {
		this.idUser = idUser;
	}
	
	public Cart transformToCart() {
		return new Cart(idUser);
	}
	
	public static Cart transformToCart(Long idUser) {
		return new Cart(
			idUser
		);
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
}
