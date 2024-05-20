package dao;
import java.util.List;
import javax.persistence.EntityManager;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;
import jakarta.inject.Inject;
import modelos.Producto;
import utils.Estado;

@Service
@RequestScoped
public class ProductoDAO implements IProductoDAO{
	
	//private EntityManager manager=DaoUtils.getEntityManager();
	private final IDAOUtils daoUtils;
	@Inject
	public ProductoDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	@Override
	public List<Producto> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<Producto> lista=(List<Producto>)manager.createQuery("from Producto").getResultList();
		return lista;
	}

	@Override
	public void agregar(Producto element) { //agrego primero un productor ,en la prueba hago un element.productor= productor.id?
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
	public void modificar(Producto element) {
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
	public void eliminar(Producto element) {
		EntityManager manager = daoUtils.getEntityManager();
		element=manager.find(Producto.class, element.getId());
		element.setEstado(Estado.SUSPENDIDO);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void activar(Producto element) {
		EntityManager manager = daoUtils.getEntityManager();
		element=manager.find(Producto.class, element.getId());
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
	public Producto getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(Producto.class,id);
		
	}

}
