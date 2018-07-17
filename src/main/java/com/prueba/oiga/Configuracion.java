package com.prueba.oiga;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configuracion {
	@Value("${activemq.broker-url}")
    private String brokerUrl;
	
	@Value("${nombre.cola}")
	private String nombreCola;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(nombreCola);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
    }

    @Bean 
    public int binInt () {
    	return 1;
    }
    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
     

}
