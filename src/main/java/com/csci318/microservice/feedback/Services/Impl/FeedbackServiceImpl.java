package com.csci318.microservice.feedback.Services.Impl;

import com.csci318.microservice.feedback.DTOs.FeedbackDTORequest;
import com.csci318.microservice.feedback.DTOs.FeedbackDTOResponse;
import com.csci318.microservice.feedback.Entities.Event.FeedbackCreatedEvent;
import com.csci318.microservice.feedback.Entities.Feedback;
import com.csci318.microservice.feedback.Mappers.FeedbackMapper;
import com.csci318.microservice.feedback.Repositories.FeedbackEventRepository;
import com.csci318.microservice.feedback.Repositories.FeedbackRepository;
import com.csci318.microservice.feedback.Services.FeedbackService;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
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
    private final FeedbackEventRepository feedbackEventRepository;
    private final FeedbackMapper feedbackMapper;
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public FeedbackServiceImpl(RestTemplate restTemplate, FeedbackRepository feedbackRepository, FeedbackEventRepository feedbackEventRepository, FeedbackMapper feedbackMapper) {
        this.restTemplate = restTemplate;
        this.feedbackRepository = feedbackRepository;
        this.feedbackEventRepository = feedbackEventRepository;
        this.feedbackMapper = feedbackMapper;
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

        FeedbackCreatedEvent event = new FeedbackCreatedEvent();
        event.setEventName("User feedback created");
        event.setUserId(feedback.getUserId());
        event.setRestaurantId(feedback.getRestaurantId());
        event.setRating(feedback.getRating());
        event.setAverageRating(averageRating);
        eventPublisher.publishEvent(event);
        if (feedbackEventRepository.count() >0) {
            Logger.getLogger(FeedbackServiceImpl.class.getName()).info("Feedback created event registered");
        }


        // Publish the event
        FeedbackDTOResponse feedbackDTOResponse = this.feedbackMapper.toDtos(feedback);
        return feedbackDTOResponse;
    }


}
