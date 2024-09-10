package com.csci318.microservice.feedback.Mappers;

import com.csci318.microservice.feedback.DTOs.FeedbackDTORequest;
import com.csci318.microservice.feedback.DTOs.FeedbackDTOResponse;
import com.csci318.microservice.feedback.Entities.Feedback;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackMapper implements Mapper<Feedback, FeedbackDTOResponse, FeedbackDTORequest> {
    @Override
    public FeedbackDTOResponse toDtos(Feedback entity) {
        FeedbackDTOResponse feedbackDTOResponse = new FeedbackDTOResponse();
        feedbackDTOResponse.setId(entity.getId());
        feedbackDTOResponse.setUserId(entity.getUserId());
        feedbackDTOResponse.setRestaurantId(entity.getRestaurantId());
        feedbackDTOResponse.setRating(entity.getRating());
        feedbackDTOResponse.setComments(entity.getComments());
        return feedbackDTOResponse;
    }

    @Override
    public Feedback toEntities(FeedbackDTORequest dto) {
        Feedback feedback = new Feedback();
        feedback.setUserId(dto.getUserId());
        feedback.setRestaurantId(dto.getRestaurantId());
        feedback.setRating(dto.getRating());
        feedback.setComments(dto.getComments());
        return feedback;
    }

    @Override
    public List<FeedbackDTOResponse> toDtos(List<Feedback> entities) {
        return entities.stream().map(this::toDtos).collect(Collectors.toList());
    }

    @Override
    public List<Feedback> toEntities(List<FeedbackDTORequest> dtos) {
        return dtos.stream().map(this::toEntities).collect(Collectors.toList());
    }
}
