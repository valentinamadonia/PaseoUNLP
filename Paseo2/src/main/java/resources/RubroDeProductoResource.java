package resources;

import java.util.List;

import org.glassfish.jersey.process.internal.RequestScoped;

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
import modelos.RubroDeProducto;

@RequestScoped
@Path("/rubrodeproducto")
@Tag(name = "Rubro de producto")
public class RubroDeProductoResource {

	@Inject
	IRubroDAO rubro;
	
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de rubros de productos", description = "Obtiene la lista de rubros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de rubros correcta", content = @Content(schema = @Schema(implementation = RubroDeProducto.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de rubros incorrecta")
		   })
	   public Response getRubros() {
		  List<RubroDeProducto> rubros=rubro.getList();
		   if(!rubros.isEmpty()) {
			 return Response.ok(rubros).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	   
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obetener rubro", description = "Obtiene un rubro por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Rubro obtenido correctamente", content = @Content(schema = @Schema(implementation = RubroDeProducto.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el rubro")
	   })
	   public Response getRubro(@PathParam("id") Long id){
		   RubroDeProducto r= rubro.getById(id);
		   if (r != null){
			   return Response.ok().entity(r).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el rubro").build();
		   }
	   }
	   
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear rubro", description = "Crea un rubro con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "Rubro creado correctamente", content = @Content(schema = @Schema(implementation = RubroDeProducto.class))),
		         @ApiResponse(responseCode = "409", description = "Ya existe un rubro con el id")
	   })
	   public Response crear(RubroDeProducto r){
			   rubro.agregar(r);
			   return Response.status(Response.Status.CREATED).entity(r)
					   .header("Access-Control-Allow-Origin", "*")
			            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
			            .header("Access-Control-Allow-Credentials", "true")
			            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
			            .header("Access-Control-Max-Age", "1209600")
			   .build();
		  
	   }
	   
	   @PUT
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Editar rubro", description = "Edita un rubro con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "Rubro editado correctamente", content = @Content(schema = @Schema(implementation = RubroDeProducto.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el rubro")
	   })
	   public Response editar(RubroDeProducto r){
		   RubroDeProducto aux = rubro.getById(r.getId());
		   if (aux != null){
			   rubro.modificar(r);
			   return Response.ok().entity(r).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el rubro").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar rubro", description = "Elimina un rubro con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "Rubro eliminado correctamente", content = @Content(schema = @Schema(implementation = RubroDeProducto.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el rubro")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   RubroDeProducto aux = rubro.getById(id);
		   if (aux != null){
			   rubro.eliminar(aux);
			   return Response.ok().entity("Rubro eliminado correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el rubro").build();
		   }
	   }
	   
	
}
