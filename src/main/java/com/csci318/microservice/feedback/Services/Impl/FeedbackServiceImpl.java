package com.csci318.microservice.feedback.Services.Impl;

import com.csci318.microservice.feedback.DTOs.FeedbackDTORequest;
import com.csci318.microservice.feedback.DTOs.FeedbackDTOResponse;
import com.csci318.microservice.feedback.Entities.Event.FeedbackCreatedEvent;
import com.csci318.microservice.feedback.Entities.Feedback;
import com.csci318.microservice.feedback.Mappers.FeedbackMapper;
import com.csci318.microservice.feedback.Repositories.FeedbackRepository;
import com.csci318.microservice.feedback.Services.FeedbackService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Value("${restaurant.url.service}")
    private String RESTAURANT_URL;
    private final RestTemplate restTemplate;
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final ApplicationEventPublisher eventPublisher;




    public FeedbackServiceImpl(RestTemplate restTemplate, FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, ApplicationEventPublisher eventPublisher) {
        this.restTemplate = restTemplate;
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public FeedbackDTOResponse createFeedback(FeedbackDTORequest feedbackDTORequest) {
        log.info("Creating feedback");
        Feedback feedback = new Feedback();
        feedback.setRating(feedbackDTORequest.getRating());
        feedback.setComments(feedbackDTORequest.getComments());
        feedback.setUserId(feedbackDTORequest.getUserId());
        feedback.setRestaurantId(feedbackDTORequest.getRestaurantId());
        this.feedbackRepository.save(feedback);

        // Update the average rating of the restaurant
        List<Feedback> feedbacks = this.feedbackRepository.findByRestaurantId(feedbackDTORequest.getRestaurantId());

        int totalRating = 0;
        for (Feedback f : feedbacks) {
            totalRating += f.getRating();
        }

        int averageRating = totalRating / feedbacks.size();
        String url = RESTAURANT_URL + "/" + feedbackDTORequest.getRestaurantId() + "/rating/" + averageRating;
        this.restTemplate.put(url, null);

        // Register event with average rating
        FeedbackCreatedEvent feedbackWithEvent = feedback.registerFeedbackCreatedEvent(averageRating);

        // Publish the event
        this.eventPublisher.publishEvent(feedbackWithEvent);
        FeedbackDTOResponse feedbackDTOResponse = this.feedbackMapper.toDtos(feedback);
        return feedbackDTOResponse;
    }
}
