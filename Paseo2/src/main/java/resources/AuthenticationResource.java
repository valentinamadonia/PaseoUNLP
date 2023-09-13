package resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationResource {

	
	
	@PostMapping(value="login")
	public String login() {
		//TODO est metodo tiene que recibir usuario password, chequear que sean validos
		//Usar el token service y devolver un jwtToken
		return "Login from public endpoint";
	}
	
	@PostMapping(value="register")
	public String register() {
		//TODO est metodo tiene que recibir usuario password, chequear que sean validos
		//Usar el token service y devolver un jwtToken
		return "Register from public endpoint";
	}
	
}
