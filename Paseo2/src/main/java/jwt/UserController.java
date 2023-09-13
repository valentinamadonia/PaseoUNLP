package jwt;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/allusers")
	public String displayUsers() {
		return "Display All Users";
	}
	
	@GetMapping("/displayuser")
	@PreAuthorize("hasRole('USUARIO') or hasRole('ADMINISTRADOR')")
	public String displayToUser() {
		return "Display to both user and admin";
	}
	
	@GetMapping("/displayadmin")
	@PreAuthorize("hasRole('ADMINISTRADOR')")
	public String displayToAdmin() {
		return "Display only to admin";
	}
}