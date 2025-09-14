package com.clinic.medAsist.config;


import com.clinic.medAsist.domain.Role;
import com.clinic.medAsist.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.findByName ("USER").isEmpty ()) {
            roleRepository.save (new Role (null, "USER"));
        }

        if(roleRepository.findByName ("ADMIN").isEmpty ()) {
            roleRepository.save (new Role (null, "ADMIN"));
        }
    }
}

