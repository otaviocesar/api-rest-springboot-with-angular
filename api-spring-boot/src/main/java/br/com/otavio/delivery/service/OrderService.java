package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Order;
import br.com.otavio.delivery.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	private static Logger orderLLogger = LogManager.getLogger(Order.class);
	
	public List<Order> findAll() {
		return orderRepository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.get();
	}

	public Order findByidCart(Order order) {
		Optional<Order> orderFound = orderRepository.findByIdCart(order);
		return orderFound.get();
	}

	private void validateRegisterOrder(Order order) {
		Optional<Order> orderFound = orderRepository.findByIdCart(order);
		if (orderFound.isPresent()) {
			orderLLogger.info("Já cadastrado");
		}
	}
	
	public Order save(Order order) {
		validateRegisterOrder(order);
		return orderRepository.save(order);
	}
}
