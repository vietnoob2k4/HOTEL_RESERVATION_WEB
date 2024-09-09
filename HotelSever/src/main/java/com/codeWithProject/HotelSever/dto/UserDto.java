package com.codeWithProject.HotelSever.dto;


import com.codeWithProject.HotelSever.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
