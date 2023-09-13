package resources;

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
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelos.Pedido;
import modelos.ProductoEncargado;

import java.util.List;

import dao.IProductoEncargadoDAO;

@Path("/productoEncargado")
@Tag(name = "Producto Encargado")
public class ProductoEncargadoResource {
      
	  @Inject
	  IProductoEncargadoDAO p ;
	
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de productos encargados", description = "Obtiene la lista de pedidos")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de productos encargados correcta", content = @Content(schema = @Schema(implementation = ProductoEncargado.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de productos encargados incorrecta")
		   })
	   public Response getProductosEncargados() {
		  List<ProductoEncargado> aux=p.getList();
		   if(!aux.isEmpty()) {
			 return Response.ok(aux).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	  
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obtener producto encargado", description = "Obtiene un producto encargado por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Producto encargado obtenido correctamente", content = @Content(schema = @Schema(implementation = ProductoEncargado.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el producto encargado")
	   })
	   public Response getProductoEncargado(@PathParam("id") Long id){
		   ProductoEncargado producto= p.getById(id);
		   if (producto != null){
			   return Response.ok().entity(producto).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el producto encargado").build();
		   }
	  }
	   
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear producto encargado", description = "Crea un producto encargado con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "Producto encargado creado correctamente", content = @Content(schema = @Schema(implementation = ProductoEncargado.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo crear el producto encargado")
	   })
	   public Response crear(ProductoEncargado producto){
		   if(producto.getId()==null || p.getById(producto.getId())==null ) {
			   p.agregar(producto);
			   return Response.status(Response.Status.CREATED).entity(producto)
					   .header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "1209600")
			   .build();
		   }else {
			   return Response.status(Response.Status.NOT_FOUND).entity("El producto encargado con ese id ya existe").build();
		   }
	   }
	   
	   @PUT
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Confirmar pedido")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "Actualizar producto encargado correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el producto encargado")
	   })
	   public Response actualizarCarrito(ProductoEncargado producto){
		   ProductoEncargado aux=p.getById(producto.getId());
		   if (aux != null){
			   p.modificar(producto);
			   return Response.ok().entity(producto).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el producto").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar carrito", description = "Elimina un producto con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "producto eliminado correctamente", content = @Content(schema = @Schema(implementation = ProductoEncargado.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el producto")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   ProductoEncargado aux = p.getById(id);
		   if (aux != null){
			   p.eliminar(aux);
			   return Response.ok().entity("producto eliminado correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el producto").build();
		   }
	   }
}
