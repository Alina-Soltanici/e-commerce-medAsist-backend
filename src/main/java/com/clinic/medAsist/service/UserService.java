package com.clinic.medAsist.service;

import com.clinic.medAsist.dto.SignupRequest;
import com.clinic.medAsist.dto.SignupResponse;

public interface UserService {
   SignupResponse signUp(SignupRequest signupRequest);

}
