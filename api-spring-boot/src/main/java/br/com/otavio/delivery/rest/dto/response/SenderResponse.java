package br.com.otavio.delivery.rest.dto.response;

import br.com.otavio.delivery.model.Sender;

public class SenderResponse {

	private Long id;
	
	private String name;

	private SenderResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static SenderResponse transformToResponseDTO(Sender sender) {
		return new SenderResponse(
			sender.getId(), sender.getName()
		);
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
