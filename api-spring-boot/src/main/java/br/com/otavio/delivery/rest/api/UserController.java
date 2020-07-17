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

import br.com.otavio.delivery.model.User;
import br.com.otavio.delivery.rest.dto.request.UserCreateRequest;
import br.com.otavio.delivery.rest.dto.response.UserResponse;
import br.com.otavio.delivery.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Grupo de endpoints para criar, listar, atualizar e deletar users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Operation(
		summary = "Listar todos as users",
		description = "Retorna uma lista com todos as users cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<UserResponse>> listUsers() {
		
		return ResponseEntity.ok(
			userService.findAll().stream().map(UserResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}

	@Operation(
		summary = "Adicionar uma user",
		description = "Essa operacao salva um novo registro com as informacoes do user"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<UserResponse> save(@RequestBody UserCreateRequest userDTO) {
		User user = userService.save(userDTO.transformToNewUser());
		
		return new ResponseEntity<>(UserResponse.transformToResponseDTO(user), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar user", description = "Buscar um user")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<UserResponse> findById(@PathVariable Long id) {
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(UserResponse.transformToResponseDTO(user));
	}
}
