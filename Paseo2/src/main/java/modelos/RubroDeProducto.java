package modelos;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utils.Estado;

@Entity
public class RubroDeProducto {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
    private String nombre;
    
    @Enumerated(EnumType.ORDINAL)
    private Estado estado;
    
    @ManyToMany(mappedBy ="rubros")
    @JsonIgnore
    private List<Producto> productos= new ArrayList<>();
    
    public RubroDeProducto() {
    	
    }
	public RubroDeProducto(String nombre, Estado estado, List<Producto> productos) {
		super();
		this.nombre = nombre;
		this.estado = estado;
		this.productos = productos;
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
	public List<Producto> getProductos() {
		return productos;
	}
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	
	
    
}
