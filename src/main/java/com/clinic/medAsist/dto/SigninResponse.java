package com.clinic.medAsist.dto;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SigninResponse {
    private String token;
    private String refreshToken;
    private String tokenType;
    private Long userId;
}
