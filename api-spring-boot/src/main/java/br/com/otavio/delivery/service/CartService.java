package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Cart;
import br.com.otavio.delivery.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	private static Logger cartLLogger = LogManager.getLogger(Cart.class);

	public List<Cart> findAll() {
		return cartRepository.findAll();
	}

	public Cart findById(Long id) {
		Optional<Cart> cart = cartRepository.findById(id);
		return cart.get();
	}
	
	public Cart findByidUser(Cart cart) {
		Optional<Cart> cartFound = cartRepository.findByIdUser(cart);
		return cartFound.get();
	}
	
	private void validateRegisterCart(Cart cart) {
		Optional<Cart> cartFound = cartRepository.findByIdUser(cart);
		if (cartFound.isPresent()) {
			cartLLogger.info("Já cadastrado");
		}
	}

	public Cart save(Cart cart) {
		validateRegisterCart(cart);
		return cartRepository.save(cart);
	}
}
