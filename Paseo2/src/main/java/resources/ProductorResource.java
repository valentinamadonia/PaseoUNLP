package resources;

import java.util.List;

import org.glassfish.jersey.process.internal.RequestScoped;

import dao.IProductorDAO;
import io.swagger.v3.oas.annotations.Operation;
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
import modelos.Productor;

@RequestScoped
@Path("/productor")
@Tag(name = "Productor")
public class ProductorResource {
	
	@Inject
	IProductorDAO p;
	
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de productores", description = "Obtiene la lista de productores")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de productores correcta", content = @Content(schema = @Schema(implementation = Productor.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de productores incorrecta")
		   })
	   public Response getProductores() {
		  List<Productor> productores=p.getList();
		   if(!productores.isEmpty()) {
			 return Response.ok(productores).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	   
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obetener productor", description = "Obtiene un productor por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "productor obtenido correctamente", content = @Content(schema = @Schema(implementation = Productor.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el productor")
	   })
	   public Response getProductor(@PathParam("id") Long id){
		   Productor r= p.getById(id);
		   if (r != null){
			   return Response.ok().entity(r).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el productor").build();
		   }
	   }
	   
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear productor", description = "Crea un productor con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "productor creado correctamente", content = @Content(schema = @Schema(implementation = Productor.class))),
		         @ApiResponse(responseCode = "409", description = "Ya existe un productor con el id")
	   })
	   public Response crear(Productor r){
			   p.agregar(r);
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
	   @Operation(summary = "Editar productor", description = "Edita un productor con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "productor editado correctamente", content = @Content(schema = @Schema(implementation = Productor.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el productor")
	   })
	   public Response editar(Productor r){
		   Productor aux = p.getById(r.getId());
		   if (aux != null){
			   p.modificar(r);
			   return Response.ok().entity(r).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el productor").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar productor", description = "Elimina un productor con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "productor eliminado correctamente", content = @Content(schema = @Schema(implementation = Productor.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo eliminar el productor")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   Productor aux = p.getById(id);
		   if (aux != null){
			   p.eliminar(aux);
			   return Response.ok().entity("productor eliminado correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el productor").build();
		   }
	   }
}
