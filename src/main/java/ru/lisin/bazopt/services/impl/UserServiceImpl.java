package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.repository.UserRepository;
import ru.lisin.bazopt.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User getUserByEmail(String userEmail) {
        return userRepository.getUserByEmail(userEmail);
    }

    @Override
    public User getCurrentUser() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.getUserByEmail(currentUserEmail);
    }

    @Override
    public User updateUser(User user) {
        User currentUser = getCurrentUser();
        currentUser.setEmail(user.getEmail());
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());

        String passwordToSave = user.getPassword();
        if (passwordToSave != null && !passwordToSave.isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        currentUser.setAddress(user.getAddress());

        return userRepository.save(currentUser);
    }
}
