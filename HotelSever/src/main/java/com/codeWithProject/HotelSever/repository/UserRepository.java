package com.codeWithProject.HotelSever.repository;


import com.codeWithProject.HotelSever.entity.User;
import com.codeWithProject.HotelSever.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByUserRole (UserRole userRole);

}
