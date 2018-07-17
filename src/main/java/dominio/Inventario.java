package dominio;

import java.io.Serializable;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

@Component
@JsonComponent
public class Inventario implements Serializable {  
	private static final long serialVersionUID = 2L;
	protected int id;
	protected String fecha;
	
	public Inventario() {
		super();
	}
	
	public Inventario(int id,String fecha) {
		this.id = id;
		this.fecha = fecha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public String getFecha() {
		return fecha;
	}

}
