package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jvnet.hk2.annotations.Contract;

@Contract
public interface IDAOUtils {
	
	public EntityManager getEntityManager();
}
