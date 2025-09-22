package com.clinic.medAsist.service;

import com.clinic.medAsist.domain.User;
import com.clinic.medAsist.dto.*;

import java.util.List;

public interface UserService {
   SignupResponse signUp(SignupRequest signupRequest);
   SigninResponse login(SigninRequest signinRequest);
   List<UserDTO> getAllUsers();

}
