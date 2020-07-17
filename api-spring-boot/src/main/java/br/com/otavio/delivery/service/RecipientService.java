package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.repository.RecipientRepository;

@Service
public class RecipientService {

	@Autowired
	private RecipientRepository recipientRepository;
	
	public Recipient save(Recipient recipient, Long messageId) {
		return recipientRepository.save(recipient, messageId);

	}

	public List<Recipient> findAll() {
		return recipientRepository.findAll();
	}

	public Recipient findById(Long id) {
		Optional<Recipient> recipient = recipientRepository.findById(id);
		return recipient.get();
	}
	
	public List<Recipient> findByDeliveryJob(Long idDeliveryJob) {
		return recipientRepository.findByDeliveryJob(idDeliveryJob);
	}
	
	public Recipient update(Long recipientId, Recipient recipient) {
		Optional<Recipient> recipientUpdated = recipientRepository.update(recipientId, recipient);
		return recipientUpdated.get();
	}
}
