package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Cart;

public class CartCreateRequest {

	private Long idUser;

	public CartCreateRequest() {
	}
	
	public CartCreateRequest(Long idUser) {
		this.idUser = idUser;
	}

	public Cart transformToNewCart() {
		return new Cart(
			null,
			idUser
		);
	}
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
}
