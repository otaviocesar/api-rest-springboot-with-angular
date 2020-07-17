package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Product;
import br.com.otavio.delivery.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	private static Logger productLLogger = LogManager.getLogger(Product.class);
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product.get();
	}

	public Product findByName(Product product) {
		Optional<Product> productFound = productRepository.findByName(product);
		return productFound.get();
	}

	private void validateRegisterProduct(Product product) {
		Optional<Product> productFound = productRepository.findByName(product);
		if (productFound.isPresent()) {
			productLLogger.info("Já cadastrado");
		}
	}
	
	public Product save(Product product) {
		validateRegisterProduct(product);
		return productRepository.save(product);
	}
}
