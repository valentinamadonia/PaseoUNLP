package dao;
import modelos.Productor;
import utils.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;
import jakarta.inject.Inject;

@Service
@RequestScoped
public class ProductorDAO implements IProductorDAO {

	//private EntityManager manager=DaoUtils.getEntityManager();
	private final IDAOUtils daoUtils;
	@Inject
	public ProductorDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	@Override
	public List<Productor> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<Productor> lista=(List<Productor>)manager.createQuery("from Productor").getResultList();
		return lista;
	}

	@Override
	public void agregar(Productor element) {
		EntityManager manager = daoUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(element);
			manager.getTransaction().commit();
		} catch (Exception e) {
			//manager.getTransaction().rollback();
		}
	}

	@Override
	public void modificar(Productor element) {
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
	public void eliminar(Productor element) {
		EntityManager manager = daoUtils.getEntityManager();
		element.setEstado(Estado.SUSPENDIDO);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	public void activar(Productor element) {
		EntityManager manager = daoUtils.getEntityManager();
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
	public Productor getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(Productor.class,id);
		
	}
}
