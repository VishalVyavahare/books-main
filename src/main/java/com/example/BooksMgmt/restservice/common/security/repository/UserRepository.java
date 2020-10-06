package com.example.BooksMgmt.restservice.common.security.repository;

import com.example.BooksMgmt.restservice.common.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findAllByUserAndPassword(String user, String password);
}
