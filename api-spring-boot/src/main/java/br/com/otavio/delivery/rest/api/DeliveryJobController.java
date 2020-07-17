package br.com.otavio.delivery.rest.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.otavio.delivery.model.DeliveryJob;
import br.com.otavio.delivery.model.DeliveryJobLog;
import br.com.otavio.delivery.model.DeliveryJobStatus;
import br.com.otavio.delivery.rest.dto.request.DeliveryJobCreateRequest;
import br.com.otavio.delivery.rest.dto.request.DeliveryJobLogCreateRequest;
import br.com.otavio.delivery.rest.dto.response.DeliveryJobLogResponse;
import br.com.otavio.delivery.rest.dto.response.DeliveryJobResponse;
import br.com.otavio.delivery.service.DeliveryJobLogService;
import br.com.otavio.delivery.service.DeliveryJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/deliveryjobs")
@Tag(name = "DeliveryJob", description = "Grupo de endpoints para criar, listar, atualizar e deletar Delivery Jobs")
public class DeliveryJobController {

	@Autowired
	private DeliveryJobService deliveryJobService;

	@Autowired
	private DeliveryJobLogService deliveryJobLogService;

	@Operation(
		summary = "Listar todos os Delivery Jobs",
		description = "Retorna uma lista com todos os Delivery Jobs cadastrados"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<DeliveryJobResponse>> listDeliveryJobs() {

		return ResponseEntity.ok(
			deliveryJobService.findAll().stream().map(DeliveryJobResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}

	@Operation(
		summary = "Adicionar um Delivery Job",
		description = "Essa operacao salva um novo registro com as informacoes do Delivery Job"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<DeliveryJobResponse> save(@RequestBody DeliveryJobCreateRequest deliveryJobDTO) {
		DeliveryJob deliveryJob = deliveryJobService.save(deliveryJobDTO.transformToNewDeliveryJob());
		return new ResponseEntity<>(DeliveryJobResponse.transformToResponseDTO(deliveryJob), HttpStatus.CREATED);
	}

	@Operation(summary = "Buscar Delivery Job", description = "Buscar um Delivery Job")
	@GetMapping(value = "/{id}")
	public @ResponseBody ResponseEntity<DeliveryJobResponse> findById(@PathVariable Long id) {
		DeliveryJob deliveryJob = deliveryJobService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(DeliveryJobResponse.transformToResponseDTO(deliveryJob));
	}

	@Operation(
		summary = "Atualiza status do DeliveryJob, envia para fila do RabbitMQ, recebe item da fila e Envia e-mails",
		description = "Essa operacao envia para fila do Rabbit ou envia e-mails de acordo com o status do Delivery Job"
	)
	@PatchMapping(value = "/{id}")
	public ResponseEntity<HttpStatus> updateDeliveryJobStatus(@PathVariable Long id,
		@RequestParam DeliveryJobStatus status) {
		deliveryJobService.update(id, status);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Operation(
		summary = "Adicionar um Delivery Job Log",
		description = "Essa operacao salva um novo registro com as informacoes do Delivery Job Log"
	)
	@PostMapping(
		value = "/{deliveryJobId}/logs",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<DeliveryJobLogResponse> addDeliveryJobLog(@PathVariable Long deliveryJobId,
		@RequestBody DeliveryJobLogCreateRequest deliveryJobLogDTO) {
		DeliveryJobLog deliveryJobLog = deliveryJobLogService
			.save(deliveryJobLogDTO.transformToNewDeliveryJobLog(), deliveryJobId);
		
		DeliveryJobLogResponse deliveryJobLogResponse = DeliveryJobLogResponse
			.transformToResponseDTO(deliveryJobLog, deliveryJobId);
		return new ResponseEntity<>(deliveryJobLogResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "Buscar Delivery Job Log", description = "Buscar um Delivery Job Log")
	@GetMapping(value = "/{deliveryJobId}/logs/{deliveryJobLogId}")
	public @ResponseBody ResponseEntity<DeliveryJobLogResponse> findByLogId(@PathVariable Long deliveryJobId,
		@PathVariable Long deliveryJobLogId) {
		DeliveryJobLog deliveryJobLog = deliveryJobLogService.findById(deliveryJobLogId);
		return new ResponseEntity<>(
			DeliveryJobLogResponse.transformToResponseDTO(deliveryJobLog, deliveryJobId),
			HttpStatus.OK
		);
	}
}
