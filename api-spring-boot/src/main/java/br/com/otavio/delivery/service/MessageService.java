package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.MessageStatus;
import br.com.otavio.delivery.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;

	public Message save(Message message) {
		return messageRepository.save(message);
	}
	
	public List<Message> findAll() {
		return messageRepository.findAll();
	}
	
	public Message findById(Long id) {
		Optional<Message> message = messageRepository.findById(id);
		return message.get();
	}
	
	public void update(Long messageId, MessageStatus status) {
		messageRepository.updateStatus(messageId, status);
	}
}
