package dao;
import java.util.List;

import javax.persistence.EntityManager;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import modelos.Pedido;
import modelos.ProductoEncargado;
import utils.Estado;   
@Service
@RequestScoped
public class ProductoEncargadoDAO implements IProductoEncargadoDAO {

	private final IDAOUtils daoUtils;
	@Inject
	public ProductoEncargadoDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	@Override
	public List<ProductoEncargado> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<ProductoEncargado> lista=(List<ProductoEncargado>)manager.createQuery("from ProductoEncargado").getResultList();
		return lista;
	}

	@Override
	public void agregar(ProductoEncargado element) {
		EntityManager manager = daoUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(element);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	@Override
	public void modificar(ProductoEncargado element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(ProductoEncargado.class, element.getId());
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}

	@Override
	public void eliminar(ProductoEncargado element) { //si se cancela el pedido
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(ProductoEncargado.class, element.getId());
		element.setEstado(Estado.SUSPENDIDO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	public void activar(ProductoEncargado element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(ProductoEncargado.class, element.getId());
		element.setEstado(Estado.ACTIVO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}


	@Override
	public ProductoEncargado getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(ProductoEncargado.class,id);
		
	}

}
