package com.example.demo.config;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class AuthenticationConfig implements UserDetailsService {
    private final UserRepository userRepository;
    public AuthenticationConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        if (!user.isActivated()){
            throw new UsernameNotFoundException("User not activated");
        }
        return createSpringSecurityUser(user);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getAuthority().toString()));
        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), authorities);
    }
}
