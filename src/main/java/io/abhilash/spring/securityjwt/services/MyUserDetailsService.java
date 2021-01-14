package io.abhilash.spring.securityjwt.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*
Using inbuilt user details service to just create a user and authenticate it
*/

import io.abhilash.spring.securityjwt.models.MyUserDetails;
import io.abhilash.spring.securityjwt.models.User;
import io.abhilash.spring.securityjwt.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        // return new User("foo", "foo", new ArrayList<>()); // originally in v1 where we passed a static user object
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(MyUserDetails::new).get();
    }
}
