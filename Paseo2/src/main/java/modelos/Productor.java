package modelos;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utils.Estado;

@Entity
public class Productor {
	
	 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	 private Long id;
     private String nombre;
     private String descripcion;
     
     @Enumerated(EnumType.ORDINAL)
     private Estado estado;
     
     @OneToMany(mappedBy = "productor")
     @JsonIgnore
     private List<Producto> productos= new ArrayList<>();
     
     @OneToMany
     @JsonIgnore
     private List<Image> imagenes;
     
     public Productor() {
    	 
     }
     
	public Productor(String nombre, String descripcion, Estado estado, List<Producto> productos,
			List<Image> imagenes) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.productos = productos;
		this.imagenes = imagenes;
	}
	
	public List<Image> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<Image> imagenes) {
		this.imagenes = imagenes;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	@Override
	public String toString() {
		return "Productor [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", productos=" + productos + ", imagenes=" + imagenes + "]";
	}
	
	public String imprimirProductos() {
		return productos.toString();
	}
     
}
