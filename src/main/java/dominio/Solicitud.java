package dominio;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component

public class Solicitud implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected int cantidad;
	protected int numeroSolicitud;

	public Solicitud() {
		super();
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(int numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public Solicitud (int cantidad) {
		this.cantidad=cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	
	
}
