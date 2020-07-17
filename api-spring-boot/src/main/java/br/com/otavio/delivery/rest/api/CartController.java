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

import br.com.otavio.delivery.model.Cart;
import br.com.otavio.delivery.rest.dto.request.CartCreateRequest;
import br.com.otavio.delivery.rest.dto.response.CartResponse;
import br.com.otavio.delivery.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/carts")
@Tag(name = "Cart", description = "Grupo de endpoints para criar, listar, atualizar e deletar carts")
public class CartController {

	@Autowired
	private CartService cartService;

	@Operation(
		summary = "Listar todos as carts",
		description = "Retorna uma lista com todos as carts cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<CartResponse>> listCarts() {

		return ResponseEntity.ok(
			cartService.findAll().stream().map(CartResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}
	
	@Operation(
		summary = "Adicionar uma cart",
		description = "Essa operacao salva um novo registro com as informacoes do cart"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CartResponse> save(@RequestBody CartCreateRequest cartDTO) {
		Cart cart = cartService.save(cartDTO.transformToNewCart());

		return new ResponseEntity<>(CartResponse.transformToResponseDTO(cart), HttpStatus.CREATED);
	}

	@Operation(summary = "Buscar cart", description = "Buscar um cart")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<CartResponse> findById(@PathVariable Long id) {
		Cart cart = cartService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(CartResponse.transformToResponseDTO(cart));
	}
}
