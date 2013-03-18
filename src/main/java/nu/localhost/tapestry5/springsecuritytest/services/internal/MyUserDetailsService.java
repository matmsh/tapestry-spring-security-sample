package nu.localhost.tapestry5.springsecuritytest.services.internal;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SuppressWarnings("deprecation")
public class MyUserDetailsService implements UserDetailsService {

    private MyUserDetailsImpl _milli;
    private MyUserDetailsImpl _mona;

    public MyUserDetailsService( PasswordEncoder encoder, SaltSource salt ) {
    	
    	// Can not replace GrantedAuthorityImpl by  SimpleGrantedAuthority
    	// because IfRole.java uses  GrantedAuthorityImpl.
    	// If GrantedAuthorityImpl is replaced by  SimpleGrantedAuthority,
    	// it also needs to be done in IfRole.java. 
        GrantedAuthority user = new GrantedAuthorityImpl( "ROLE_DEFAULT" );
         
        _milli = new MyUserDetailsImpl( "milli" );
        _milli.addAuthority( user );
        _milli.addAuthority( new GrantedAuthorityImpl( "ROLE_ADMIN" ) );
         
        _milli.setPassword( encoder.encodePassword( "milli", salt.getSalt( _milli ) ) );

        _mona = new MyUserDetailsImpl( "mona");
        _mona.addAuthority( user );
        _mona.addAuthority( new GrantedAuthorityImpl( "ROLE_TELLER" ) );
        
        _mona.setPassword( encoder.encodePassword( "mona", salt.getSalt( _mona ) ) );
    }

    public UserDetails loadUserByUsername( String name )
        throws UsernameNotFoundException {

        if ( "milli".equals( name ) ) return _milli;
        if ( "mona".equals( name ) ) return _mona;
        return null;
    }

}
