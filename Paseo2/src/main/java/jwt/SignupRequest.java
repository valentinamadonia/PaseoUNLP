package jwt;

import utils.Rol;

public class SignupRequest {
	private String nombre; 
	private String email;
	private String password;
	private Rol rol;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public String getUserName() {
		return nombre;
	}
	public void setUserName(String userName) {
		this.nombre = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
