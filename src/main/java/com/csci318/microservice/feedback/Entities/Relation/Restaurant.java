package com.csci318.microservice.feedback.Entities.Relation;

import com.csci318.microservice.feedback.Constants.HttpStatus.CuisineType;

import java.time.LocalTime;

public class Restaurant {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private CuisineType cuisine;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Double rating;
    private String description;
    private boolean isOpened;
}
