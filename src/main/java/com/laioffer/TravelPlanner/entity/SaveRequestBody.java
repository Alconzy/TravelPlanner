package com.laioffer.TravelPlanner.entity;

import java.util.List;

public class SaveRequestBody {

    private Integer itineraryId;

    private List<String> dates;

    private List<List<Integer>> places;

    public Integer getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Integer itineraryId) {
        this.itineraryId = itineraryId;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<List<Integer>> getPlaces() {
        return places;
    }

    public void setPlaces(List<List<Integer>> places) {
        this.places = places;
    }
}
