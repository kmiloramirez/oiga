package com.prueba.oiga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import dominio.Proceso;
import dominio.Solicitud;

@Component

public class ConsumoMensajes {
	@Autowired
	Proceso proceso;
	
	@JmsListener(destination = "cola_de_solicitudes")
    public void consumo(int cantidad) {
		proceso.procesar(new Solicitud(cantidad));
        
    }

}
