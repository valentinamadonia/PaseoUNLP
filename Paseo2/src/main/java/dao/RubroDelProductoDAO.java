package dao;
import java.util.List;

import javax.persistence.EntityManager;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import modelos.RubroDeProducto;
import modelos.Usuario;
import utils.Estado;
@Service
@RequestScoped
public class RubroDelProductoDAO implements IRubroDAO {

	//private EntityManager manager=DaoUtils.getEntityManager();
	private final IDAOUtils daoUtils;
	@Inject
	public RubroDelProductoDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	@Override
	public List<RubroDeProducto> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<RubroDeProducto> lista=(List<RubroDeProducto>)manager.createQuery("from RubroDeProducto").getResultList();
		return lista;
	}

	@Override
	public void agregar(RubroDeProducto element) {
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
	public void modificar(RubroDeProducto element) {
		EntityManager manager = daoUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}

	@Override
	public void eliminar(RubroDeProducto element) {
		EntityManager manager = daoUtils.getEntityManager();
		element=manager.find(RubroDeProducto.class, element.getId());
		element.setEstado(Estado.SUSPENDIDO);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	public void activar(RubroDeProducto element) {
		EntityManager manager = daoUtils.getEntityManager();
		element=manager.find(RubroDeProducto.class, element.getId());
		element.setEstado(Estado.ACTIVO);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}


	public RubroDeProducto getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(RubroDeProducto.class,id);
		
	}

}
