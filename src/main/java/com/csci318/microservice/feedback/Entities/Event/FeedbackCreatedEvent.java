package com.csci318.microservice.feedback.Entities.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "feedback-event")
public class FeedbackCreatedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String eventName;
    private UUID userId;
    private UUID restaurantId;
    private int rating;
    private double averageRating;



}
