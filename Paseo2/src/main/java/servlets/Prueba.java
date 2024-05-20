package servlets;
 //PRUEBA
import java.io.IOException;
import java.util.List;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import configuracion.RequestScopeBinder;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.*;
import utils.Estado;
import utils.Rol;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
/**
 * Servlet implementation class Prueba
 */

@WebServlet("/Prueba")
public class Prueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PuntoDeRetiro a,a2,a3;
	private Image m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17;
	private Ronda r1,r2,r3;
	private Productor p1,p2,p3;
	private RubroDeProducto rubro1,rubro2,rubro3,rubro4,rubro5,rubro6,rubro7,rubro8,rubro9;
	private Producto producto1,producto2,producto3,producto4,producto5,producto6,producto7,producto8,producto9,producto10,producto12;
	private Usuario u1,u2,u3;
    private Pedido pedido1,pedido2;
	private ProductoEncargado pe1,pe2,pe3;
    /**
     * Default constructor. 
     */
     public Prueba() {
    	 
     }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
		ServiceLocatorUtilities.bind(locator, new RequestScopeBinder());
		
		IUsuarioDAO u = locator.getService(IUsuarioDAO.class);
		IPuntoDeRetiroDAO punto= locator.getService(IPuntoDeRetiroDAO.class);
		IImageDAO i = locator.getService(IImageDAO.class);
		IRondaDAO r = locator.getService(IRondaDAO.class);
		IProductorDAO productor = locator.getService(IProductorDAO.class);
		IRubroDAO rubro = locator.getService(IRubroDAO.class);
		IProductoDAO producto = locator.getService(IProductoDAO.class);
		IPedidoDAO pedido = locator.getService(IPedidoDAO.class);
		IProductoEncargadoDAO pe = locator.getService(IProductoEncargadoDAO.class);
		
		System.out.println("PUNTO DE RETIRO");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar puntos de retiros");
		a=new PuntoDeRetiro("Calle 50 &, Av. 120","Facultad de informatica",Estado.ACTIVO);
		agregarPuntoDeRetiro(a,punto);
		a2=new PuntoDeRetiro("Calle 7 1023","Una direccion",Estado.ACTIVO);
		agregarPuntoDeRetiro(a2,punto);
		a3=new PuntoDeRetiro("Calle 7 entre 47 y 48","Rectorado",Estado.ACTIVO);
		agregarPuntoDeRetiro(a3,punto);

		
		System.out.println("IMAGEN");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar imagenes");
		m1=new Image("bolson","http://imagendelbolson");
		i.agregar(m1);
		m2=new Image("maple huevos","http://imagenmapple");
		i.agregar(m2);
		m3=new Image("papa","http://imagendelapapa");
		i.agregar(m3);
		m4=new Image("productor1","http://imagenproductor1");
		i.agregar(m4);
		m5=new Image("productor2","http://imagenproductor2");
		i.agregar(m5);
		m6=new Image("productor3","http://imagenproductor3");
		i.agregar(m6);
		m7=new Image("huevos","https://cdn.discordapp.com/attachments/751986280405139556/1125576983254945852/huevos.JPG");
		i.agregar(m7);
		m8=new Image("mix cereales","https://cdn.discordapp.com/attachments/751986280405139556/1125752243233239131/72_nuts_min.jpg");
		i.agregar(m8);
		m9=new Image("pan lactal blanco","https://cdn.discordapp.com/attachments/751986280405139556/1125752378461794324/pan_lactal_blanco.jpg");
		i.agregar(m9);
		m10=new Image("pan lactal salvado","https://cdn.discordapp.com/attachments/751986280405139556/1125752530387869787/Pan_Lactal.jpg");
		i.agregar(m10);
		m11=new Image("pan hamburguesa","https://cdn.discordapp.com/attachments/751986280405139556/1125752644892381324/panhamburguesa.jpg");
		i.agregar(m11);
		m12=new Image("tarta tofi","https://cdn.discordapp.com/attachments/751986280405139556/1125752788291440710/Tarta_tofi.jpg");
		i.agregar(m12);
		m13=new Image("yerba","https://cdn.discordapp.com/attachments/751986280405139556/1125752889575489596/yerba.webp");
		i.agregar(m13);
		m14=new Image("jabon liquido","https://cdn.discordapp.com/attachments/751986280405139556/1125752989156655154/limpieza2.jpg");
		i.agregar(m14);
		m15=new Image("tomate","https://cdn.discordapp.com/attachments/751986280405139556/1125753070807154728/tomate.jpg");
		i.agregar(m15);
		m16=new Image("miel","https://cdn.discordapp.com/attachments/751986280405139556/1125753148938666014/Miel.jpg");
		i.agregar(m16);
		m17=new Image("papa lavada","https://cdn.discordapp.com/attachments/751986280405139556/1125753272603512852/papa_lavada.jpg");
		i.agregar(m17);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\n");
		
		
		System.out.println("RONDA");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar rondas");
		r1=new Ronda(new Date(2023,6,5),new Date(2023,6,9),new Date(2023,6,10),"9am-15pm",Estado.ACTIVO);
		r2=new Ronda(new Date(2023,6,12),new Date(2023,6,16),new Date(2023,6,17),"9am-17pm",Estado.ACTIVO);
		r3=new Ronda(new Date(2023,6,19),new Date(2023,6,23),new Date(2023,6,24),"9am-17pm",Estado.ACTIVO);
		r.agregar(r1);
		r.agregar(r2);
		r.agregar(r3);
		
		
		System.out.println("PRODUCTOR");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar productor");
		List<Image>imagenes=new ArrayList<Image>();
		imagenes.add(m3);
		imagenes.add(m2);
		List<Producto>productos=new ArrayList<Producto>();
		p1=new Productor("Pedro","venta de verduras",Estado.ACTIVO,new ArrayList<Producto>(),imagenes);
		productor.agregar(p1);
		List<Image>imagenes2=new ArrayList<Image>();
		imagenes2.add(m4);
		List<Producto>productos2=new ArrayList<Producto>();
		p2=new Productor("Vivivana","venta de frutas y verduras",Estado.ACTIVO,new ArrayList<Producto>(),imagenes2);
		productor.agregar(p2);
		List<Image>imagenes3=new ArrayList<Image>();
		imagenes3.add(m5);
		List<Producto>productos3=new ArrayList<Producto>();
		p3=new Productor("Susana","venta de comidas",Estado.ACTIVO,new ArrayList<Producto>(),imagenes3);
		productor.agregar(p3);
		System.out.println("-----------------------------------------------------------");
		System.out.println("Editar productor(productor 3 se agrego imagen)");
		imagenes3=p3.getImagenes();
		Image mnueva=new Image("pizza","http://imagendepizza");
		i.agregar(mnueva);
		imagenes3.add(mnueva);
		p3.setImagenes(imagenes3);
		productor.modificar(p3);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\n");
		
		
		
		System.out.println("RUBRO");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar rubro");
		List<Producto> rproductos1=new ArrayList<Producto>();
		rubro1=new RubroDeProducto("Verduras y frutas",Estado.ACTIVO,new ArrayList<Producto>());
		List<Producto> rproductos2=new ArrayList<Producto>();
		rubro2=new RubroDeProducto("Huevos y lacteos",Estado.ACTIVO,new ArrayList<Producto>());
		List<Producto> rproductos3=new ArrayList<Producto>();
		rubro3=new RubroDeProducto("Mermelada y conservas",Estado.ACTIVO,new ArrayList<Producto>());
		List<Producto> rproductos4=new ArrayList<Producto>();
		rubro4=new RubroDeProducto("Cereales y frutos secos",Estado.ACTIVO,new ArrayList<Producto>());
		List<Producto> rproductos5=new ArrayList<Producto>();
		rubro5=new RubroDeProducto("Panaderia y confiteria",Estado.ACTIVO,rproductos5);
		List<Producto> rproductos6=new ArrayList<Producto>();
		rubro6=new RubroDeProducto("Productos de limpieza",Estado.ACTIVO,rproductos6);
		List<Producto> rproductos7=new ArrayList<Producto>();
		rubro7=new RubroDeProducto("Pastas, hamburguesas y congelados",Estado.ACTIVO,rproductos7);
		List<Producto> rproductos8=new ArrayList<Producto>();
		rubro8=new RubroDeProducto("Almacen",Estado.ACTIVO,rproductos8);
		List<Producto> rproductos9=new ArrayList<Producto>();
		rubro9=new RubroDeProducto("Artesanias",Estado.ACTIVO,rproductos9);
		rubro.agregar(rubro1);
		rubro.agregar(rubro2);
		rubro.agregar(rubro3);
		rubro.agregar(rubro4);
		rubro.agregar(rubro5);
		rubro.agregar(rubro6);
		rubro.agregar(rubro7);
		rubro.agregar(rubro8);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\n");
		
		
		System.out.println("PRODUCTO");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar productos");
		List<RubroDeProducto> listar=new ArrayList<RubroDeProducto>();
		listar.add(rubro2);
		List<Image> il=new ArrayList<Image>();
		il.add(m7);
		producto1=new Producto("Huevos de campo",46,"Media docena de huevos", new BigDecimal(110),Estado.ACTIVO,p2,il,listar);
		producto.agregar(producto1);
		
		List<RubroDeProducto> listar2=new ArrayList<RubroDeProducto>();
		listar2.add(rubro4);
		List<Image> i2=new ArrayList<Image>();
		i2.add(m8);
		producto2=new Producto("Mix de cereales y frutos secos",50,"Mix de 1kg",new BigDecimal(520),Estado.ACTIVO,p2,i2,listar2);
		producto.agregar(producto2);
		
		List<RubroDeProducto> listar3=new ArrayList<RubroDeProducto>();
		listar3.add(rubro5);
		List<Image> i3=new ArrayList<Image>();
		i3.add(m9);
		producto3=new Producto("Pan lactal blanco",35,"Pan lactal de 600gr",new BigDecimal(170),Estado.ACTIVO,p1,i3,listar3);
		producto.agregar(producto3);

		List<RubroDeProducto> listar4=new ArrayList<RubroDeProducto>();
		listar4.add(rubro5);
		List<Image> i4=new ArrayList<Image>();
		i4.add(m10);
		producto4=new Producto("Pan lactal salvado",60,"Pan lactal de 600gr 100% vegano",new BigDecimal(230),Estado.ACTIVO,p3,i4,listar4);
		producto.agregar(producto4);
		
		List<RubroDeProducto> listar5=new ArrayList<RubroDeProducto>();
		listar5.add(rubro5);
		List<Image> i5=new ArrayList<Image>();
		i5.add(m11);
		producto5=new Producto("Pan para hamburguesa",60,"Pan de hamburguesa x4",new BigDecimal(120),Estado.ACTIVO,p3,i5,listar5);
		producto.agregar(producto5);
		
		List<RubroDeProducto> listar6=new ArrayList<RubroDeProducto>();
		listar6.add(rubro5);
		List<Image> i6=new ArrayList<Image>();
		i6.add(m12);
		producto12=new Producto("Tarta tofi",60,"tarta dulce de 300gr",new BigDecimal(250),Estado.ACTIVO,p3,i6,listar6);
		producto.agregar(producto12);
		
		List<RubroDeProducto> listar7=new ArrayList<RubroDeProducto>();
		listar7.add(rubro8);
		List<Image> i7=new ArrayList<Image>();
		i7.add(m13);
		producto6=new Producto("Yerba",5,"1kg",new BigDecimal(270),Estado.ACTIVO,p3,i7,listar6);
		producto.agregar(producto6);
		
		List<RubroDeProducto> listar8=new ArrayList<RubroDeProducto>();
		listar8.add(rubro6);
		List<Image> i8=new ArrayList<Image>();
		i8.add(m14);
		producto7=new Producto("Jabon liquido para manos",18,"1l",new BigDecimal(130),Estado.ACTIVO,p3,i8,listar8);
		producto.agregar(producto7);
		
		List<RubroDeProducto> listar9=new ArrayList<RubroDeProducto>();
		listar8.add(rubro1);
		List<Image> i9=new ArrayList<Image>();
		i9.add(m15);
		producto9=new Producto("Tomate",15,"1kg",new BigDecimal(290),Estado.ACTIVO,p3,i9,listar9);
		producto.agregar(producto9);
		
		List<RubroDeProducto> listar10=new ArrayList<RubroDeProducto>();
		listar10.add(rubro3);
		List<Image> i10=new ArrayList<Image>();
		i10.add(m16);
		producto8=new Producto("Miel",5,"1/2kg",new BigDecimal(290),Estado.ACTIVO,p3,i10,listar10);
		producto.agregar(producto8);
		
		List<RubroDeProducto> listar11=new ArrayList<RubroDeProducto>();
		listar11.add(rubro1);
		List<Image> i11=new ArrayList<Image>();
		i11.add(m17);
		producto10=new Producto("Papa",50,"papa lavada 1kg",new BigDecimal(260),Estado.ACTIVO,p3,i11,listar11);
		producto.agregar(producto10);
		
		//agrego los productos al productor
		productos2.add(producto1);
		productos2.add(producto2);
		p2.setProductos(productos2);
		productor.modificar(p2);
		productos.add(producto3);
		p1.setProductos(productos);
		productor.modificar(p1);
		productos3.add(producto4);
		productos3.add(producto10);
		productos3.add(producto9);
		productos3.add(producto12);
		productos3.add(producto8);
		productos3.add(producto6);
		productos3.add(producto5);
		p3.setProductos(productos3);
		productor.modificar(p3);
		//agrego el producto al rubro
		rproductos1.add(producto10);
		rproductos1.add(producto9);
		rubro1.setProductos(rproductos1);
		rubro.modificar(rubro1);
		rproductos2.add(producto1);
		rubro2.setProductos(rproductos2);
                rubro.modificar(rubro2);
                rproductos5.add(producto3);
                rproductos5.add(producto5);
                rproductos5.add(producto4);
                rproductos5.add(producto12);
		rubro5.setProductos(rproductos5);
		rubro.modificar(rubro5);
		rproductos3.add(producto8);
		rubro3.setProductos(rproductos3);
		rproductos4.add(producto2);
		rubro4.setProductos(rproductos4);
		rubro.modificar(rubro3);
		rubro.modificar(rubro4);
		rproductos6.add(producto7);
		rubro6.setProductos(rproductos6);
		rubro.modificar(rubro6);
		rproductos7.add(producto6);
		rubro8.setProductos(rproductos7);
		rubro.modificar(rubro8);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Se agregaron los productos a los productores");
		//listarProductores(productor);
		System.out.println("-----------------------------------------------------------");
		System.out.println("Se agregaron los productos a los rubros");
		//listarRubros(rubro);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\n");
		
	
		
		System.out.println("USUARIO");
		System.out.println("-----------------------------------------------------------");
		System.out.println("Agregar usuario");
		List<Pedido> pedidos1=new ArrayList<Pedido>();
		System.out.println("llego1");
		u1=new Usuario("Martin","Perez","martin@gmail.com","calle 8 1232",221456873,"martin1234",pedidos1,Rol.USUARIO,Estado.ACTIVO);
		List<Pedido> pedidos2=new ArrayList<Pedido>();
		System.out.println("llego2");
		u2=new Usuario("Elena","Martinez","ele213@gmail.com","calle 44 920",221342343,"123456",pedidos2,Rol.USUARIO,Estado.ACTIVO);
		System.out.println("llego3");
		u3=new Usuario("Pablo","Lopez","admin@gmail.com","calle 62 233",221423352,"admin",new ArrayList<Pedido>(),Rol.ADMINISTRADOR,Estado.ACTIVO);
		u.agregar(u1);
		System.out.println("llego4");
		u.agregar(u2);
		System.out.println("llego5");
		u.agregar(u3);
		System.out.println("-----------------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println("\n");
	
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public void listarProductosEncargados(IProductoEncargadoDAO pe) {
		System.out.println("Lista de productos encargados:");
		List<ProductoEncargado> lista12= pe.getList();
		for(ProductoEncargado l:lista12) {
			System.out.println("-> Id: "+ l.getId()+ ", cantidad: "+ l.getCantidad()+", estado: "+ l.getEstado());
			System.out.println("    Pedido  Id:"+l.getPedido().getId()+", fecha:" + l.getPedido().getFecha()+", usuario: "+l.getPedido().getUsuario().getId()+", fecha entrega: "+l.getPedido().getFechaEntrega()+ ", retiro o entrega: "+l.getPedido().getRetiroEntrega()+ ", estado: "+ l.getPedido().getEstado()+", direccion de retiro: "+l.getPedido().getDireccionRetiro()+", direccion de entrega: "+ l.getPedido().getRetiroEntrega()+", monto total: "+ l.getPedido().getMontoTotal()+ ", rango horario: " +l.getPedido().getRangoHorario()+ "ronda: "+ l.getPedido().getRonda());
			System.out.println("    Producto: Id: "+l.getProducto().getId()+", nombre: "+l.getProducto().getNombre()+", descripcion: "+l.getProducto().getDescripcion()+", stock: "+l.getProducto().getStock()+", estado: "+l.getProducto().getEstado()+",precio: "+l.getProducto().getPrecio());
		}
	}
	public void listarPedidos(IPedidoDAO pedido) {
		System.out.println("Lista de pedidos:");
		List<Pedido> lista12= pedido.getList();
		for(Pedido l:lista12) {
			System.out.println("->Pedido  Id:"+l.getId()+", fecha:" + l.getFecha()+", fecha entrega: "+l.getFechaEntrega()+ ", retiro o entrega: "+l.getRetiroEntrega()+ ", estado: "+ l.getEstado()+", direccion de retiro: "+l.getDireccionRetiro()+", direccion de entrega: "+ l.getRetiroEntrega()+", monto total: "+ l.getMontoTotal()+ ", rango horario: " +l.getRangoHorario());
			System.out.println("      Usuario Id: "+l.getUsuario().getId()+ ", nombre: "+l.getUsuario().getNombre()+", apellido: "+l.getUsuario().getApellido()+ ", direccion: "+l.getUsuario().getDireccion()+", email: "+l.getUsuario().getEmail()+", telefono: "+l.getUsuario().getTelefono()+ ", contrase�a: "+l.getUsuario().getPassword()+ ", estado: "+l.getUsuario().getEstado()+", rol: "+l.getUsuario().getRol());
			System.out.println("      Ronda Id: "+l.getRonda().getId()+", fecha de inicio : "+ l.getRonda().getFechaInicio()+", fecha fin: "+l.getRonda().getFechaFin()+ ", fecha de retiro: "+l.getRonda().getFechaRetiro()+", rango horario: "+l.getRonda().getRangoHorario());
			for(ProductoEncargado pe:l.getListaProductos()) { 
		    	System.out.println("      ProductoEncargado id: "+ pe.getId()+ ", cantidad: "+ pe.getCantidad()+", pedido: " + pe.getPedido().getId());
		    	System.out.println("         Producto Id: "+pe.getProducto().getId()+", nombre: "+pe.getProducto().getNombre()+", descripcion: "+pe.getProducto().getDescripcion()+", stock: "+pe.getProducto().getStock()+", estado: "+pe.getProducto().getEstado()+",precio: "+pe.getProducto().getPrecio());
			}
		
		}
	}
	
	public void listarUsuarios(IUsuarioDAO u) {
		System.out.println("Lista de usuarios:");
		List<Usuario> lista12= u.getList();
		for(Usuario l:lista12) {
			System.out.println("-> Id: "+l.getId()+ ", nombre: "+l.getNombre()+", apellido: "+l.getApellido()+ ", direccion: "+l.getDireccion()+", email: "+l.getEmail()+", telefono: "+l.getTelefono()+ ", contrase�a: "+l.getPassword()+ ", estado: "+l.getEstado()+", rol: "+l.getRol());
			for (Pedido p: l.getPedidos()) { 
				System.out.println("    Pedido  Id:"+p.getId()+", fecha:" + p.getFecha()+", fecha entrega: "+p.getFechaEntrega()+", usuario: "+ p.getUsuario().getId()+ ", retiro o entrega: "+p.getRetiroEntrega()+ ", estado: "+ p.getEstado()+", direccion de retiro: "+p.getDireccionRetiro()+", direccion de entrega: "+ p.getRetiroEntrega()+", monto total: "+ p.getMontoTotal()+ ", rango horario: " + p.getRangoHorario()+ "ronda: "+ p.getRonda().getId());
			    for(ProductoEncargado pe:p.getListaProductos()) { 
			    	System.out.println("      ProductoEncargado id: "+ pe.getId()+ ", cantidad: "+ pe.getCantidad()+", producto: "+ pe.getProducto().getId()+", pedido: " + pe.getPedido().getId());
			    }
			}
		}
	}
	public void listarProductos(IProductoDAO producto) {
    	System.out.println("Lista de productos:");
		List<Producto> lista12= producto.getList();
		for(Producto l:lista12) {
//			System.out.print(l.toString());			
			System.out.println("-> Id: "+l.getId()+", nombre: "+l.getNombre()+", descripcion: "+l.getDescripcion()+", stock: "+l.getStock()+", estado: "+l.getEstado()+",precio: "+l.getPrecio());
		    System.out.println("   Productor:"+ " id: "+l.getProductor().getId()+", nombre: "+l.getProductor().getNombre()+ ", descripcion: "+l.getProductor().getDescripcion()+", estado: "+l.getProductor().getEstado());
		    for( RubroDeProducto r:l.getRubros()) {
		    	System.out.println("   Rubro:  id: "+ r.getId()+ ", nombre: "+ r.getNombre()+", estado: "+r.getEstado());
		    }
		}
    }
	public void listarRubros(IRubroDAO rubro) {
    	System.out.println("Lista de rubros:");
		List<RubroDeProducto> lista= rubro.getList();
		for(RubroDeProducto l:lista) {
			System.out.println("-> Id: "+ l.getId()+", nombre: "+ l.getNombre()+ ", estado: " + l.getEstado());
			for(Producto p:l.getProductos()) {
				System.out.println("    Producto  id: "+p.getId()+ ", nombre: "+p.getNombre()+",descricion: "+p.getDescripcion()+", stock: "+ p.getStock()+ ", estado: "+ p.getEstado()+", precio: "+p.getPrecio());
			}
		}
    }
	public void listarProductores(IProductorDAO productor) {
    	System.out.println("Lista de productores:");
		List<Productor> lista= productor.getList();
		for(Productor l:lista) {
			System.out.println("-> Id: "+ l.getId()+ ", nombre: "+ l.getNombre()+ ", descripcion: "+ l.getDescripcion()+ ", estado: "+l.getEstado());
			for(Image m:l.getImagenes()) {
				System.out.println("    Imagen  id: "+m.getId()+ ", nombre: "+m.getNombre()+",imagen: "+m.getImagen());
			}
			for(Producto p: l.getProductos()) {
				System.out.println("    Producto  id: "+p.getId()+ ", nombre: "+p.getNombre()+",descricion: "+p.getDescripcion()+", stock: "+ p.getStock()+ ", estado: "+ p.getEstado()+", precio: "+p.getPrecio());
			}
		}
    }
    public void listarPuntoDeRetiro(IPuntoDeRetiroDAO punto) {
    	System.out.println("Lista de puntos de retiro:");
		List<PuntoDeRetiro> listaPuntoDeRetiro= punto.getList();
		for(PuntoDeRetiro l:listaPuntoDeRetiro) {
			System.out.println(l.toString());
		}
    }
    public void agregarPuntoDeRetiro(PuntoDeRetiro punto,IPuntoDeRetiroDAO p) {
    	p.agregar(punto);
    }
    public void eliminarPuntoDeRetiro(PuntoDeRetiro punto,IPuntoDeRetiroDAO p) {
    	System.out.println("Eliminar punto de retiro");
    	p.eliminar(punto);
    	listarPuntoDeRetiro(p);
    }
    public void editarPuntoDeRetiro(PuntoDeRetiro punto,IPuntoDeRetiroDAO p) {
    	System.out.println("Editar punto de retiro");
    	p.modificar(punto);
    	listarPuntoDeRetiro(p);
    }
    public void listarImagenes(IImageDAO i) {
    	System.out.println("Lista de imagenes:");
		List<Image> lista= i.getList();
		for(Image l:lista) {
			System.out.println(l.toString());
		}
    }
    public void listarRondas(IRondaDAO r) {
    	System.out.println("Lista de rondas:");
		List<Ronda> list= r.getList();
		for(Ronda l:list) {
			System.out.println(l.toString());
		}
    }
    
}
