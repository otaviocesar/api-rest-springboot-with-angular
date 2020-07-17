package br.com.otavio.delivery.rest.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.otavio.delivery.model.Order;
import br.com.otavio.delivery.rest.dto.request.OrderCreateRequest;
import br.com.otavio.delivery.rest.dto.response.OrderResponse;
import br.com.otavio.delivery.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order", description = "Grupo de endpoints para criar, listar, atualizar e deletar orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Operation(
		summary = "Listar todos as orders",
		description = "Retorna uma lista com todos as orders cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<OrderResponse>> listOrders() {
		
		return ResponseEntity.ok(
			orderService.findAll().stream().map(OrderResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}

	@Operation(
		summary = "Adicionar uma order",
		description = "Essa operacao salva um novo registro com as informacoes do order"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<OrderResponse> save(@RequestBody OrderCreateRequest orderDTO) {
		Order order = orderService.save(orderDTO.transformToNewOrder());
		
		return new ResponseEntity<>(OrderResponse.transformToResponseDTO(order), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar order", description = "Buscar um order")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
		Order order = orderService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(OrderResponse.transformToResponseDTO(order));
	}
}
