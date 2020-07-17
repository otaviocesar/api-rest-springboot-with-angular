package br.com.otavio.delivery.service;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import br.com.otavio.delivery.model.Attachment;
import br.com.otavio.delivery.model.DeliveryJob;
import br.com.otavio.delivery.model.Message;
import br.com.otavio.delivery.model.MessageParam;
import br.com.otavio.delivery.model.Recipient;
import br.com.otavio.delivery.model.RecipientType;
import br.com.otavio.delivery.model.Template;
import br.com.otavio.delivery.repository.AttachmentRepository;
import br.com.otavio.delivery.repository.RecipientRepository;

@Service
public class MailService {
	
	private static Logger mailLogger = LogManager.getLogger(MailService.class);
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	private Recipient recipient;
	
	@Autowired
	private Message message;
	
	@Autowired
	private MessageParam messageParam;
	
	@Autowired
	private Template template;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageParamService messageParamService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private RecipientRepository recipientRepository;
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Autowired
	private DeliveryJobService deliveryJobService;
	
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void dataRecovery(Long deliveryJobId) {

		DeliveryJob newDeliveryJob = deliveryJobService.findById(deliveryJobId);
		List<Recipient> recipientList = recipientRepository.findByDeliveryJob(newDeliveryJob.getId());
		for (int i = 0; i < recipientList.size(); i++) {
			Recipient recipientSend = recipientList.get(i);
			mailLogger.info(recipientSend.getAddress());
			mailLogger.info(recipientSend.getType());
			if (recipientSend.getType() != null && recipientSend.getType() == RecipientType.TO) {
				recipient.setAddressTO(recipientSend.getAddress());
				recipient.setTypeCC(recipientSend.getType());
			}
			if (recipientSend.getType() != null && recipientSend.getType() == RecipientType.CC) {
				recipient.setAddressCC(recipientSend.getAddress());
				recipient.setTypeCC(recipientSend.getType());
			}
			if (recipientSend.getType() != null && recipientSend.getType() == RecipientType.BCC) {
				recipient.setAddressBCC(recipientSend.getAddress());
				recipient.setTypeBCC(recipientSend.getType());
			}
		}

		Recipient recipientSend = recipientList.get(0);
		Message messageSend = messageService.findById(recipientSend.getIdMessage());
		message.setIdTemplate(messageSend.getIdTemplate());
		Locale localeBR = new Locale("pt", "BR");
		String sendDateFormat = messageSend.getSendDate()
			.format(DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy. ' Horário:' HH:mm", localeBR));
		message.setSendDateFormat(sendDateFormat);
		message.setId(messageSend.getId());

		MessageParam messageParamSend = messageParamService.findByMessageId(messageSend.getId());
		messageParam.setName(messageParamSend.getName());
		messageParam.setValue(messageParamSend.getValue());
		messageParam.setAuthor(messageParamSend.getAuthor());

		Template templateSend = templateService.findById(messageSend.getIdTemplate());
		template.setName(templateSend.getName());

		Map<String, Object> model = new HashMap<>();
		model.put("sendDate", message.getSendDateFormat());
		model.put("subject", messageParam.getName());
		model.put("emailContent", messageParam.getValue());
		messageParam.setProps(model);
	}
	
	public void sendEmailWithAttachment(Long deliveryJobId)
		throws MessagingException {
		dataRecovery(deliveryJobId);
		MimeMessage email = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(
			email,
			MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
			StandardCharsets.UTF_8.name()
		);
		
		if (message.getId() != null) {
			List<Attachment> attachmentList = attachmentRepository.findByMessage(message.getId());
			if (!attachmentList.isEmpty()) {
				for (int i = 0; i < attachmentList.size(); i++) {
					Attachment attachmentSend = attachmentList.get(i);
					mailLogger.info(attachmentSend.getFileName());
					helper.addAttachment(
						attachmentSend.getFileName(), new ByteArrayResource(attachmentSend.getContent())
					);
				}
			}
		}
		
		Context context = new Context();
		context.setVariables(messageParam.getProps());
		
		if (message.getIdTemplate() != null && template.getName() != null) {
			String html = templateEngine.process(template.getName(), context);
			helper.setText(html, true);
			helper.setTo(InternetAddress.parse(recipient.getAddressTO()));
			if (recipient.getAddressCC() != null) {
				helper.setCc(InternetAddress.parse(recipient.getAddressCC()));
			}
			if (recipient.getAddressBCC() != null) {
				helper.setBcc(InternetAddress.parse(recipient.getAddressBCC()));
			}
			helper.setSubject(messageParam.getName());
			javaMailSender.send(email);
		}
	}
}
