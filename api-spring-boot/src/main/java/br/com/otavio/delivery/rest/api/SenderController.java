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

import br.com.otavio.delivery.model.Sender;
import br.com.otavio.delivery.rest.dto.request.SenderCreateRequest;
import br.com.otavio.delivery.rest.dto.response.SenderResponse;
import br.com.otavio.delivery.service.SenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/senders")
@Tag(name = "Sender", description = "Grupo de endpoints para criar, listar, atualizar e deletar senders")
public class SenderController {
	
	@Autowired
	private SenderService senderService;
	
	@Operation(
		summary = "Listar todos as senders",
		description = "Retorna uma lista com todos as senders cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<SenderResponse>> listSenders() {
		
		return ResponseEntity.ok(
			senderService.findAll().stream().map(SenderResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}

	@Operation(
		summary = "Adicionar uma sender",
		description = "Essa operacao salva um novo registro com as informacoes do sender"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<SenderResponse> save(@RequestBody SenderCreateRequest senderDTO) {
		Sender sender = senderService.save(senderDTO.transformToNewSender());
		
		return new ResponseEntity<>(SenderResponse.transformToResponseDTO(sender), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar sender", description = "Buscar um sender")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<SenderResponse> findById(@PathVariable Long id) {
		Sender sender = senderService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(SenderResponse.transformToResponseDTO(sender));
	}
}
