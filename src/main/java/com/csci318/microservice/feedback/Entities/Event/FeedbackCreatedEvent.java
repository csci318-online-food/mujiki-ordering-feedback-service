package com.csci318.microservice.feedback.Entities.Event;

import lombok.Getter;

import java.util.UUID;

@Getter
public class FeedbackCreatedEvent {
    private final UUID feedbackId;
    private final UUID restaurantId;
    private final int rating;

    public FeedbackCreatedEvent(UUID feedbackId, UUID restaurantId, int rating) {
        this.feedbackId = feedbackId;
        this.restaurantId = restaurantId;
        this.rating = rating;
    }
}
