package dominio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.prueba.oiga.ConsumoMensajes;
import com.prueba.oiga.ProductorMensajes;
import com.prueba.oiga.WidgetsService;
@Component
public class Proceso {
	public Proceso() {
		super();
	}
	
	@Autowired
	private ProductorMensajes productorMensajes;
	@Autowired
	ConsumoMensajes consumoMensajes ;
	@Autowired
	WidgetsService widgetsService;
	@Value("${id.widgets}")
	private int id;
	
	public List<Inventario> getItems() {
		return items;
	}

	private static final int CANTIDAD_DE_PRODUCTOS_POR_MIN = 5;
	private int capacidadActual = CANTIDAD_DE_PRODUCTOS_POR_MIN;
	private LocalTime horaDeProduccion;
	private List<Inventario> items;
	
	public void iniciarProceso(Solicitud solicitud ){
		productorMensajes.publicar(solicitud.getCantidad());
	}
	public void procesar(Solicitud solicitud ){
		items= new  ArrayList<>();
		revisarTiempo();
		if(revisarCapacidad(solicitud)) {
			crear(solicitud.getCantidad());
		}
		else {
			while(solicitud.getCantidad()>0) {
				if(capacidadActual==0) {
					revisarTiempo();
					continue;
				}
				int porFabricar=solicitud.getCantidad()-capacidadActual;
				crear(solicitud.getCantidad());
				solicitud.setCantidad(porFabricar);
			}
		}
		widgetsService.sendItems();
		
	}
	private void crear(int cantidad) {
		while(capacidadActual!=0 && cantidad!=0 ){
			  Inventario item =new Inventario(id++, LocalTime.now().toString())	;
			  items.add(item);
			  --cantidad;
			  --capacidadActual;
			  
		}	
	}
	private void revisarTiempo() {
		LocalTime horaActual= LocalTime.now();
		if(horaDeProduccion==null) {
			horaDeProduccion=horaActual;
		}
		if(existeDiferenciaEnMinutos(horaActual) || existeDiferenciaEnHoras(horaActual)){
			horaDeProduccion=LocalTime.now();
			capacidadActual=CANTIDAD_DE_PRODUCTOS_POR_MIN;
		}
	}
	private boolean existeDiferenciaEnHoras(LocalTime horaActual) {
		return horaActual.getHour()-horaDeProduccion.getHour()!=0;
	}
	private boolean existeDiferenciaEnMinutos(LocalTime horaActual) {
		return horaActual.getMinute()-horaDeProduccion.getMinute()!=0;
	}
	public boolean revisarCapacidad(Solicitud solicitud) {
		return solicitud.getCantidad()>capacidadActual;			
				
	}	

}
