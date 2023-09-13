package dao;
import java.util.List;
import javax.persistence.EntityManager;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.jvnet.hk2.annotations.Service;
import jakarta.inject.Inject;
import modelos.Image;


@Service
@RequestScoped
public class ImageDAO  implements IImageDAO{

	//private EntityManager manager=DaoUtils.getEntityManager();
	private final IDAOUtils daoUtils;
	@Inject
	public ImageDAO(IDAOUtils daoUtils) {
		this.daoUtils = daoUtils;
	}
	
	
	@Override
	public List<Image> getList() {
		EntityManager manager = daoUtils.getEntityManager();
		List<Image> lista=(List<Image>)manager.createQuery("from Image").getResultList();
		return lista;
	}

	@Override
	public void agregar(Image element) {
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
	public void modificar(Image element) {
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
	public void eliminar(Image element) {
		EntityManager manager = daoUtils.getEntityManager();
		element=manager.find(Image.class, element.getId());
		try {
			manager.getTransaction().begin();
			manager.remove(element);
			manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public Image getById(Long id) {
		return null;
	}

}
