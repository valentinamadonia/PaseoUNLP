package modelos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import utils.Estado;

@Entity
public class PuntoDeRetiro {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	private String direccion;
	private String nombre;
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Estado.class)
    private Estado estado;
	
	public PuntoDeRetiro() {
		super();
		
	}

	public PuntoDeRetiro(String direccion, String nombre,Estado estado) {
		super();
		this.direccion = direccion;
		this.nombre = nombre;
		this.estado= estado;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "PuntoDeRetiro [id=" + id + ", direccion=" + direccion + ", nombre=" + nombre + "]";
	}

}
