package com.clinic.medAsist.dto;

import lombok.Data;

@Data
public class SignupResponse {
    private Long id;
    private String fullName;
    private String email;
    private String message;
    private String token;
}
