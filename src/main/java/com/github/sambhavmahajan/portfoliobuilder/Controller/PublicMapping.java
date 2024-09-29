package com.github.sambhavmahajan.portfoliobuilder.Controller;

import com.github.sambhavmahajan.portfoliobuilder.Model.Link;
import com.github.sambhavmahajan.portfoliobuilder.Model.User;
import com.github.sambhavmahajan.portfoliobuilder.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

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
        List<Link> links = u.getLinks();
        List<String> li = new ArrayList<>();
        for(Link link : links) {
            li.add(link.toString());
        }
        model.addAttribute("links", li);
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
        userService.saveUser(u);
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
        model.addAttribute("firstname", u.getFirstname());
        model.addAttribute("lastname", u.getLastname());
        model.addAttribute("email", u.getEmail());
        model.addAttribute("objective", u.getObjective());
        List<Link> links = u.getLinks();
        StringBuilder s = new StringBuilder();
        for(Link link : links) {
            s.append(link.toString());
        }
        model.addAttribute("links", s.toString());
        return "profile";
    }
    @PostMapping("/profile")
    public String updateProfile(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("firstname") String firstname,
            @RequestParam("lastname") String lastname,
            @RequestParam("links") String links,
            @RequestParam("objective") String objective,
            Model model) {

        User u = userService.getUserByUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setObjective(objective);
        u.LinesToLinks(links);
        userService.saveUser(u);
        model.addAttribute("message", "Profile Updated");
        model.addAttribute("username", u.getUsername());
        model.addAttribute("firstname", u.getFirstname());
        model.addAttribute("lastname", u.getLastname());
        model.addAttribute("email", u.getEmail());
        model.addAttribute("objective", u.getObjective());
        List<Link> linksLi = u.getLinks();
        StringBuilder s = new StringBuilder();
        for(Link link : linksLi) {
            s.append(link.toString());
        }
        model.addAttribute("links", s.toString());
        return "profile";
    }
}