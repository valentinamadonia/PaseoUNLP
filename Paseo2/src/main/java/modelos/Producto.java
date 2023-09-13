package modelos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utils.Estado;

@Entity
public class Producto {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	@ManyToMany
	private List<RubroDeProducto> rubros;
	
	private String nombre;
	private int stock;
	private String descripcion;
	private BigDecimal precio;
	@Enumerated(EnumType.ORDINAL)
	private Estado estado;
	
	@ManyToOne
	private Productor productor;
	
	@OneToMany
	private List<Image> imagenes;
	
	public Producto() {
		
	}
	
	public Producto( String nombre, int stock, String descripcion, BigDecimal precio,
			Estado estado, Productor productor, List<Image> imagenes,List<RubroDeProducto> rubros) {
		super();
		this.nombre = nombre;
		this.stock = stock;
		this.descripcion = descripcion;
		this.precio = precio;
		this.estado = estado;
		this.productor = productor;
		this.imagenes = imagenes;
		this.rubros = rubros;
	}

	
	public List<RubroDeProducto> getRubros() {
		return rubros;
	}
	public void setRubros(List<RubroDeProducto> rubros) {
		this.rubros = rubros;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public List<Image> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<Image> imagenes) {
		this.imagenes = imagenes;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Productor getProductor() {
		return productor;
	}
	public void setProductor(Productor productor) {
		this.productor = productor;
	}





	
}
