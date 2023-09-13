package dao;

import modelos.Producto;

public interface IProductoDAO extends GenericDAO<Producto> {
	public void activar(Producto element);
}
