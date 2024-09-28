package com.github.sambhavmahajan.portfoliobuilder.Controller;

import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import com.github.sambhavmahajan.portfoliobuilder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PublicMapping {
    @Autowired
    private UserService userService;
    @GetMapping("/view/{username}")
    public String view(Model model, @PathVariable("username") String username) {
        User u = userService.getUserByUsername(username);
        if (u == null) {
            model.addAttribute("username", "User not found");
            return "viewUser";
        }
        model.addAttribute("username", u.getUsername());
        model.addAttribute("firstname", u.getFirstname());
        model.addAttribute("lastname", u.getLastname());
        model.addAttribute("email", u.getEmail());
        model.addAttribute("objective", u.getObjective());
        model.addAttribute("links", u.getLinks());
        return "viewUser";
    }
}
