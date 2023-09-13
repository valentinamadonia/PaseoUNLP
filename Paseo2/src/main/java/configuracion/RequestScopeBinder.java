package configuracion;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import dao.DaoUtils;
import dao.IDAOUtils;
import dao.IImageDAO;
import dao.IPedidoDAO;
import dao.IProductoDAO;
import dao.IProductoEncargadoDAO;
import dao.IProductorDAO;
import dao.IPuntoDeRetiroDAO;
import dao.IRondaDAO;
import dao.IRubroDAO;
import dao.IUsuarioDAO;
import dao.ImageDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dao.ProductoEncargadoDAO;
import dao.ProductorDAO;
import dao.PuntoDeRetiroDAO;
import dao.RondaDAO;
import dao.RubroDelProductoDAO;
import dao.UsuarioDAO;

public class RequestScopeBinder extends AbstractBinder{

	@Override
	protected void configure() {
		bind(UsuarioDAO.class).to(IUsuarioDAO.class);
		bind(ImageDAO.class).to(IImageDAO.class);
		bind(RondaDAO.class).to(IRondaDAO.class);
		bind(ProductorDAO.class).to(IProductorDAO.class);

		bind(RubroDelProductoDAO.class).to(IRubroDAO.class);
		
		bind(ProductoDAO.class).to(IProductoDAO.class);
		bind(PedidoDAO.class).to(IPedidoDAO.class);
		bind(ProductoEncargadoDAO.class).to(IProductoEncargadoDAO.class);
		bind(PuntoDeRetiroDAO.class).to(IPuntoDeRetiroDAO.class);
		bind(DaoUtils.class).to(IDAOUtils.class);
		
	}

}
