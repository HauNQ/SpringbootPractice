package com.example.springMVCSecurityUsingJpa.service;

import com.example.springMVCSecurityUsingJpa.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUsername(String username);

}
