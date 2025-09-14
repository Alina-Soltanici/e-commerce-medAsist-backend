package com.clinic.medAsist.mapper;


import com.clinic.medAsist.domain.User;
import com.clinic.medAsist.dto.SignupRequest;
import com.clinic.medAsist.dto.SignupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/*
When use @Mapper(componentModel = "spring"), MapStruct will generate the implementation of the
mapper interface as a Spring Bean. This means you can inject the mapper into other Spring-managed beans,
such as services and controllers, without manually creating instances of the mapper.
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);

    @Mapping (target = "message", constant = "registration completed successfully")
    SignupResponse toSignupResponse(User user);

}
