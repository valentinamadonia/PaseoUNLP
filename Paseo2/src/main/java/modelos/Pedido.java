package modelos;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utils.EstadoPedido;
import utils.RetiroEntrega;

@Entity
public class Pedido {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	private Date fecha;
	
	private Date fechaEntrega;
	
	@ManyToOne
	private Usuario usuario;
	
	@Enumerated(EnumType.ORDINAL) //0,1..
	private RetiroEntrega retiroEntrega;
	
	
    //para que lo cambie el admin, no confirmado es carrito
	@Enumerated(EnumType.ORDINAL) //0,1,2..
	private EstadoPedido estado;
	
	@OneToOne
	private PuntoDeRetiro direccionRetiro;
	
	private String direccionEntrega;
	private BigDecimal montoTotal;
	private String rangoHorario;
	
	@OneToMany(mappedBy = "pedido")
	private List<ProductoEncargado> listaProductos;
	
	@ManyToOne 
	private Ronda ronda;
	
	public Pedido() {
		
	}
	public Pedido(Date fecha, Date fechaEntrega, Usuario usuario, RetiroEntrega retiroEntrega, EstadoPedido estado,
			PuntoDeRetiro direccionRetiro, String direccionEntrega, BigDecimal montoTotal, String rangoHorario,
			List<ProductoEncargado> listaProductos, Ronda ronda) {
		super();
		this.fecha = fecha;
		this.fechaEntrega = fechaEntrega;
		this.usuario = usuario;
		this.retiroEntrega = retiroEntrega;
		this.estado = estado;
		this.direccionRetiro = direccionRetiro;
		this.direccionEntrega = direccionEntrega;
		this.montoTotal = montoTotal;
		this.rangoHorario = rangoHorario;
		this.listaProductos = listaProductos;
		this.ronda = ronda;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public RetiroEntrega getRetiroEntrega() {
		return retiroEntrega;
	}
	public void setRetiroEntrega(RetiroEntrega retiroEntrega) {
		this.retiroEntrega = retiroEntrega;
	}
	public EstadoPedido getEstado() {
		return estado;
	}
	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}
	public PuntoDeRetiro getDireccionRetiro() {
		return direccionRetiro;
	}
	public void setDireccionRetiro(PuntoDeRetiro direccionRetiro) {
		this.direccionRetiro = direccionRetiro;
	}
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getRangoHorario() {
		return rangoHorario;
	}
	public void setRangoHorario(String rangoHorario) {
		this.rangoHorario = rangoHorario;
	}
	public List<ProductoEncargado> getListaProductos() {
		return listaProductos;
	}
	public void setListaProductos(List<ProductoEncargado> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public Ronda getRonda() {
		return ronda;
	}
	public void setRonda(Ronda ronda) {
		this.ronda = ronda;
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fecha=" + fecha + ", fechaEntrega=" + fechaEntrega + ", usuario=" + usuario
				+ ", retiroEntrega=" + retiroEntrega + ", estado=" + estado + ", direccionRetiro=" + direccionRetiro
				+ ", direccionEntrega=" + direccionEntrega + ", montoTotal=" + montoTotal + ", rangoHorario="
				+ rangoHorario + ", listaProductos=" + listaProductos + ", ronda=" + ronda + "]";
	}
	

}
