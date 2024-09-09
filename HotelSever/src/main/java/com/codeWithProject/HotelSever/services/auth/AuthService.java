package com.codeWithProject.HotelSever.services.auth;

import com.codeWithProject.HotelSever.dto.SignupRequest;
import com.codeWithProject.HotelSever.dto.UserDto;
import com.codeWithProject.HotelSever.entity.User;
import com.codeWithProject.HotelSever.enums.UserRole;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface AuthService {

UserDto createUser(SignupRequest signupRequest);

}
