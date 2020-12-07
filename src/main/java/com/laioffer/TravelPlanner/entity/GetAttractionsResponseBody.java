package com.laioffer.TravelPlanner.entity;

import java.util.List;

public class GetAttractionsResponseBody {

    public String date;

    public List<String> names;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
