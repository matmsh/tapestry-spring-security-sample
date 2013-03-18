package nu.localhost.tapestry5.springsecuritytest.services;

import nu.localhost.tapestry5.springsecurity.services.RequestInvocationDefinition;
import nu.localhost.tapestry5.springsecuritytest.services.internal.MyUserDetailsService;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.services.ServiceOverride;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;


public class TestsecurityModule {

    public static UserDetailsService buildUserDetailsService( @Inject
    PasswordEncoder encoder, @Inject
    SaltSource salt ) {

        return new MyUserDetailsService( encoder, salt );
    }

//    public static void contributeProviderManager(
//        OrderedConfiguration<AuthenticationProvider> configuration,
//        @InjectService( "DaoAuthenticationProvider" )
//        AuthenticationProvider daoAuthenticationProvider ) {
//
//        configuration.add(
//            "daoAuthenticationProvider",
//            daoAuthenticationProvider );
//    }
    
    public static void contributeAuthenticationManager(
            OrderedConfiguration<AuthenticationProvider> configuration,
            @InjectService( "DaoAuthenticationProvider" )
            AuthenticationProvider daoAuthenticationProvider ) {

            configuration.add(
                "daoAuthenticationProvider",
                daoAuthenticationProvider );
        }
    

    public static void contributeFilterSecurityInterceptor(
        Configuration<RequestInvocationDefinition> configuration ) {

        configuration.add( new RequestInvocationDefinition(
            "/ltd.pdf",
            "ROLE_ADMIN", null ) );
    }


    @Contribute(ServiceOverride.class)
    public static void setupApplicationServiceOverrides(MappedConfiguration<Class<?>,Object> configuration)
    {
      configuration.add(PasswordEncoder.class, new ShaPasswordEncoder());
      // Uncomment this line to run in Jboss 7. 
     // configuration.add(ClasspathURLConverter.class, new JBoss7ClasspathURLConverterImpl());
      
    }
    
    
    public static void contributeApplicationDefaults(
        MappedConfiguration<String, String> configuration ) {

        configuration.add( SymbolConstants.SUPPORTED_LOCALES, "de,en" );
        configuration.add( "tapestry.default-cookie-max-age", "31536000" );
        configuration.add( SymbolConstants.PRODUCTION_MODE, "false" );

        configuration.add( "spring-security.failure.url", "/loginpage/failed" );
        configuration.add( "spring-security.accessDenied.url", "/forbidden" );
        configuration.add(
            "spring-security.check.url",
            "/j_spring_security_check" );
        configuration.add( "spring-security.target.url", "/" );
        configuration.add( "spring-security.afterlogout.url", "/" );
        configuration.add( "spring-security.rememberme.key", "REMEMBERMEKEY" );
        configuration.add( "spring-security.loginform.url", "/loginpage" );
        configuration.add( "spring-security.force.ssl.login", "false" );
        configuration.add( "spring-security.anonymous.key", "acegi_anonymous" );
        configuration.add(
            "spring-security.anonymous.attribute",
            "anonymous,ROLE_ANONYMOUS" );
        configuration.add( "spring-security.password.salt", "DEADBEEF" );
        

 
    }

}
