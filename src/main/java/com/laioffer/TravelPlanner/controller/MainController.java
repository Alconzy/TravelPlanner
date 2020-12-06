package com.laioffer.TravelPlanner.controller;

import javax.validation.Valid;

import com.laioffer.TravelPlanner.entity.Itinerary;
import com.laioffer.TravelPlanner.entity.RegisterResponseBody;
import com.laioffer.TravelPlanner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.laioffer.TravelPlanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@RestController
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public RegisterResponseBody register(@RequestBody User user) {
        RegisterResponseBody registerResponseBody;

        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null) {
            registerResponseBody = new RegisterResponseBody("failed",userExists.getEmail(),"already exists");
        } else {
            userService.saveUser(user);
            registerResponseBody = new RegisterResponseBody("success",user.getEmail(),"registered");
        }
        return registerResponseBody;
    }




    @RequestMapping(value= {"/"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//
//        model.addObject("userName", user.getFirstName() + " " + user.getLastName());
        model.setViewName("home");
        return model;
    }

//    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
//    public ModelAndView accessDenied() {
//        ModelAndView model = new ModelAndView();
//        model.setViewName("errors/access_denied");
//        return model;
//    }

    @RequestMapping(value= {"/history/{startDate}/{endDate}"},method = RequestMethod.GET)
    public ModelAndView history(@PathVariable(value = "startDate") Date startDate, @PathVariable(value = "endDate") Date endDate){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userService.findUserByEmail(userName);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/history");
        List<Itinerary> itinerary = user.getItinerary();
        modelAndView.addObject("msg", "User Itinerary History");

        for(int i = 0; i<itinerary.size(); i++){
            if(itinerary.get(i).getStartDate().compareTo(startDate) >0  &&  itinerary.get(i).getEndDate().compareTo(endDate) <0){
                modelAndView.addObject("itinerary",itinerary.get(i));
            }
        }
        return modelAndView;
    }

}

