package com.laioffer.TravelPlanner.entity;

import java.util.List;

public class SaveRequestBody {

    private List<String> dates;

    private List<List<String>> places;

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }

    public List<List<String>> getPlaces() {
        return places;
    }

    public void setPlaces(List<List<String>> places) {
        this.places = places;
    }
}
