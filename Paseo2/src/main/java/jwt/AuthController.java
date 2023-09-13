package jwt;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.IUsuarioDAO;
import modelos.Usuario;
import utils.Rol;
@RestController
@RequestMapping("/auth")
public class AuthController {
	 
	 @Autowired
	  IUsuarioDAO userRepository;
	  @Autowired
	  PasswordEncoder encoder;
	  @Autowired
	  AuthenticationManager authenticationManager;
	  @Autowired
	  JwtTokenUtil jwtTokenUtil;
	  
	  @PostMapping("/login")
	  public ResponseEntity<?> userLogin(@Valid @RequestBody Usuario user) {
	    //System.out.println("AuthController -- userLogin");
	    Authentication authentication = authenticationManager.authenticate(
	          new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
	    
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String token = jwtTokenUtil.generateJwtToken(authentication);
	    CustomUserBean userBean = (CustomUserBean) authentication.getPrincipal();    
	   
	    Rol rol = userBean.getRol();
	    AuthResponse authResponse = new AuthResponse();
	    authResponse.setToken(token);
	    authResponse.setRol(rol);
	    return ResponseEntity.ok(authResponse);
	  }
	  
	  @PostMapping("/signup")
	  public ResponseEntity<?> userSignup(@Valid @RequestBody SignupRequest signupRequest) {
	    if(userRepository.getUserByEmail(signupRequest.getEmail())!=null){
	      return ResponseEntity.badRequest().body("Username is already taken");
	    }
	    if(userRepository.getUserByEmail(signupRequest.getEmail())==null){
	      return ResponseEntity.badRequest().body("Email is already taken");
	    }
	    Usuario user = new Usuario();
	    user.setNombre(signupRequest.getUserName());
	    user.setEmail(signupRequest.getEmail());
	    user.setPassword(encoder.encode(signupRequest.getPassword()));
	    //System.out.println("Encoded password--- " + user.getPassword());
	    user.setRol(Rol.USUARIO);
	    userRepository.agregar(user);
	    return ResponseEntity.ok("User signed up successfully");
	  }
}
