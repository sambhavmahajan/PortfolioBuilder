package com.github.sambhavmahajan.portfoliobuilder.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Link {
    @Id
    private String name;
    private String url;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
    public Link() {}
    public Link(String name, String url, User u) {
        this.name = name;
        this.url = url;
        this.user = u;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name + " " + url + '\n';
    }
}
