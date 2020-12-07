package com.laioffer.TravelPlanner.entity;

public class SaveResponseBody {

    public Integer ItineraryId;

    public String status;

    public String msg;

    public SaveResponseBody() {

    }

    public SaveResponseBody(String status, Integer ItineraryId, String msg) {
        this.status = status;
        this.ItineraryId = ItineraryId;
        this.msg = msg;
    }
}
