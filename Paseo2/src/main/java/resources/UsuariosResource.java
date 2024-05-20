package resources;
import modelos.Usuario;
import dao.IUsuarioDAO;
import dao.UsuarioDAO;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.glassfish.jersey.process.internal.RequestScoped;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
@RequestScoped
@Path("/usuarios")
@Tag(name = "Usuarios")
public class UsuariosResource {

	//UsuarioDAO udao = new UsuarioDAO();
	@Inject
	IUsuarioDAO udao;

	/**
	 * Esto solo descomentalo si no funcinoa el @Inject
	 * */
//	public UsuariosResource() {
//		ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
//		ServiceLocatorUtilities.bind(locator, new RequestScopeBinder());
//		this.udao = (IUsuarioDAO) locator.getService(IUsuarioDAO.class);
//	}
//	
//	private final IUsuarioDAO udao;
//	    
//	@Inject
//	public UsuariosResource(IUsuarioDAO udao) {
//	    this.udao = udao;
//    }

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Operation(summary = "Lista de usuarios", description = "Obtiene la lista de usuarios y administradores")
   @ApiResponses(value = {
	         @ApiResponse(responseCode = "200", description = "Lista de usuarios correcta", content = @Content(schema = @Schema(implementation = Usuario.class))),
	         @ApiResponse(responseCode = "404", description = "Lista de usuarios incorrecta")
	   })
   public Response getUsuarios() {
	  List<Usuario> users=udao.getList();
	   if(!users.isEmpty()) {
		 return Response.ok(users).build();
	   }else {
		  return Response.status(Response.Status.NOT_FOUND).build();
	   }
   }
   
   @GET
   @Path("{id}")
   @Produces(MediaType.APPLICATION_JSON)
   @Operation(summary = "Obetener usuario", description = "Obtiene un usuario por su id")
   @ApiResponses(value = {
	         @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente", content = @Content(schema = @Schema(implementation = Usuario.class))),
	         @ApiResponse(responseCode = "404", description = "No se pudo obtener el usuario")
   })
   public Response getUser(@Parameter(description = "id del usuario, utilizar 3 para la prueba") @PathParam("id") Long id){
	   Usuario user= udao.getUser(id);
	   if (user != null){
		   return Response.ok().entity(user).build();
	   } else {
		   return Response.status(Response.Status.NOT_FOUND).entity("No se encontro el usuario").build();
	   }
  }
   
   @GET
   @Path("email/{email}")
   @Produces(MediaType.APPLICATION_JSON)
   @Operation(summary = "Obtener usuario", description = "Obtiene un usuario por su email")
   @ApiResponses(value = {
	         @ApiResponse(responseCode = "200", description = "Usuario obtenido correctamente", content = @Content(schema = @Schema(implementation = Usuario.class))),
	         @ApiResponse(responseCode = "409", description = "No se pudo obtener el usuario")
   })
   public Response getUserEmail( @PathParam("email") String email){
	   Usuario user= udao.getUserByEmail(email);
	   if (user != null){
		   return Response.ok().entity(user).build();
	   } else {
		   return Response.status(Response.Status.CONFLICT).entity("No se encontro el usuario").build();
	   }
  }
   
   
   @POST 
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @Operation(summary = "Crear usuario", description = "Crea un usuario con los parametros")
   @ApiResponses(value = {
	         @ApiResponse(responseCode = "201", description = "Usuario creado correctamente", content = @Content(schema = @Schema(implementation = Usuario.class))),
	         @ApiResponse(responseCode = "409", description = "Ya existe un usuario con el email")
   })
   public Response crear(Usuario usuario){
	   if(udao.getUserByEmail(usuario.getEmail()) == null) {
		   udao.agregar(usuario);
		   return Response.status(Response.Status.CREATED).entity(usuario)
				   .header("Access-Control-Allow-Origin", "*")
		            .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
		            .header("Access-Control-Allow-Credentials", "true")
		            .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
		            .header("Access-Control-Max-Age", "1209600")
		   .build();
	   }else {
			   return Response.status(Response.Status.CONFLICT).entity("El email ya se encuentra en uso").build();
		  
	   }
   }
   
   @PUT
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_JSON)
   @Operation(summary = "Editar usuario", description = "Edita un usuario con los parametros")
   @ApiResponses(value = {
	         @ApiResponse(responseCode = "202", description = "Usuario editado correctamente", content = @Content(schema = @Schema(implementation = Usuario.class))),
	         @ApiResponse(responseCode = "404", description = "No se pudo obtener el usuario")
   })
   public Response editar(@Parameter(description = "Utilizar el id 3 para probar") Usuario usuario){
	   Usuario aux = udao.getUser(usuario.getId());
	   if (aux != null){
		   //actualizar(aux,usuario);
		   udao.modificar(usuario);
		   return Response.ok().entity(usuario).build();
	   } else {
		   return Response.status(Response.Status.NOT_FOUND).entity("No existe el usuario").build();
	   }
   }
   
   @DELETE //En realidad seria un put porque es eliminado logico
   @Path("{id}")
   @Produces(MediaType.TEXT_PLAIN)
   @Operation(summary = "Eliminar usuario", description = "Elimina un usuario con el parametro id")
   @ApiResponses(value = {
	         @ApiResponse(responseCode = "202", description = "Usuario eliminado correctamente", content = @Content(schema = @Schema(implementation = Usuario.class))),
	         @ApiResponse(responseCode = "404", description = "No se pudo obtener el usuario")
   })
   public Response borrar(@PathParam("id") Long id){
	   Usuario aux = udao.getUser(id);
	   if (aux != null){
		   udao.eliminar(aux);
		   return Response.ok().entity("Usuario eliminado correctamente").build();
	   } else {
		   return Response.status(Response.Status.NOT_FOUND).entity("No existe el usuario").build();
	   }
   }
   
  public boolean existe(Usuario u) {
	  if(udao.getUser(u.getId())!=null) {
		  return true;
	  }else return false;
  }
  
  public void actualizar(Usuario aux,Usuario u) {
	  if(u.getNombre()!=null) {
		  aux.setNombre(u.getNombre());
	  }
	  if(u.getApellido()!=null) {
		  aux.setApellido(u.getApellido());
	  }
	  if(u.getEmail()!=null) {
		  aux.setEmail(u.getEmail());
	  }
	  if(u.getDireccion()!=null) {
		  aux.setDireccion(u.getDireccion());
	  }
	  if(u.getRol()!=null) {
		  aux.setRol(u.getRol());
	  }
	  if(u.getTelefono()!=0) {
		  aux.setTelefono(u.getTelefono());
	  }
	  if(u.getEstado()!=null) {
		  aux.setEstado(u.getEstado());
	  }
	  if(u.getPedidos()!=null) {
		  aux.setPedidos(u.getPedidos());
	  }
  }
}
