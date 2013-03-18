package nu.localhost.tapestry5.springsecuritytest.services.internal;

import java.util.Collection;
import java.util.List;

import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -492528595540877572L;

	private List<GrantedAuthority> _authorities = CollectionFactory.newList();
	
	private String _password;
	private String _username;
	
	public MyUserDetailsImpl( String username ) {
	
		_username = username;
	}
	
	public Collection<GrantedAuthority> getAuthorities() {
		return _authorities;
	}

	public void addAuthority( GrantedAuthority authority ) {
		
		_authorities.add( authority );
	}
	
	public String getPassword() {

		return _password;
	}
	
	public void setPassword( String password ) {
		
		_password = password;
	}

	public String getUsername() {

		return _username;
	}
	
	public void setUsername( String username ) {
	
		_username = username;
	}

	public boolean isAccountNonExpired() {

		return true;
	}

	public boolean isAccountNonLocked() {

		return true;
	}

	public boolean isCredentialsNonExpired() {

		return true;
	}

	public boolean isEnabled() {

		return true;
	}
}
