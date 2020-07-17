package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.Sender;
import br.com.otavio.delivery.repository.SenderRepository;

@Service
public class SenderService {

	@Autowired
	private SenderRepository senderRepository;

	private static Logger senderLLogger = LogManager.getLogger(Sender.class);

	public List<Sender> findAll() {
		return senderRepository.findAll();
	}

	public Sender findById(Long id) {
		Optional<Sender> sender = senderRepository.findById(id);
		return sender.get();
	}
	
	public Sender findByName(Sender sender) {
		Optional<Sender> senderFound = senderRepository.findByName(sender);
		return senderFound.get();
	}
	
	private void validateRegisterSender(Sender sender) {
		Optional<Sender> senderFound = senderRepository.findByName(sender);
		if (senderFound.isPresent()) {
			senderLLogger.info("Já cadastrado");
		}
	}

	public Sender save(Sender sender) {
		validateRegisterSender(sender);
		return senderRepository.save(sender);
	}
}
