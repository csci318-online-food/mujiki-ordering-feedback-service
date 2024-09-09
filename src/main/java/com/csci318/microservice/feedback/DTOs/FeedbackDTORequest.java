package com.csci318.microservice.feedback.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackDTORequest {
    private UUID restaurantId;
    private Integer rating;
    private String comments;
}
