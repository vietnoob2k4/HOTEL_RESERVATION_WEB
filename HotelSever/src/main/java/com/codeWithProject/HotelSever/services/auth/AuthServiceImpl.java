package com.codeWithProject.HotelSever.services.auth;


import com.codeWithProject.HotelSever.dto.SignupRequest;
import com.codeWithProject.HotelSever.dto.UserDto;
import com.codeWithProject.HotelSever.entity.User;
import com.codeWithProject.HotelSever.enums.UserRole;
import com.codeWithProject.HotelSever.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.codeWithProject.HotelSever.services.auth.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private  final UserRepository userRepository;
    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findFirstByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));


            userRepository.save(user);
            System.out.println("Admin account created");
        }else {
            System.out.println("Admin account already exists");
        }
    }
    public UserDto createUser(SignupRequest signupRequest){
        if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()){
            throw  new EntityExistsException("Email already exists" + signupRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}