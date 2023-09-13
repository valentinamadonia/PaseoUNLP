package dao;
import java.util.List;

import javax.persistence.EntityManager;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;

import jakarta.inject.Inject;
import modelos.Pedido;
import modelos.Usuario;
import utils.EstadoPedido;
import utils.RetiroEntrega;
@Service
@RequestScoped
public class PedidoDAO implements IPedidoDAO {

	private final IDAOUtils daoUtils;
	@Inject
	public PedidoDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	
	@Override
	public List<Pedido> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<Pedido> lista=(List<Pedido>)manager.createQuery("from Pedido").getResultList();
		return lista;
	}

	public Pedido getPedido(Long id) {
		EntityManager manager = daoUtils.getEntityManager();
		return manager.find(Pedido.class,id);
	}
	
	@Override
	public void agregar(Pedido element) {
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
	public void modificar(Pedido element) {
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
	public void eliminar(Pedido element) {
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setEstado(EstadoPedido.CANCELADO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void activar(Pedido element) { //que vuelva a ser pedido 
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setEstado(EstadoPedido.NOCONFIRMADO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void confirmar(Pedido element) { //que confirme el pedido el admin
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setEstado(EstadoPedido.CONFIRMADO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void marcarComoRetirado(Pedido element) { 
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setEstado(EstadoPedido.RETIRADO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void marcarComoEntregado(Pedido element) { 
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setEstado(EstadoPedido.ENTREGADO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void marcarParaEntrega(Pedido element) { 
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setRetiroEntrega(RetiroEntrega.ENTREGA);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}
	public void marcarParaRetiro(Pedido element) { 
		EntityManager manager = daoUtils.getEntityManager();
		//element=manager.find(Pedido.class, element.getId());
		element.setRetiroEntrega(RetiroEntrega.RETIRO);
		try {
			manager.getTransaction().begin();
			manager.merge(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			manager.getTransaction().rollback();
		}
	}



	@Override
	public Pedido getById(Long id) {
		return null;
	}

}
