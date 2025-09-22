package com.clinic.medAsist.controller;

import com.clinic.medAsist.dto.*;
import com.clinic.medAsist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/api/auth/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<> (userService.signUp (signupRequest), HttpStatus.CREATED);
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<SigninResponse> login(@RequestBody SigninRequest signinRequest) {
        return new ResponseEntity<>(userService.login(signinRequest), HttpStatus.OK);
    }


    @GetMapping("/secure")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }


    @GetMapping("/api/admin/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping("/api/user/profile")
    public ResponseEntity<String> profile() {
        return new ResponseEntity<>("User profile", HttpStatus.OK);
    }
}
