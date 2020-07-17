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

import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.rest.dto.request.TemplateCreateRequest;
import br.com.otavio.delivery.rest.dto.response.TemplateResponse;
import br.com.otavio.delivery.service.TemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/templates")
@Tag(name = "Template", description = "Grupo de endpoints para criar, listar, atualizar e deletar templates")
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	@Operation(
		summary = "Listar todos as templates",
		description = "Retorna uma lista com todos as templates cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TemplateResponse>> listTemplates() {
		
		return ResponseEntity.ok(
			templateService.findAll().stream().map(TemplateResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}
	
	@Operation(
		summary = "Adicionar uma template",
		description = "Essa operacao salva um novo registro com as informacoes do template"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<TemplateResponse> save(@RequestBody TemplateCreateRequest templateDTO) {
		Template template = templateService.save(templateDTO.transformToNewTemplate());
		return new ResponseEntity<>(TemplateResponse.transformToResponseDTO(template), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar template", description = "Buscar um template")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<TemplateResponse> findById(@PathVariable Long id) {
		Template template = templateService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(TemplateResponse.transformToResponseDTO(template));
	}
}
