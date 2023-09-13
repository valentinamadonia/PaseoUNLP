package dao;
import modelos.Productor;
import modelos.Ronda;
import java.util.List;

import javax.persistence.EntityManager;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;

@Service
@RequestScoped
public class RondaDAO implements IRondaDAO{
	
	//private EntityManager manager=DaoUtils.getEntityManager();
	private final IDAOUtils daoUtils;
	@Inject
	public RondaDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	@Override
	public List<Ronda> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<Ronda> lista=(List<Ronda>)manager.createQuery("from Ronda").getResultList();
		return lista;
	}

	@Override
	public void agregar(Ronda element) {
		EntityManager manager = daoUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
			manager.persist(element);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public void modificar(Ronda element) {
		EntityManager manager = daoUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(Ronda element) {
		EntityManager manager = daoUtils.getEntityManager();
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Ronda getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(Ronda.class,id);
	}

}
