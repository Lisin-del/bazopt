package ru.lisin.bazopt.service;

import ru.lisin.bazopt.model.User;

public interface UserService {
    User createUser(User user);

    User getUserById(long id);

    User getUserByEmail(String userEmail);

    User getCurrentUser();

    User updateUser(User user);
}
