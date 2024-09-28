package com.github.sambhavmahajan.portfoliobuilder.Service;

import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import com.github.sambhavmahajan.portfoliobuilder.Repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }
        return u;
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.isAuthenticated()) {
            return auth.getName();
        }
        return null;
    }
}
