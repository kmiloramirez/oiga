package com.prueba.oiga;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import dominio.Proceso;
import dominio.Solicitud;

@RestController
@Transactional
@RequestMapping(value = "/empresaA")
@EnableAutoConfiguration
public class WidgetsService {
	@Autowired
	Proceso proceso;
	@Autowired
	RestTemplate restTemplate ;
	@RequestMapping(value = "/crear")
	public String home() {
		return "Empresa A";
	}

	@RequestMapping(value = "/crear/widgets", method = RequestMethod.POST)
	@ResponseBody
	public void servicioCrearWidgets(@RequestBody Solicitud solicitud) { 
		 proceso.iniciarProceso(solicitud);
	}
	
	
	public void sendItems() {	
		restTemplate.postForObject("http://localhost:8082/empresaB/recibir", proceso.getItems(),String.class);
	}
	
}
