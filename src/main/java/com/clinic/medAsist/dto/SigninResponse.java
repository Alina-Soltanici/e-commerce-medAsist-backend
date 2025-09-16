package com.clinic.medAsist.dto;
import lombok.Data;

@Data
public class SigninResponse {
    private String token;
    private String tokenType;
    private Long userId;
}
