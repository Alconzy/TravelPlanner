package com.laioffer.TravelPlanner.service;

import com.laioffer.TravelPlanner.entity.ItineraryItem;
import com.laioffer.TravelPlanner.entity.SaveRequestBody;
import com.laioffer.TravelPlanner.repository.AttractionRepository;
import com.laioffer.TravelPlanner.repository.ItineraryItemRepository;
import com.laioffer.TravelPlanner.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ItineraryItemService {


    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    AttractionRepository attractionRepository;

    @Autowired
    ItineraryItemRepository itineraryItemRepository;


    public void saveItinerary(SaveRequestBody saveRequestBody) {
        Integer itineraryId =  saveRequestBody.getItineraryId();
        List<String> dates = saveRequestBody.getDates();
        List<List<Integer>> places = saveRequestBody.getPlaces();

        ItineraryItem itineraryItem;

        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = LocalDate.parse(dates.get(i), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            for (int p = 0; p < places.get(i).size(); p++) {
                Integer target = places.get(i).get(p);
                itineraryItem = new ItineraryItem();
                itineraryItem.setVisitDate(date);
                itineraryItem.setAttraction(attractionRepository.findById(target).orElse(null));
                itineraryItem.setItinerary(itineraryRepository.findById(itineraryId).orElse(null));
                itineraryItemRepository.save(itineraryItem);

            }
        }

    }

}
