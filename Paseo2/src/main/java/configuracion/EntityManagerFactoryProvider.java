package configuracion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.glassfish.hk2.api.Factory;

import jakarta.inject.Inject;

public class EntityManagerFactoryProvider implements Factory<EntityManager> {

	private final EntityManagerFactory emFactory;
	    
	@Inject
	public EntityManagerFactoryProvider(EntityManagerFactory emFactory) {
	        this.emFactory = Persistence.createEntityManagerFactory("persistencia");
	}
	 
	@Override
	public EntityManager provide() {
		 return emFactory.createEntityManager();
	}

	@Override
	public void dispose(EntityManager instance) {
		instance.close();
	}

}
