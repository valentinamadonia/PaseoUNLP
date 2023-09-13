package modelos;

import javax.persistence.*;

@Entity
public class Image {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	private String nombre;
	private String imagen;

	
	public Image() {
		
	}
	
	public Image(String nombre, String imagen) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + "]";
	}
}
