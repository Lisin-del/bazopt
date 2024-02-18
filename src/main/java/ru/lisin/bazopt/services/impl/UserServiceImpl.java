package ru.lisin.bazopt.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lisin.bazopt.model.User;
import ru.lisin.bazopt.repository.UserRepository;
import ru.lisin.bazopt.services.EncryptorService;
import ru.lisin.bazopt.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EncryptorService encryptorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EncryptorService encryptorService) {
        this.userRepository = userRepository;
        this.encryptorService = encryptorService;
    }

    @Override
    public User createUser(User user) {
        user.setPassword(encryptorService.encrypt(user.getPassword()));
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
}
