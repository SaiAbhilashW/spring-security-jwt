package io.abhilash.spring.securityjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.abhilash.spring.securityjwt.filters.JWTRequestFilter;
import io.abhilash.spring.securityjwt.services.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTRequestFilter jwtRequestFilter;
    
    //authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(myUserDetailsService);
    }

    //authorization
    // permit all requests hitting the endpoint /authenticate and for all others user needs to be authenticated
    // makes the session management stateless so that nothing is stored in session since we are using JWT
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/authenticate").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // TODO Auto-generated method stub
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
