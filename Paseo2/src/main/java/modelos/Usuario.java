package modelos;
import utils.Estado;
import utils.Rol;

import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario{ 
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	@Column(unique=true)
	private String email;
	
	private String direccion;
	
	private int telefono;
	
	private String password;
	
	@OneToMany(mappedBy="usuario")
	@JsonIgnore
	private List<Pedido> pedidos;

	
	@Enumerated(EnumType.ORDINAL)
	private Rol rol;
	
	@Enumerated(EnumType.ORDINAL)
	private Estado estado;
	
	public Usuario() {
		
	}
	public Usuario(String nombre, String apellido, String email, String direccion, int telefono, String password,
			List<Pedido> pedidos, Rol rol, Estado estado) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.direccion = direccion;
		this.telefono = telefono;
		this.password = password;
		this.pedidos = pedidos;
		this.rol = rol;
		this.estado = estado;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", password=" + password + ", pedidos="
				+ pedidos + ", rol=" + rol + ", estado=" + estado + "]";
	}
	

}
