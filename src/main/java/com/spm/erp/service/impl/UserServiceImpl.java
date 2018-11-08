package com.spm.erp.service.impl;

import com.spm.erp.exception.AppException;
import com.spm.erp.model.*;
import com.spm.erp.repository.RoleRepository;
import com.spm.erp.repository.UserRepository;
import com.spm.erp.security.JwtTokenProvider;
import com.spm.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Override
    public CustomResponse registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new CustomResponse(false, "Username is already taken!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return new CustomResponse(false, "Email Address already in use!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));
        try {
            userRepository.save(user);
            return new CustomResponse(true, "User registered successfully");
        } catch (Exception ex) {
            throw new AppException("User Role not set.");
        }
    }

    @Override
    public String authenticateUser(Login login) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return jwt;
    }
}
