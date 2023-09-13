package dao;

import java.util.List;


import javax.persistence.EntityManager;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import modelos.PuntoDeRetiro;
import modelos.Ronda;
import modelos.Usuario;
@Service
@RequestScoped
public class PuntoDeRetiroDAO implements IPuntoDeRetiroDAO{

	//private EntityManager manager=DaoUtils.getEntityManager();
	
	private final IDAOUtils daoUtils;
	@Inject
	public PuntoDeRetiroDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	@Override
	public List<PuntoDeRetiro> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<PuntoDeRetiro> lista=(List<PuntoDeRetiro>)manager.createQuery("from PuntoDeRetiro").getResultList();
		return lista;
	}

	public PuntoDeRetiro getPuntoDeRetiro(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(PuntoDeRetiro.class,id);
	}
	@Override
	public void agregar(PuntoDeRetiro element) {
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
	public void modificar(PuntoDeRetiro element) { //recibir elemento modificado
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(PuntoDeRetiro.class, element.getId());
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminar(PuntoDeRetiro element) {
		EntityManager manager = daoUtils.getEntityManager();
		element=manager.find(PuntoDeRetiro.class, element.getId());
		try {
			manager.getTransaction().begin();
			manager.remove(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public PuntoDeRetiro getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(PuntoDeRetiro.class,id);
		
	}
	

}
