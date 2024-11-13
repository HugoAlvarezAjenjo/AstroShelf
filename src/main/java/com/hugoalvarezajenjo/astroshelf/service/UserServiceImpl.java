package com.hugoalvarezajenjo.astroshelf.service;

import com.hugoalvarezajenjo.astroshelf.model.User;
import com.hugoalvarezajenjo.astroshelf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUserById(final Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(final Long userId) {
        return this.userRepository.findById(userId);
    }
}
