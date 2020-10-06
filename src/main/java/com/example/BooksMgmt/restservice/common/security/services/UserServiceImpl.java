package com.example.BooksMgmt.restservice.common.security.services;

import com.example.BooksMgmt.restservice.common.security.model.User;
import com.example.BooksMgmt.restservice.common.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {
        return userRepository.findAllByUserAndPassword(username, password);
    }
}
