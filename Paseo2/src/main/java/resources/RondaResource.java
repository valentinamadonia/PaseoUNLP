package resources;

import java.util.List;

import org.glassfish.jersey.process.internal.RequestScoped;
import dao.IRondaDAO;
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
import modelos.Ronda;

@RequestScoped
@Path("/ronda")
@Tag(name = "Ronda")
public class RondaResource {
	@Inject
	IRondaDAO r;
	
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de rondas", description = "Obtiene la lista de rondas")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de rondas correcta", content = @Content(schema = @Schema(implementation = Ronda.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de rondas incorrecta")
		   })
	   public Response getRondas() {
		  List<Ronda> rondas=r.getList();
		   if(!rondas.isEmpty()) {
			 return Response.ok(rondas).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	   
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obtener ronda", description = "Obtiene una ronda por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "ronda obtenida correctamente", content = @Content(schema = @Schema(implementation = Ronda.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el ronda")
	   })
	   public Response getRonda(@PathParam("id") Long id){
		   Ronda ronda= r.getById(id);
		   if (ronda != null){
			   return Response.ok().entity(ronda).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el ronda").build();
		   }
	   }
	   
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear ronda", description = "Crea una ronda con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "ronda creada correctamente", content = @Content(schema = @Schema(implementation = Ronda.class))),
		         @ApiResponse(responseCode = "409", description = "Ya existe una ronda con el id")
	   })
	   public Response crear(Ronda ronda){
			   r.agregar(ronda);
			   return Response.status(Response.Status.CREATED).entity(ronda)
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
	   @Operation(summary = "Editar ronda", description = "Edita una ronda con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "ronda editada correctamente", content = @Content(schema = @Schema(implementation = Ronda.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener la ronda")
	   })
	   public Response editar(Ronda ronda){
		   Ronda aux = r.getById(ronda.getId());
		   if (aux != null){
			   r.modificar(ronda);
			   return Response.ok().entity(ronda).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe la ronda").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar ronda", description = "Elimina una ronda con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "ronda eliminada correctamente", content = @Content(schema = @Schema(implementation = Ronda.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo eliminar la ronda")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   Ronda aux = r.getById(id);
		   if (aux != null){
			   r.eliminar(aux);
			   return Response.ok().entity("ronda eliminada correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe la ronda").build();
		   }
	   }
}
