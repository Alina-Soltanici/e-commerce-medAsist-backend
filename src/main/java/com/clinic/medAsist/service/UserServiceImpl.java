package com.clinic.medAsist.service;

import com.clinic.medAsist.domain.Role;
import com.clinic.medAsist.domain.User;
import com.clinic.medAsist.dto.SignupRequest;
import com.clinic.medAsist.dto.SignupResponse;
import com.clinic.medAsist.exception.PasswordMismatchException;
import com.clinic.medAsist.exception.RoleNotFoundException;
import com.clinic.medAsist.exception.TermsAndPrivacyNotAcceptedException;
import com.clinic.medAsist.exception.UserAlreadyExistsException;
import com.clinic.medAsist.mapper.UserMapper;
import com.clinic.medAsist.repository.RoleRepository;
import com.clinic.medAsist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public SignupResponse signUp(SignupRequest signupRequest) {
        if(userRepository.existsByEmail (signupRequest.getEmail ())) {
            throw new UserAlreadyExistsException ("Email already taken!");
        }

        if(!signupRequest.getConfirmPassword ().equals (signupRequest.getPassword ())){
            throw new PasswordMismatchException ("Passwords do not match!");
        }

        if(!signupRequest.isTermsAccepted () || !signupRequest.isPrivacyAccepted ()) {
            throw new TermsAndPrivacyNotAcceptedException ("You must accept the terms and privacy policy to create an account.");
        }

        User user = new User ();
        user.setFullName (signupRequest.getFullName ());
        user.setEmail (signupRequest.getEmail ());
        user.setPassword (passwordEncoder.encode (signupRequest.getPassword ()));
        user.setTermsAccepted (signupRequest.isTermsAccepted ());
        user.setPrivacyAccepted (signupRequest.isPrivacyAccepted ());

        Role defaultRole = roleRepository.findByName ("USER")
                .orElseThrow (() -> new RoleNotFoundException ("Role not found!"));

        user.setRoles (Set.of (defaultRole));
        User savedUser = userRepository.save (user);
        return userMapper.toSignupResponse (savedUser);
    }
}
