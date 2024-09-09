package com.codeWithProject.HotelSever.dto;


import com.codeWithProject.HotelSever.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private  Long userId;
    private UserRole userRole;
}
