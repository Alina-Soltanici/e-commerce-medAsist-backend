package com.clinic.medAsist.config;


import com.clinic.medAsist.domain.Role;
import com.clinic.medAsist.domain.User;
import com.clinic.medAsist.repository.RoleRepository;
import com.clinic.medAsist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role(null, "USER")));

        if(!userRepository.existsByEmail("admin1@gmail.com")) {
            User admin = User
                    .builder()
                    .fullName("admin")
                    .email("admin1@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .termsAccepted(true)
                    .privacyAccepted(true)
                    .roles(Set.of(adminRole))
                    .build();

            userRepository.save(admin);
        }
    }
}

