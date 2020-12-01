package com.laioffer.TravelPlanner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laioffer.TravelPlanner.entity.User;
import com.laioffer.TravelPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewCustomer (@RequestParam String emailId, @RequestParam String password) {
        User n = new User();
        n.setFirstName(emailId);
        n.setLastName(password);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        List<User> users = userRepository.findByEmail(email);
        if (users == null) {
            log.warn("attempting to log in with the non-existed account");
            return "User has not registered";
        } else {
            User user = users.get(0);
            if (user.getPassword().equals(password)) {
                //todo, return name value to front, assume related el expression is ${name}
                model.addAttribute("name", user.getFirstName()+ " " + user.getLastName());
                log.warn(user.toString()+ " logged in");
            } else {
                model.addAttribute("name", "logging failed, password incorrect");
                log.warn(user.toString()+ " failed to log in");
            }
            //todo, assume login page is index.html
            return "index";
        }
    }




}

