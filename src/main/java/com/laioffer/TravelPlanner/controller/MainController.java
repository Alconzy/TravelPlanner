package com.laioffer.TravelPlanner.controller;

import com.laioffer.TravelPlanner.entity.*;
import com.laioffer.TravelPlanner.service.ItineraryItemService;
import com.laioffer.TravelPlanner.service.ItineraryService;
import com.laioffer.TravelPlanner.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ItineraryService itineraryService;

    @Autowired
    private ItineraryItemService itineraryItemService;

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

    @PostMapping(value = "/save")
    public SaveResponseBody save(@RequestBody SaveRequestBody saveRequestBody) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userService.findUserByEmail(userName);
        SaveResponseBody saveResponseBody;

        Integer itineraryId = itineraryService.saveItinerary(saveRequestBody, user);
        saveResponseBody = new SaveResponseBody("OK", itineraryId, "saved");
        return saveResponseBody;
    }

    @PostMapping(value = "/get_history")
    public List<GetHistoryResponseBody> getHistory(@RequestBody GetHistoryRequestBody request) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userService.findUserByEmail(userName);
        List<GetHistoryResponseBody> response = itineraryService.getHistory(user,request.startDate, request.endDate);
        return response;
    }

    @PostMapping(value = "/get_attractions")
    public ResponseEntity<List<GetAttractionsResponseBody>> getAttractions(@RequestBody GetAttractionsRequestBody request) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("status","200");
        responseHeaders.set("message","success");
        Integer itineraryId = request.getItineraryId();
        Itinerary itinerary = itineraryService.findById(itineraryId);
        List<GetAttractionsResponseBody> response = itineraryItemService.getAttractions(itinerary);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(response);
    }

    @RequestMapping(value= {"/"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }
}

