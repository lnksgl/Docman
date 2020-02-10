package spring.implementation;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import spring.model.User;
import spring.repository.UserRepository;

import java.util.Collections;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service("UserDetailsService")
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("No user found " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), true, true, true, true,
                getAuthorities("ROLE_USER"));
    }

    private java.util.Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
