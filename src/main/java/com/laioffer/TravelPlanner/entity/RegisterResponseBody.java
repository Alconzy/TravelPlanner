package com.laioffer.TravelPlanner.entity;

public class RegisterResponseBody {

    public String status;

    public String email;

    public String msg;

    public RegisterResponseBody() {

    }

    public RegisterResponseBody(String status, String email, String msg) {
        this.status = status;
        this.email = email;
        this.msg = msg;
    }
}
