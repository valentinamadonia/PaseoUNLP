package dao;

import org.jvnet.hk2.annotations.Contract;

import modelos.ProductoEncargado;

@Contract
public interface IProductoEncargadoDAO extends GenericDAO<ProductoEncargado> {
	public void activar(ProductoEncargado element);
}
