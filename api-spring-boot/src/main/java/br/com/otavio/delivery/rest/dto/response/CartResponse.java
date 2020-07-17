package br.com.otavio.delivery.rest.dto.response;

import br.com.otavio.delivery.model.Cart;

public class CartResponse {

	private Long id;
	
	private Long idUser;

	private CartResponse(Long id, Long idUser) {
		this.id = id;
		this.idUser = idUser;
	}

	public static CartResponse transformToResponseDTO(Cart cart) {
		return new CartResponse(
			cart.getId(), cart.getIdUser()
		);
	}

	public Long getId() {
		return id;
	}
	
	public Long getIdUser() {
		return idUser;
	}
}
