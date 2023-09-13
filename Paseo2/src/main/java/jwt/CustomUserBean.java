package jwt;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import modelos.Usuario;
import utils.Rol;

public class CustomUserBean implements UserDetails {
		  private static final long serialVersionUID = -4709084843450077569L;  
		  private Long id; 
		  private String email;
		  @JsonIgnore
		  private String password;
		  private GrantedAuthority authority;
		  
		  
		  CustomUserBean(Long id, String email, 
		      String password,GrantedAuthority authority){
		    this.id = id;
		    this.email = email;
		    this.password = password;
		    this.authority = authority;
		  }
		  
		  public static CustomUserBean createInstance(Usuario user) {
			  GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().name());
		        return new CustomUserBean(user.getId(), user.getEmail(), user.getPassword(), authority);
		
		  }
		  
		  public Rol getRol() {
		        return Rol.valueOf(authority.getAuthority());
		    }
		  
		  @Override
		  public Collection<? extends GrantedAuthority> getAuthorities() {
		    return Collections.singleton(authority);
		  }

		  @Override
		  public String getPassword() {
		    return password;
		  }


		  public Long getId() {
		    return id;
		  }

		  public String getEmail() {
		    return email;
		  }

		  @Override
		  public boolean isAccountNonExpired() {
		    return true;
		  }

		  @Override
		  public boolean isAccountNonLocked() {
		    return true;
		  }

		  @Override
		  public boolean isCredentialsNonExpired() {
		    return true;
		  }

		  @Override
		  public boolean isEnabled() {
		    return true;
		  }
		  @Override
		  public boolean equals(Object rhs) {
		    if (rhs instanceof CustomUserBean) {
		      return email.equals(((CustomUserBean) rhs).email);
		    }
		    return false;
		  }

		  @Override
		  public int hashCode() {
		    return email.hashCode();
		  }

		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return email;
		}
		
}
