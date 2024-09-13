package com.csci318.microservice.feedback.Services.Impl;

import com.csci318.microservice.feedback.Domain.Events.FeedbackCreatedEvent;
import com.csci318.microservice.feedback.Domain.Relations.Restaurant;
import com.csci318.microservice.feedback.Repositories.FeedbackEventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventHandler {

    private final FeedbackEventRepository feedbackEventRepository;
    private final RestTemplate restTemplate;
    @Value("${restaurant.url.service}")
    private String RESTAURANT_URL;
    private final Logger logger = Logger.getLogger(EventHandler.class.getName());

    public EventHandler(FeedbackEventRepository feedbackEventRepository, RestTemplate restTemplate) {
        this.feedbackEventRepository = feedbackEventRepository;
        this.restTemplate = restTemplate;
    }

   @EventListener
    public void handleFeedbackCreatedEvent(FeedbackCreatedEvent event) {
        try {
            // STEP 1: save the event to the database
            feedbackEventRepository.save(event);
            logger.info("Saving FeedbackCreatedEvent: " + event);

            // STEP 2: retrieve the restaurant from the restaurant service
            Restaurant restaurant = restTemplate.getForObject(RESTAURANT_URL + "/findById/"+ event.getRestaurantId(), Restaurant.class);
            if (restaurant == null) {
                throw new Exception("Restaurant not found for id: " + event.getRestaurantId());
            }

            // STEP 3: update the restaurant rating from the event rating
            String url = RESTAURANT_URL + "/" + event.getRestaurantId() + "/rating";
            this.restTemplate.put(url, event);
            logger.info("Updating restaurant rating: " + url);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving FeedbackCreatedEvent: " + e.getMessage(), e);
        }
    }

}
