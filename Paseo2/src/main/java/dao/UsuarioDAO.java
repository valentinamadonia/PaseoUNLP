package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;
import jakarta.inject.Inject;
import modelos.Usuario;
import utils.Estado;
import utils.Rol;

@Service
@RequestScoped
public class UsuarioDAO implements IUsuarioDAO {

	private final IDAOUtils daoUtils;
	@Inject
	public UsuarioDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	public List<Usuario> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<Usuario> lista=(List<Usuario>)manager.createQuery("from Usuario").getResultList();
		
		return lista;
	}


	public Usuario getUser(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(Usuario.class,id);
	}
	
	public Usuario getUserByEmail(String email) {
		EntityManager manager = daoUtils.getEntityManager();
		Query query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
        query.setParameter("email", email);
        try {
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
	}
	
	@Override
	public void agregar(Usuario element) {
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
	public void modificar(Usuario element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Usuario.class, element.getId());
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}

	@Override
	public void eliminar(Usuario element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Usuario.class, element.getId());
		element.setEstado(Estado.SUSPENDIDO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	
	}
	public void activar(Usuario element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Usuario.class, element.getId());
		element.setEstado(Estado.ACTIVO);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void adminRol(Usuario element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Usuario.class, element.getId());
		element.setRol(Rol.ADMINISTRADOR);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
    public void userRol(Usuario element) {
    	EntityManager manager = daoUtils.getEntityManager();
    	//element=manager.find(Usuario.class, element.getId());
		element.setRol(Rol.USUARIO);
		try {
			manager.getTransaction().begin();
		    manager.merge(element);
		    manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}


	@Override
	public Usuario getById(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(Usuario.class,id);
		
	}
	
}
