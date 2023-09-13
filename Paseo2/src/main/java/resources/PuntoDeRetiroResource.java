package resources;

import java.util.List;

import dao.IPuntoDeRetiroDAO;
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
import modelos.PuntoDeRetiro;
import modelos.Ronda;

@Path("/puntoDeRetiro")
@Tag(name = "Punto de retiro")
public class PuntoDeRetiroResource {
	
	   @Inject
       IPuntoDeRetiroDAO p;
	   
	   @GET
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Lista de puntos de retiros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "Lista de  puntos de retiros correcta", content = @Content(schema = @Schema(implementation = PuntoDeRetiro.class))),
		         @ApiResponse(responseCode = "404", description = "Lista de  puntos de retiros incorrecta")
		   })
	   public Response getPuntoDeRetiros() {
		  List<PuntoDeRetiro> aux=p.getList();
		   if(!aux.isEmpty()) {
			 return Response.ok(aux).build();
		   }else {
			  return Response.status(Response.Status.NOT_FOUND).build();
		   }
	   }
	   
	   @GET
	   @Path("{id}")
	   @Produces(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Obtener punto de retiro", description = "Obtiene un punto de retiro por su id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "200", description = "punto de retiro obtenido correctamente", content = @Content(schema = @Schema(implementation = PuntoDeRetiro.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el punto de retiro")
	   })
	   public Response getPuntoDeRetiro(@PathParam("id") Long id){
		   PuntoDeRetiro punto= p.getById(id);
		   if (punto != null){
			   return Response.ok().entity(punto).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el punto de retiro").build();
		   }
	   }
	   
	   @POST 
	   @Produces(MediaType.APPLICATION_JSON)
	   @Consumes(MediaType.APPLICATION_JSON)
	   @Operation(summary = "Crear punto de retiro", description = "Crea un punto de retiro con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "201", description = "punto de retiro creado correctamente", content = @Content(schema = @Schema(implementation = PuntoDeRetiro.class))),
		         @ApiResponse(responseCode = "409", description = "Ya existe un punto de retiro con el id")
	   })
	   public Response crear(PuntoDeRetiro punto){
			   p.agregar(punto);
			   return Response.status(Response.Status.CREATED).entity(punto)
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
	   @Operation(summary = "Editar punto de retiro", description = "Edita un punto de retiro con los parametros")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "punto de retiro editado correctamente", content = @Content(schema = @Schema(implementation = PuntoDeRetiro.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo obtener el punto de retiro")
	   })
	   public Response editar(PuntoDeRetiro punto){
		   PuntoDeRetiro aux = p.getById(punto.getId());
		   if (aux != null){
			   p.modificar(punto);
			   return Response.ok().entity(punto).build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el punto de retiro").build();
		   }
	   }
	   
	   @DELETE 
	   @Path("{id}")
	   @Produces(MediaType.TEXT_PLAIN)
	   @Operation(summary = "Eliminar punto de retiro", description = "Elimina un punto de retiro con el parametro id")
	   @ApiResponses(value = {
		         @ApiResponse(responseCode = "202", description = "punto de retiro eliminado correctamente", content = @Content(schema = @Schema(implementation = PuntoDeRetiro.class))),
		         @ApiResponse(responseCode = "404", description = "No se pudo eliminar el punto de retiro")
	   })
	   public Response borrar(@PathParam("id") Long id){
		   PuntoDeRetiro aux = p.getById(id);
		   if (aux != null){
			   p.eliminar(aux);
			   return Response.ok().entity("punto de retiro eliminado correctamente").build();
		   } else {
			   return Response.status(Response.Status.NOT_FOUND).entity("No existe el punto de retiro").build();
		   }
	   }
}
