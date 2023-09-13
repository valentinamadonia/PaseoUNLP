package dao;

import org.jvnet.hk2.annotations.Contract;

import modelos.Productor;
@Contract
public interface IProductorDAO extends GenericDAO<Productor>{
	public void activar(Productor element);
}
