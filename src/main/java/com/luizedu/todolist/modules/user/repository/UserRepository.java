package com.luizedu.todolist.modules.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.luizedu.todolist.modules.user.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

    UserDetails findByUsername(String login);
    
}
