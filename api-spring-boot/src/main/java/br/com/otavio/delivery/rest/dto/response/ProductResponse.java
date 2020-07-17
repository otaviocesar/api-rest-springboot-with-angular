package br.com.otavio.delivery.rest.dto.response;

import br.com.otavio.delivery.model.Product;

public class ProductResponse {

	private Long id;
	
	private String name;
	
	private String details;
	
	private Long price;

	private ProductResponse(Long id, String name, String details, Long price) {
		this.id = id;
		this.name = name;
		this.details = details;
		this.price = price;
	}

	public static ProductResponse transformToResponseDTO(Product product) {
		return new ProductResponse(
			product.getId(), product.getName(), product.getDetails(), product.getPrice()
		);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
