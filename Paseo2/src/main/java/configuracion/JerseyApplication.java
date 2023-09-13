package configuracion;

import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.ws.rs.ApplicationPath;
import resources.CarritoResource;
import resources.ProductoEncargadoResource;
import resources.ProductoResource;
import resources.RondaResource;
import resources.UsuariosResource;
import io.swagger.v3.oas.annotations.servers.*;
import resources.RubroDeProductoResource;
import resources.PuntoDeRetiroResource;
import resources.ProductorResource;

@ApplicationPath("rest")
@OpenAPIDefinition(info = @Info(title = "API Paseo", version = "1.0.0"),servers = @Server(url = "http://localhost:8080/Paseo2/"))

public class JerseyApplication extends ResourceConfig {
	public JerseyApplication() {
		packages(true, "resources");
		OpenApiResource openApiResource = new OpenApiResource();
        register(openApiResource);
        register(UsuariosResource.class);
        register(CarritoResource.class);
        register(ProductoEncargadoResource.class);
        register(ProductoResource.class);
        register(ProductorResource.class);
        register(RondaResource.class);
        register(PuntoDeRetiroResource.class);
        register(RubroDeProductoResource.class);
        register(RequestScopeBinder.class);

	}
	
}