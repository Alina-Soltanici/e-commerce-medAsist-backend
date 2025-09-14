package com.clinic.medAsist.controller;

import com.clinic.medAsist.dto.SignupRequest;
import com.clinic.medAsist.dto.SignupResponse;
import com.clinic.medAsist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @CrossOrigin(origins = "http://127.0.0.1:5500", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        return new ResponseEntity<> (userService.signUp (signupRequest), HttpStatus.CREATED);
    }
}
