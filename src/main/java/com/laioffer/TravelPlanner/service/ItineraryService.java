package com.laioffer.TravelPlanner.service;

import com.laioffer.TravelPlanner.entity.*;
import com.laioffer.TravelPlanner.repository.AttractionRepository;
import com.laioffer.TravelPlanner.repository.ItineraryItemRepository;
import com.laioffer.TravelPlanner.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ItineraryService {

    @Autowired
    ItineraryRepository itineraryRepository;

    @Autowired
    AttractionRepository attractionRepository;

    @Autowired
    ItineraryItemRepository itineraryItemRepository;

    public Integer saveItinerary(SaveRequestBody saveRequestBody, User user) {
        List<String> dates = saveRequestBody.getDates();
        Itinerary itinerary = new Itinerary();
        itinerary.setStartDate(LocalDate.parse(dates.get(0), DateTimeFormatter.ISO_LOCAL_DATE));
        itinerary.setEndDate(LocalDate.parse(dates.get(dates.size() - 1), DateTimeFormatter.ISO_LOCAL_DATE));
        itinerary.setUser(user);
        itineraryRepository.save(itinerary);

        List<List<Integer>> places = saveRequestBody.getPlaces();

        for (int i = 0; i < dates.size(); i++) {
            LocalDate date = LocalDate.parse(dates.get(i), DateTimeFormatter.ISO_LOCAL_DATE);
            for (int p = 0; p < places.get(i).size(); p++) {
                Integer target = places.get(i).get(p);
                ItineraryItem itineraryItem = new ItineraryItem();
                itineraryItem.setVisitDate(date);
                itineraryItem.setAttraction(attractionRepository.findById(target).orElse(null));
                itineraryItem.setItinerary(itinerary);
                itineraryItemRepository.save(itineraryItem);
            }
        }

        return itinerary.getId();
    }

    public List<GetHistoryResponseBody> getHistory(User user, String start, String end) {
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);

        List<Itinerary> itineraries = user.getItinerary();
        List<Itinerary> remainedIti = new ArrayList<Itinerary>();

        for (Itinerary itinerary : itineraries) {
            if (itinerary.getStartDate().compareTo(startDate) > 0 && itinerary.getEndDate().compareTo(endDate) < 0) {
                remainedIti.add(itinerary);
            }
        }

        List<GetHistoryResponseBody> results = new ArrayList<GetHistoryResponseBody>();
        for (Itinerary itinerary : remainedIti) {
            GetHistoryResponseBody item = new GetHistoryResponseBody();
            item.setItineraryId(itinerary.getId());
            item.setCity("New York");
            item.setDate(itinerary.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            Double[][] locations = this.findLocation(itinerary);
            item.setStartLocation(Arrays.asList(locations[0]));
            item.setEndLocation(Arrays.asList(locations[1]));
            results.add(item);
        }
        return results;

    }

    private Double[][] findLocation(Itinerary itinerary) {
        List<ItineraryItem> places = itinerary.getItineraryItem();
        places.sort(Comparator.comparing(ItineraryItem::getVisitDate));
        Attraction start = places.get(0).getAttraction();
        Attraction end = places.get(places.size() - 1).getAttraction();
        Double startLat = start.getLatitude();
        Double startLon = start.getLongitude();
        Double endLat = end.getLatitude();
        Double endLon = end.getLongitude();
        Double[][] result = new Double[2][2];
        result[0][0] = startLat;
        result[0][1] = startLon;
        result[1][0] = endLat;
        result[1][1] = endLon;
        return result;
    }

}
