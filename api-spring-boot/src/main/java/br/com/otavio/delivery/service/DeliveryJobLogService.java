package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.DeliveryJobLog;
import br.com.otavio.delivery.repository.DeliveryJobLogRepository;
import br.com.otavio.delivery.repository.DeliveryJobRepository;

@Service
public class DeliveryJobLogService {

	@Autowired
	private DeliveryJobLogRepository deliveryJobLogRepository;

	@Autowired
	private DeliveryJobRepository deliveryJobRepository;

	public DeliveryJobLog save(DeliveryJobLog deliveryJobLog, Long deliveryJobId) {
		return deliveryJobLogRepository.save(deliveryJobLog, deliveryJobId);
	}

	public List<DeliveryJobLog> findAll() {
		return deliveryJobLogRepository.findAll();
	}

	public DeliveryJobLog findById(Long id) {
		Optional<DeliveryJobLog> deliveryJobLog = deliveryJobLogRepository.findById(id);
		return deliveryJobLog.get();
	}
}
