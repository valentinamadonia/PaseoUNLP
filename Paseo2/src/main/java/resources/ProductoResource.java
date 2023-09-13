package resources;

import java.util.List;

import dao.IImageDAO;
import dao.IProductoDAO;
import dao.IProductorDAO;
import dao.IRubroDAO;
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
import modelos.Image;
import modelos.Pedido;
import modelos.Producto;
import modelos.RubroDeProducto;

@Path("/producto")
@Tag(name = "Producto")
public class ProductoResource {
	@Inject
	private IProductoDAO p;
	
	@Inject
	private IProductorDAO productor;
	
	@Inject
	private IRubroDAO rubro;
	
	@Inject
	private IImageDAO image;
	
	 
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de productos", description = "Obtiene la lista de productos")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de productos correcta", content = @Content(schema = @Schema(implementation = Producto.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de productos incorrecta")
		   })
	   public Response getProductos() {
		  List<Producto> aux=p.getList();
		   if(!aux.isEmpty()) {
			 return Response.ok(aux).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	   
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obtener producto", description = "Obtiene un producto por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "producto obtenido correctamente", content = @Content(schema = @Schema(implementation = Producto.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el producto")
	   })
	   public Response getProducto(@Parameter() @PathParam("id") Long id){
		   Producto producto= p.getById(id);
		   if (producto != null){
			   return Response.ok().entity(producto).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el producto").build();
		   }
	  }
      
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear producto", description = "Crea un producto con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "Producto creado correctamente", content = @Content(schema = @Schema(implementation = Pedido.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo crear el producto")
	   })
	   public Response crear(Producto producto){
		   if(producto.getId()==null || p.getById(producto.getId())==null ) {
			   //agrego la imagen a la bd
			   List<Image> imagenes= producto.getImagenes();
			   if(imagenes.isEmpty()) {
				   System.out.println("NOLLEGAIMAGENES");
			   }
			   for(int i=0;i<imagenes.size();i++) {
				   image.agregar(producto.getImagenes().get(i));
				   System.out.println("IMAGEN:"+producto.getImagenes().get(i));
			   }
			   //agrego el producto
			   p.agregar(producto);
			   System.out.println("PRODUCTO:"+producto.getId());
			   //agrego el producto al productor
			   System.out.println("PRODUCTOR:"+producto.getProductor().getId());
			   List<Producto> productos= producto.getProductor().getProductos();
			   productos.add(producto);
			   producto.getProductor().setProductos(productos);
			   productor.modificar(producto.getProductor());
			   //agrego el producto a los rubros
			   List<RubroDeProducto> rubros=producto.getRubros();
			   for(int i=0; i<rubros.size();i++){
				   rubros.get(i).getProductos().add(producto);
				   rubro.modificar(rubros.get(i));
			   }
			   return Response.status(Response.Status.CREATED).entity(producto)
					   .header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "1209600")
			   .build();
		   }else {
			   return Response.status(Response.Status.NOT_FOUND).entity("El producto con ese id ya existe").build();
		   }
	   } 
	   
	   @PUT
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Modificar producto")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "Modificar producto correctamente", content = @Content(schema = @Schema(implementation = Producto.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el producto")
	   })
	   public Response modificarProducto(Producto producto){
		   Producto aux=p.getById(producto.getId());
		   if (aux != null){
			   for(int i=0;i<producto.getImagenes().size();i++){
				   System.out.println("IMAGEN: "+ producto.getImagenes().get(i).getImagen());
				   image.modificar(producto.getImagenes().get(i));
			   }
			   p.modificar(producto);
			   return Response.ok().entity(producto).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el producto").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar producto", description = "Elimina un producto con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "producto eliminado correctamente", content = @Content(schema = @Schema(implementation = Producto.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el producto")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   Producto aux = p.getById(id);
		   if (aux != null){
			   p.eliminar(aux);
			   return Response.ok().entity("producto eliminado correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el producto").build();
		   }
	   }
}
