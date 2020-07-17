package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Attachment;
import br.com.otavio.delivery.repository.AttachmentRepository;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;
	
	public void save(Attachment attachment, Long messageId) {
		attachmentRepository.save(attachment, messageId);
	}

	public Attachment findById(Long messageId, Long attachmentId) {
		Optional<Attachment> attachmentOptional = attachmentRepository.findById(messageId, attachmentId);
		return attachmentOptional.get();
	}

	public List<Attachment> findAll() {
		return attachmentRepository.findAll();
	}
}
