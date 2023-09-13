package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;


@Service
@RequestScoped
public class DaoUtils implements IDAOUtils {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("persistencia");
	EntityManager manager=emf.createEntityManager();
	
	@Override
	public EntityManager getEntityManager() {
		return manager;
	}
//	private final EntityManager entityManager;
//
//    @Inject
//    public DaoUtils(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

	
}
