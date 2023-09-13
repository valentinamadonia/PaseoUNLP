package modelos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import utils.Estado;

@Entity
public class ProductoEncargado {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int cantidad;
	
	@OneToOne
	private Producto producto;
	
	@ManyToOne
	@JsonIgnore
	private Pedido pedido;
	
	private Estado estado;
	
	public ProductoEncargado() {
		super();
	}
	
	public ProductoEncargado(int cantidad, Producto producto, Pedido pedido, Estado estado) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.pedido = pedido;
		this.estado = estado;
	}

	public ProductoEncargado(ProductoEncargado productoEncargado) {
		this.id = productoEncargado.getId();
        this.cantidad = productoEncargado.getCantidad();

        // Copiar propiedades de Producto
        this.producto = new Producto();
        this.producto.setId(productoEncargado.getProducto().getId());
        this.producto.setNombre(productoEncargado.getProducto().getNombre());
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
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public String toString() {
		return "ProductoEncargado [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + ", pedido="
				+ pedido + ", estado=" + estado + "]";
	}
	
}
