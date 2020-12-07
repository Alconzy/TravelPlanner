package com.laioffer.TravelPlanner.service;

import com.laioffer.TravelPlanner.entity.GetAttractionsRequestBody;
import com.laioffer.TravelPlanner.entity.GetAttractionsResponseBody;
import com.laioffer.TravelPlanner.entity.Itinerary;
import com.laioffer.TravelPlanner.entity.ItineraryItem;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ItineraryItemService {

    public List<GetAttractionsResponseBody> getAttractions(Itinerary itinerary) {
        List<GetAttractionsResponseBody> results = new ArrayList<GetAttractionsResponseBody>();
        List<ItineraryItem> items = itinerary.getItineraryItem();
        items.sort(Comparator.comparing(ItineraryItem::getVisitDate));
        List<String> parsedDates = new ArrayList<String>();
        for (ItineraryItem item : items) {
            String currName = item.getAttraction().getName();
            String currDate = item.getVisitDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            if (!parsedDates.contains(currDate)) {
                parsedDates.add(currDate);
                GetAttractionsResponseBody result = new GetAttractionsResponseBody();
                result.setDate(item.getVisitDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
                result.names = new ArrayList<String>();
                result.names.add(currName);
                results.add(result);
            } else {
                results.get(results.size()-1).names.add(currName);
            }

        }
        return results;
    }
}
