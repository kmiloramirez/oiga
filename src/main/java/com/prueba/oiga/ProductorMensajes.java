package com.prueba.oiga;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productor")
public class ProductorMensajes {
	
	@Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;
	
    @RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
    public String publicar (int cantidad) {

        jmsTemplate.convertAndSend(queue, cantidad);

        return "Published Successfully";
    }
}
