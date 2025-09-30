package com.clinic.medAsist.service;

import com.clinic.medAsist.domain.RefreshToken;
import com.clinic.medAsist.domain.Role;
import com.clinic.medAsist.domain.User;
import com.clinic.medAsist.domain.UserPrincipal;
import com.clinic.medAsist.dto.*;
import com.clinic.medAsist.exception.*;
import com.clinic.medAsist.mapper.UserMapper;
import com.clinic.medAsist.repository.RoleRepository;
import com.clinic.medAsist.repository.UserRepository;
import com.clinic.medAsist.security.AccessTokenService;
import com.clinic.medAsist.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public SignupResponse signUp(SignupRequest signupRequest) {
        if(userRepository.existsByEmail (signupRequest.getEmail ())) {
            log.info("Email already exists: {}", signupRequest.getEmail());
            throw new UserAlreadyExistsException ("Email already taken!");
        }

        if(!signupRequest.getConfirmPassword ().equals (signupRequest.getPassword ())){
            log.info("Password don't match.");
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

        log.info("User registered successfully");

        UserPrincipal userPrincipal = new UserPrincipal(savedUser);
        String accessToken = accessTokenService.generateToken(userPrincipal);

        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(userPrincipal);
        refreshTokenService.saveRefreshToken(refreshToken);
        SignupResponse signupResponse = userMapper.toSignupResponse(savedUser);
        signupResponse.setToken(accessToken);
        signupResponse.setRefreshToken(refreshToken.getToken());
        return signupResponse;
    }

    @Override
    public SigninResponse login(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = accessTokenService.generateToken(userPrincipal);
        log.info("Access token issued successfully:)");
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken(userPrincipal);
        refreshTokenService.saveRefreshToken(refreshToken);
        log.info("Refresh token issued successfully!");
        log.info("User logged successfully");

        SigninResponse signinResponse = new SigninResponse();
        signinResponse.setToken(token);
        signinResponse.setRefreshToken(refreshToken.getToken());
        signinResponse.setTokenType("Bearer");
        signinResponse.setUserId(userPrincipal.getId());

        return signinResponse;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> usersEntity = userRepository.findAll();
        return userMapper.toUserDTO(usersEntity);
    }
}
