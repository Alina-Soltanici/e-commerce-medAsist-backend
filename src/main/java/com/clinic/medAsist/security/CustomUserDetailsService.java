package com.clinic.medAsist.security;

import com.clinic.medAsist.domain.User;
import com.clinic.medAsist.domain.UserPrincipal;
import com.clinic.medAsist.exception.UserNotFoundException;
import com.clinic.medAsist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("user not found"));
        return new UserPrincipal(user);
    }
}
