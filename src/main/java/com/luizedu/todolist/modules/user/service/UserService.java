package com.luizedu.todolist.modules.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luizedu.todolist.modules.user.model.UserModel;
import com.luizedu.todolist.modules.user.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
    
    public List<UserModel> allUsers() {
        return userRepository.findAll();
    }

    public void save(UserModel user) {
        userRepository.save(user);
    }


}
