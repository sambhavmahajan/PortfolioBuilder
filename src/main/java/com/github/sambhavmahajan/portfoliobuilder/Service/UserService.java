package com.github.sambhavmahajan.portfoliobuilder.Service;

import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import com.github.sambhavmahajan.portfoliobuilder.Repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User addUser(User user) {
        return userRepository.save(user);
    }
}
