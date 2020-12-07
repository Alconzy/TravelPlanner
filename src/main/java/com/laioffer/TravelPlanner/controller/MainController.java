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

import java.util.ArrayList;
import java.util.Date;
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

    @GetMapping(value = "/get_history")
    public List<GetHistoryResponseBody> getHistory(@RequestBody GetHistoryRequestBody request) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userName = loggedInUser.getName();
        User user = userService.findUserByEmail(userName);


        List<GetHistoryResponseBody> response = itineraryService.getHistory(user,request.startDate, request.endDate);
        return response;
    }

    @GetMapping(value = "/get_attractions")
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

//        for(int i = 0; i<itinerary.size(); i++){
//            if(itinerary.get(i).getStartDate().compareTo(startDate) >0  &&  itinerary.get(i).getEndDate().compareTo(endDate) <0){
//                modelAndView.addObject("itinerary",itinerary.get(i));
//            }
//        }
        return modelAndView;
    }

}

