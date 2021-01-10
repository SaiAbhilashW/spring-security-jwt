package io.abhilash.spring.securityjwt.services;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*
Using inbuilt user details service to just create a user and authenticate it
*/

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        return new User("foo", "foo", new ArrayList<>());
    }
}
