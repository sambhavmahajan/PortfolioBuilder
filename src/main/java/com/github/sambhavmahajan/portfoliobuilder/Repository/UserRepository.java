package com.github.sambhavmahajan.portfoliobuilder.Repository;

import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    public User findByUsername(String username);
    public User findByEmail(String email);
}
