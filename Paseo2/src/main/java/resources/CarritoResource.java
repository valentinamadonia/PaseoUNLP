package resources;

import java.util.List;
import java.util.ArrayList;
import dao.IPedidoDAO;
import dao.IProductoEncargadoDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelos.Pedido;
import modelos.ProductoEncargado;
import modelos.Usuario;
import utils.Estado;
import utils.EstadoPedido;
import utils.RetiroEntrega;

@Path("/carrito")
@Tag(name = "Carrito")
public class CarritoResource {
	   @Inject
	   private IPedidoDAO p;
	   
	   @Inject
	   private IProductoEncargadoDAO pedao;
	   
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de pedidos", description = "Obtiene la lista de pedidos")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de pedidos correcta", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de pedidos incorrecta")
		   })
	   public Response getCarritos() {
		  List<Pedido> aux=p.getList();
		   if(!aux.isEmpty()) {
			 return Response.ok(aux).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	   
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obtener carrito", description = "Obtiene un carrito por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Carrito obtenido correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el carrito")
	   })
	   public Response getCarrito(@Parameter() @PathParam("id") Long id){
		   Pedido pedido= p.getPedido(id);
		   if (pedido != null){
			   return Response.ok().entity(pedido).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el carrito").build();
		   }
	  }
	   
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear pedido", description = "Crea un pedido con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "Pedido creado correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo crear el pedido")
	   })
	   public Response crear(Pedido pedido){
		   if(pedido.getId()==null || p.getPedido(pedido.getId())==null ) {
			   List<ProductoEncargado> pe;
			   pe=pedido.getListaProductos();
			   pedido.setListaProductos(null);
			   p.agregar(pedido);
			   for(int i=0;i<pe.size();i++) {
				   pe.get(i).setPedido(pedido);
				   pedao.agregar(pe.get(i));
			   }
			   pedido.setListaProductos(pe);
			   p.modificar(pedido);
			   return Response.status(Response.Status.CREATED).entity(pedido)
					   .header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "1209600")
			   .build();
		   }else {
			   return Response.status(Response.Status.NOT_FOUND).entity("El pedido con ese id ya existe").build();
		   }
	   } 
	   
	   @PUT
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Confirmar pedido")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "Confirmar pedido correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el carrito")
	   })
	   public Response confirmarCarrito(@Parameter(description = "Utilizar el id 1,punto de retiro 1")Pedido ped){
		   Pedido aux=p.getPedido(ped.getId());
		   if (aux != null){
			   actualizar(aux,ped);
			   p.modificar(aux);
			   return Response.ok().entity(aux).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el carrito").build();
		   }
	   }
	   
	   @PUT
	   @Path("/modificar")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Modificar pedido")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "Pedido modificado correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo modificar el carrito")
	   })
	   public Response modificarCarrito(Pedido ped){
		   Pedido aux=p.getPedido(ped.getId());
		   if (aux != null){
//			   List<ProductoEncargado> pe=ped.getListaProductos();
//			   List<ProductoEncargado> peNueva= new ArrayList<ProductoEncargado>();
//			   
//			   System.out.println("Llegaron los productos a modificar:(los que tienen null es porque son nuevos, los que tienen id los modifico por si modifico la cantidad)");
//			   for(int i=0;i<pe.size();i++) {
//				   System.out.println("-"+pe.get(i).getId()+","+pe.get(i).getCantidad()+","+pe.get(i).getProducto().getNombre());
//			   }
//			   System.out.println("---------------------------------------");
//			   for(int i=0;i<pe.size();i++) {
//				   if(pe.get(i).getId()!=null) { //si esta creado, lo modifico
//					   pedao.modificar(pe.get(i)); 
//					   peNueva.add(pe.get(i));
//				   }//si aca ponia un else no andaba(no se porque)
//				   if(pe.get(i).getId() == null) { //si no esta creado el productoEncargado
//					   pe.get(i).setPedido(ped);
//					   pedao.agregar(pe.get(i));
//					   peNueva.add(pe.get(i)); 
//				   }
//			   }
//			   System.out.println("PEDIDO MODIFICADO:(ya tienen todos id)");
//			   for(int i=0;i<peNueva.size();i++) {
//				   System.out.println("-"+peNueva.get(i).getId()+","+peNueva.get(i).getCantidad()+","+peNueva.get(i).getProducto().getNombre());
//			   }
//			   ped.setListaProductos(peNueva);  
//			   System.out.println("pedido antes de la base de datos:");
//			   for(int i=0;i<ped.getListaProductos().size();i++) {
//				   System.out.println("-"+ped.getListaProductos().get(i).getId()+","+ped.getListaProductos().get(i).getCantidad()+","+ped.getListaProductos().get(i).getProducto().getNombre());
//			   }
//			   System.out.println("ID  PEDIDO: "+ ped.getId());
//			   p.modificar(ped);
//			   System.out.println("pedido despues de la base de datos:");
//			   for(int i=0;i<ped.getListaProductos().size();i++) {
//				   System.out.println("-"+ped.getListaProductos().get(i).getId()+","+ped.getListaProductos().get(i).getCantidad()+","+ped.getListaProductos().get(i).getProducto().getNombre());
//			   }
			   
			   List<ProductoEncargado> listaAnterior=aux.getListaProductos();
			   List<ProductoEncargado> listaNueva= ped.getListaProductos();
			   //VER SI SE ELIMINO ALGUN PRODUCTO Y SI HAY REPETIDO SETEO CANTIDAD
			   for(int i=0;i<listaAnterior.size();i++) {
				   boolean encontro=false;
				   for(int o=0;o<listaNueva.size();o++) {
					   if(listaAnterior.get(i).getId()==listaNueva.get(o).getId()){ //si existe
						   listaAnterior.get(i).setCantidad(listaNueva.get(o).getCantidad());
						   pedao.modificar(listaNueva.get(o));
						   encontro=true;
					   }
				   }
				   if(!encontro) {
					   listaAnterior.get(i).setEstado(Estado.SUSPENDIDO);
					   pedao.modificar(listaAnterior.get(i));
					   listaAnterior.remove(i);
				   }
			   }
			   //SI HAY NUEVOS LOS AGREGO
			   for(int o=0;o<listaNueva.size();o++) {
				   if(listaNueva.get(o).getId()==null) {
					   listaNueva.get(o).setPedido(aux);
					   pedao.agregar(listaNueva.get(o));
					   listaAnterior.add(listaNueva.get(o));
				   }
			   }
			   aux.setMontoTotal(ped.getMontoTotal());
			   aux.setDireccionEntrega(ped.getDireccionEntrega());
			   aux.setDireccionRetiro(ped.getDireccionRetiro());
			   aux.setRangoHorario(ped.getRangoHorario());
			   aux.setRetiroEntrega(ped.getRetiroEntrega());
			   aux.setListaProductos(listaAnterior);
			   for(int i=0;i<aux.getListaProductos().size();i++) {
				   System.out.println("-"+aux.getListaProductos().get(i).getId()+","+aux.getListaProductos().get(i).getCantidad()+","+aux.getListaProductos().get(i).getProducto().getNombre());
			   }
			   p.modificar(aux);
			   return Response.ok().entity(ped).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se pudo modificar el carrito").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar carrito", description = "Elimina un carrito con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "carrito eliminado correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el carrito")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   Pedido aux = p.getPedido(id);
		   if (aux != null){
			   p.eliminar(aux);
			   return Response.ok().entity("carrito eliminado correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el carrito").build();
		   }
	   }
	   
	   public void actualizar(Pedido aux, Pedido ped) {
		   aux.setEstado(EstadoPedido.CONFIRMADO); //lo cambia a confirmado independientemente de si lo ingresa manualmente
		   if(ped.getDireccionRetiro()!=null) {
			   aux.setDireccionRetiro(ped.getDireccionRetiro());
		   }
		   if(ped.getDireccionEntrega()!=null) {
			   aux.setDireccionEntrega(ped.getDireccionEntrega());
		   }
		   if(ped.getFecha()!=null) {
			   aux.setFecha(ped.getFecha());
		   }
		   if(ped.getFechaEntrega()!=null) {
			   aux.setFechaEntrega(ped.getFechaEntrega());
		   }
		   if(ped.getRetiroEntrega()!=null) {
			   aux.setRetiroEntrega(ped.getRetiroEntrega());
			   if(aux.getRetiroEntrega().equals(RetiroEntrega.ENTREGA)) {
				    aux.setDireccionRetiro(null);
			   }else if(aux.getRetiroEntrega().equals(RetiroEntrega.RETIRO)) {
				        aux.setDireccionEntrega(null);
				        aux.setRangoHorario(null);
			   }
		   }
		   if(ped.getEstado()!=null) { 
			   aux.setEstado(ped.getEstado());
		   }
		   if(ped.getMontoTotal()!=null) {
			   aux.setMontoTotal(ped.getMontoTotal());
		   }
		   if(ped.getRangoHorario()!=null) {
			   aux.setRangoHorario(ped.getRangoHorario());
		   }
		   if(ped.getListaProductos()!=null) {
			   aux.setListaProductos(ped.getListaProductos());
		   }
		   if(ped.getRonda()!=null) {
			   aux.setRonda(ped.getRonda());
		   }
	   }
}
