package br.com.otavio.delivery;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class RabbitSender {

	@Autowired
	RabbitTemplate rabbitTemplate;

	static final String EXCHANGENAME = "DeliveryExchange";
	
	static final String QUEUENAME = "DeliveryQueue";

	@Bean
	Queue queue() {
		return new Queue(QUEUENAME, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(EXCHANGENAME);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("delivery");
	}
	
	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
		MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUENAME);
		container.setMessageListener(listenerAdapter);
		container.setDefaultRequeueRejected(false);
		container.setMissingQueuesFatal(false);
		return container;
	}
	
	@Bean
	MessageListenerAdapter listenerAdapter(RabbitReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	public void send(Long deliveryJob, String key) {
		rabbitTemplate.convertAndSend(EXCHANGENAME, key, deliveryJob);
	}
}
