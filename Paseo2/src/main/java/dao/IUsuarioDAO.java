package dao;

import org.jvnet.hk2.annotations.Contract;

import modelos.Usuario;

@Contract
public interface IUsuarioDAO extends GenericDAO<Usuario>{
	public Usuario getUser(Long id);
	public Usuario getUserByEmail(String email);
	public void activar(Usuario element) ;
	public void adminRol(Usuario element);
	public void userRol(Usuario element);
}
