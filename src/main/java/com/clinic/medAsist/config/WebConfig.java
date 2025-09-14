//package com.clinic.medAsist.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:3000"); // Frontend URL
//        configuration.addAllowedMethod("GET"); // Allow GET method
//        configuration.addAllowedMethod("POST"); // Allow POST method
//        configuration.addAllowedMethod("PUT"); // Allow PUT method
//        configuration.addAllowedMethod("DELETE"); // Allow DELETE method
//        configuration.addAllowedHeader("*"); // Allow all headers
//        configuration.setAllowCredentials(true); // Allow credentials (if needed)
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Apply to all endpoints
//
//        return source;
//    }
//}
