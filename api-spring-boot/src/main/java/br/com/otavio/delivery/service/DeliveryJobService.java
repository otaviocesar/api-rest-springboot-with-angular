package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.RabbitSender;
import br.com.otavio.delivery.model.DeliveryJob;
import br.com.otavio.delivery.model.DeliveryJobStatus;
import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.model.Sender;
import br.com.otavio.delivery.repository.DeliveryJobRepository;

@Service
public class DeliveryJobService {

	@Autowired
	private DeliveryJobRepository deliveryJobRepository;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private RecipientService recipientService;
	
	@Autowired
	private SenderService senderService;

	@Autowired
	private RabbitSender rabbitSender;

	private static final String EXCEPTION = "Delivery Job não encontrado";

	public DeliveryJob save(DeliveryJob deliveryJob) {
		return deliveryJobRepository.save(deliveryJob);
	}

	public List<DeliveryJob> findAll() {
		return deliveryJobRepository.findAll();
	}

	public DeliveryJob findById(Long id) {
		Optional<DeliveryJob> deliveryJob = deliveryJobRepository.findById(id);
		if (!deliveryJob.isPresent()) {
			
		}
		return deliveryJob.get();
	}

	public void update(Long deliveryJobId, DeliveryJobStatus status) {
		Optional<DeliveryJob> optionalDeliveryJob = deliveryJobRepository.findById(deliveryJobId);
		if (!optionalDeliveryJob.get().getStatus().equals(DeliveryJobStatus.FINISHED)
				&& status.equals(DeliveryJobStatus.RUNNING)) {
			
			List<Recipient> recipientList = recipientService.findByDeliveryJob(deliveryJobId);
			Recipient recipientSend = recipientList.get(0);
			Message messageSend = messageService.findById(recipientSend.getIdMessage());
			Sender sender = senderService.findById(messageSend.getIdSender());
			
			rabbitSender.send(deliveryJobId, sender.getName());
			deliveryJobRepository.updateStatus(deliveryJobId, status);
		}
		if (optionalDeliveryJob.get().getStatus().equals(DeliveryJobStatus.RUNNING)
				&& status.equals(DeliveryJobStatus.FINISHED)) {
			deliveryJobRepository.updateStatus(deliveryJobId, status);
		}
	}
}
