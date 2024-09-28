package com.github.sambhavmahajan.portfoliobuilder.Controller;

import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import com.github.sambhavmahajan.portfoliobuilder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class PublicMapping {
    private final UserService userService;

    public PublicMapping(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping("/register/")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register/new")
    public String registerNewUser(@RequestParam("username") String username,
                                  @RequestParam("email") String email,
                                  @RequestParam("firstname") String firstname,
                                  @RequestParam("lastname") String lastname,
                                  @RequestParam("password") String password,
                                  Model model) {
        User u = userService.getUserByUsername(username);
        if (u != null) {
            model.addAttribute("error", "Username is already taken");
            return "register";
        }
        u = userService.getUserByEmail(email);
        if (u != null) {
            model.addAttribute("error", "Email is already in use");
            return "register";
        }
        u = new User(username, email, password, firstname, lastname, "");
        userService.addUser(u);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/profile";
    }
    @GetMapping("/profile")
    public String profile(Model model) {
        User u = userService.getUserByUsername(userService.getCurrentUsername());
        if(u == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", u.getUsername());
        return "profile";
    }
}