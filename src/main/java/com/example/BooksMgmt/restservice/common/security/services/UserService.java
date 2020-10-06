package com.example.BooksMgmt.restservice.common.security.services;

import com.example.BooksMgmt.restservice.common.security.model.User;

public interface UserService {

    User createUser(User user);

    User findByUserNameAndPassword(String username, String password);
}
