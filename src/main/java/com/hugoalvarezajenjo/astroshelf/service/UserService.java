package com.hugoalvarezajenjo.astroshelf.service;

import com.hugoalvarezajenjo.astroshelf.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);

    Optional<User> findUserByUsername(String username);

    void deleteUserById(Long id);

    List<User> findAllUsers();

    Optional<User> getUserById(Long userId);

}
