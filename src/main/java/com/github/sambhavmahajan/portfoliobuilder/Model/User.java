package com.github.sambhavmahajan.portfoliobuilder.Model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "UserTable")
public class User implements UserDetails {
    @Id
    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    public String objective;
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Link> Links;
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<GrantedAuthority> authorities;
    public User() {}
    public User(String username, String email, String password, String firstname, String lastname, String objective) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.objective = objective;
        this.Links = new ArrayList<Link>();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    public String getEmail() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    public List<Link> getLinks() {
        return this.Links;
    }

    public void addLink(Link link) {
        this.Links.add(link);
    }

    public void removeLink(Link link) {
        this.Links.remove(link);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getObjective() {
        return objective;
    }
    public void setObjective(String objective) {
        this.objective = objective;
    }
}
