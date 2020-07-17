package br.com.otavio.delivery.rest.api;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import br.com.otavio.delivery.model.Attachment;
import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.MessageParam;
import br.com.otavio.delivery.model.MessageStatus;
import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.rest.dto.request.MessageCreateRequest;
import br.com.otavio.delivery.rest.dto.request.MessageParamCreateRequest;
import br.com.otavio.delivery.rest.dto.request.RecipientCreateRequest;
import br.com.otavio.delivery.rest.dto.response.MessageParamResponse;
import br.com.otavio.delivery.rest.dto.response.MessageResponse;
import br.com.otavio.delivery.rest.dto.response.RecipientResponse;
import br.com.otavio.delivery.service.AttachmentService;
import br.com.otavio.delivery.service.MessageParamService;
import br.com.otavio.delivery.service.MessageService;
import br.com.otavio.delivery.service.RecipientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/messages")
@Tag(name = "Message", description = "Grupo de endpoints para criar, listar, atualizar e deletar mensagens")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageParamService messageParamService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private RecipientService recipientService;
	
	@Operation(
		summary = "Adicionar uma mensagem",
		description = "Essa operacao salva um novo registro com as informacoes da mensagem"
	)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<MessageResponse> save(@RequestBody MessageCreateRequest messageDTO) {
		Message message = messageService.save(messageDTO.transformToNewMessage());

		return new ResponseEntity<>(MessageResponse.responseDTO(message), HttpStatus.CREATED);
	}
	
	@Operation(summary = "Buscar mensagem", description = "Buscar uma mensagem")
	@GetMapping(value = "/{id}")
	public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
		Message message = messageService.findById(id);
		MessageResponse messageResponse = MessageResponse.transformToResponseDTO(message);
		return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
	}
	
	@Operation(
		summary = "Listar todos as mensagens",
		description = "Retorna uma lista com todas as mensagens cadastradas"
	)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<MessageResponse>> listMessages() {
		
		return ResponseEntity.ok(
			messageService.findAll().stream().map(MessageResponse::transformToResponseDTO).collect(Collectors.toList())
		);
	}
	
	@Operation(
		summary = "Adicionar uma parâmetro de mensagem",
		description = "Essa operacao salva um novo registro com as informacoes do parâmetro de mensagem"
	)
	@PostMapping(
		value = "/{messageId}/params",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public @ResponseBody ResponseEntity<MessageParamResponse> addMessageParam(@PathVariable Long messageId,
		@RequestBody MessageParamCreateRequest messageParamDTO) {
		MessageParam messageParam = messageParamService
			.save(messageParamDTO.transformToNewMessageParam(), messageId);
		MessageParamResponse messageParamResponse = MessageParamResponse
			.transformToResponseDTO(messageParam, messageId);
		return new ResponseEntity<>(messageParamResponse, HttpStatus.CREATED);
	}
	
	@Operation(
		summary = "Buscar MessageParam",
		description = "Essa operação recupera os parâmetros de uma mensagem por id"
	)
	@GetMapping(value = "/{messageId}/params/{messageParamId}")
	public @ResponseBody ResponseEntity<MessageParamResponse> findMessageParamById(@PathVariable Long messageId,
		@PathVariable Long messageParamId) {
		MessageParam optionalMessageParam = messageParamService.findById(messageParamId);
		return new ResponseEntity<>(
			MessageParamResponse.transformToResponseDTO(optionalMessageParam, messageId),
			HttpStatus.OK
		);
	}
	
	@Operation(
		summary = "Adicionar um anexo",
		description = "Essa operacao salva um novo anexo"
	)
	@PostMapping(value = "/{messageId}/attachments")
	public @ResponseBody ResponseEntity<Attachment> saveAttachment(@RequestParam MultipartFile file,
		@PathVariable Long messageId) throws IOException {

		messageService.findById(messageId);
		Attachment attachment = new Attachment(file.getBytes(), file.getOriginalFilename(), file.getContentType());
		attachmentService.save(attachment, messageId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "Buscar anexo", description = "Busca um anexo")
	@GetMapping("/{messageId}/attachments/{attachmentId}")
	public @ResponseBody ResponseEntity<Attachment> findById(@PathVariable Long messageId,
		@PathVariable Long attachmentId) {
		Attachment attachment = attachmentService.findById(messageId, attachmentId);
		return new ResponseEntity<>(attachment, HttpStatus.OK);
	}
	
	@Operation(
		summary = "Adicionar uma destinatário",
		description = "Essa operacao salva um novo registro com as informacoes da destinatário"
	)
	@PostMapping(
		value = "/{messageId}/recipients",
		produces = MediaType.APPLICATION_JSON_VALUE,
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public @ResponseBody ResponseEntity<RecipientResponse> addRecipient(@PathVariable Long messageId,
		@RequestBody RecipientCreateRequest recipientDTO) {
		Recipient recipient = recipientService
			.save(recipientDTO.transformToNewRecipient(), messageId);
		RecipientResponse recipientResponse = RecipientResponse
			.transformToResponseDTO(recipient, messageId);
		return new ResponseEntity<>(recipientResponse, HttpStatus.CREATED);
	}

	@Operation(summary = "Buscar destinatário", description = "Buscar uma destinatário")
	@GetMapping(value = "/{messageId}/recipients/{recipientId}")
	public @ResponseBody ResponseEntity<RecipientResponse> findRecipientById(@PathVariable Long messageId,
		@PathVariable Long recipientId) {
		Recipient optionalRecipient = recipientService.findById(recipientId);
		return new ResponseEntity<>(
			RecipientResponse.transformToResponseDTO(optionalRecipient, messageId),
			HttpStatus.OK
		);
	}

	@Operation(
		summary = "Atualizar status da Mensagem para Pronta",
		description = "Essa operacao atualiza status da Mensagem para Pronta"
	)
	@PatchMapping(value = "/{messageId}")
	public ResponseEntity<HttpStatus> updateMessageStatus(@PathVariable Long messageId) {
		messageService.update(messageId, MessageStatus.READY);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
