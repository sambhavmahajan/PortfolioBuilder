package com.github.sambhavmahajan.portfoliobuilder.Controller;

import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import com.github.sambhavmahajan.portfoliobuilder.Service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view/")
public class PublicMapping {
    private UserService userService;
    @GetMapping("/{username}")
    public String view(Model model, @PathVariable("username") String username) {
        return "viewUser.html";
    }
}
