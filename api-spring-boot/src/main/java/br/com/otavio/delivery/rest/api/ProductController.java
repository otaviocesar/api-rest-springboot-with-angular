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

import br.com.otavio.delivery.model.Product;
import br.com.otavio.delivery.rest.dto.request.ProductCreateRequest;
import br.com.otavio.delivery.rest.dto.response.ProductResponse;
import br.com.otavio.delivery.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Grupo de endpoints para criar, listar, atualizar e deletar products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(
		summary = "Listar todos as products",
		description = "Retorna uma lista com todos as products cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ProductResponse>> listProducts() {

		return ResponseEntity.ok(
			productService.findAll().stream().map(ProductResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}
	
	@Operation(
		summary = "Adicionar uma product",
		description = "Essa operacao salva um novo registro com as informacoes do product"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<ProductResponse> save(@RequestBody ProductCreateRequest productDTO) {
		Product product = productService.save(productDTO.transformToNewProduct());

		return new ResponseEntity<>(ProductResponse.transformToResponseDTO(product), HttpStatus.CREATED);
	}

	@Operation(summary = "Buscar product", description = "Buscar um product")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ProductResponse.transformToResponseDTO(product));
	}
}
