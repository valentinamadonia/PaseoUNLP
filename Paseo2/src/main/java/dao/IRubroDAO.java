package dao;

import java.util.List;

import org.jvnet.hk2.annotations.Contract;

import modelos.RubroDeProducto;
@Contract
public interface IRubroDAO extends GenericDAO<RubroDeProducto> {
	public void activar(RubroDeProducto element);
	List<RubroDeProducto> getList();
	RubroDeProducto getById(Long id);
}
