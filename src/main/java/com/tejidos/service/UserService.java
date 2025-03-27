package com.tejidos.service;


import com.tejidos.persistence.entity.User;

public interface UserService {
    User findByUsername(String username);
}
