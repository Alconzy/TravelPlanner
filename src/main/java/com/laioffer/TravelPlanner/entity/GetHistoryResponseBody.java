package com.laioffer.TravelPlanner.entity;

import java.util.List;

public class GetHistoryResponseBody {

    public Integer itineraryId;

    public String city;

    public String date;

    public List<Double> startLocation;

    public List<Double> endLocation;

    public Integer getItineraryId() {
        return itineraryId;
    }

    public void setItineraryId(Integer itineraryId) {
        this.itineraryId = itineraryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Double> getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(List<Double> startLocation) {
        this.startLocation = startLocation;
    }

    public List<Double> getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(List<Double> endLocation) {
        this.endLocation = endLocation;
    }

    public GetHistoryResponseBody() {
    }

    public GetHistoryResponseBody(Integer itineraryId,
                                  String city,
                                  String date,
                                  List<Double> startLocation,
                                  List<Double> endLocation) {
        this.itineraryId = itineraryId;
        this.city = city;
        this.date = date;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }
}
