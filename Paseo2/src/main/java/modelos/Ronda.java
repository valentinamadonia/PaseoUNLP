package modelos;

import java.util.Date;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import utils.Estado;

@Entity
public class Ronda {
	
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaRetiro; 
	private String rangoHorario;
	
	@Enumerated(EnumType.ORDINAL)
	@Schema(implementation = Estado.class)
    private Estado estado;
	
	@OneToMany(mappedBy = "ronda")
	@JsonIgnore
	private List<Pedido> pedidos;
	
	public Ronda() {
		
	}
	public Ronda(Date fechaInicio, Date fechaFin, Date fechaRetiro, String rangoHorario,Estado estado) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaRetiro = fechaRetiro;
		this.rangoHorario = rangoHorario;
		this.estado = estado;
	}
	
	public String getRangoHorario() {
		return rangoHorario;
	}
	public void setRangoHorario(String rangoHorario) {
		this.rangoHorario = rangoHorario;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public Long getId() {
		return id;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Date getFechaRetiro() {
		return fechaRetiro;
	}
	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	@Override
	public String toString() {
		return "Ronda [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaRetiro="
				+ fechaRetiro + ", rangoHorario=" + rangoHorario + ", estado=" + estado + ", pedidos=" + pedidos + "]";
	}
	
	
}
