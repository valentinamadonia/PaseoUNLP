package jwt;

import java.util.List;

import utils.Rol;

public class AuthResponse {
	  private String token;
	  private Rol rol;

	  public String getToken() {
	    return token;
	  }

	  public void setToken(String token) {
	    this.token = token;
	  }

	  public Rol getRol() {
	    return this.rol;
	  }

	  public void setRol(Rol rol) {
	    this.rol = rol;
	  }  
}
