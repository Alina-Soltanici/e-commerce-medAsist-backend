package com.clinic.medAsist.service;

import com.clinic.medAsist.dto.*;

import java.util.List;

public interface AuthService {
   SignupResponse signUp(SignupRequest signupRequest);
   SigninResponse login(SigninRequest signinRequest);
   List<UserDTO> getAllUsers();

}
