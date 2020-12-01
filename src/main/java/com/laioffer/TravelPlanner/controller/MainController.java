package com.laioffer.TravelPlanner.controller;

import com.laioffer.TravelPlanner.entity.User;
import com.laioffer.TravelPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

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






}

