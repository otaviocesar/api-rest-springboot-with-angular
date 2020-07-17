package br.com.otavio.delivery.rest.dto.request;

import br.com.otavio.delivery.model.Product;

public class ProductCreateRequest {
	
	private String name;
	
	private String details;
	
	private Long price;
	
	public ProductCreateRequest() {
	}

	public ProductCreateRequest(String name, String details, Long price) {
		this.name = name;
		this.details = details;
		this.price = price;
	}
	
	public Product transformToNewProduct() {
		return new Product(
			null,
			name,
			details,
			price
		);
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
