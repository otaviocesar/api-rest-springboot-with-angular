package br.com.otavio.delivery;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.otavio.delivery.model.DeliveryJobLog;
import br.com.otavio.delivery.model.DeliveryJobStatus;
import br.com.otavio.delivery.service.DeliveryJobLogService;
import br.com.otavio.delivery.service.DeliveryJobService;
import br.com.otavio.delivery.service.MailService;

@Component
@Lazy
public class RabbitReceiver {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private DeliveryJobService deliveryJobService;
	
	@Autowired
	private DeliveryJobLogService deliveryJobLogService;

	private static Logger receiverLLogger = LogManager.getLogger(RabbitReceiver.class);
	
	@RabbitListener(queues = "DeliveryQueue")
	public void receiveMessage(Long deliveryJobId)
		throws MessagingException {
		try {
			mailService.sendEmailWithAttachment(deliveryJobId);
			deliveryJobService.update(deliveryJobId, DeliveryJobStatus.FINISHED);
			receiverLLogger.info("Delivery Job Finished");
		} catch (MessagingException e) {
			DeliveryJobLog deliveryJobLog = new DeliveryJobLog();
			deliveryJobLog.setSendDate(LocalDateTime.now());
			deliveryJobLog.setError(e.toString());
			deliveryJobLogService.save(deliveryJobLog, deliveryJobId);
		}
	}
}
