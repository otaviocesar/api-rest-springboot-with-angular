package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.MessageParam;
import br.com.otavio.delivery.repository.MessageParamRepository;

@Service
public class MessageParamService {

	@Autowired
	private MessageParamRepository messageParamRepository;

	public MessageParam save(MessageParam messageParam, Long messageId) {
		return messageParamRepository.save(messageParam, messageId);
	}

	public List<MessageParam> findAll() {
		return messageParamRepository.findAll();
	}

	public MessageParam findById(Long id) {
		Optional<MessageParam> messageParam = messageParamRepository.findById(id);
		return messageParam.get();
	}
	
	public MessageParam findByMessageId(Long id) {
		Optional<MessageParam> messageParam = messageParamRepository.findByMessageId(id);
		return messageParam.get();
	}
}
