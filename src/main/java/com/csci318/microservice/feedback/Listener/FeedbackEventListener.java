package com.csci318.microservice.feedback.Listener;

import com.csci318.microservice.feedback.Entities.Event.FeedbackCreatedEvent;
import com.csci318.microservice.feedback.Entities.Relation.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class FeedbackEventListener {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${restaurant.url.service}")
    private String RESTAURANT__URL;

//    @TransactionalEventListener
//    public void handleFeedbackCreatedEvent(FeedbackCreatedEvent event) {
//        Optional<Restaurant> restaurantOptional = restTemplate.getForObject("http://restaurant-service/restaurants/" + event.getRestaurantId(), Optional.class);
//        if (restaurantOptional.isPresent()) {
//            Restaurant restaurant = restaurantOptional.get();
//
//            // Update the average rating of the restaurant
//            double currentRating = restaurant.getRating() == null ? 0 : restaurant.getRating();
//            long feedbackCount = restaurant.getFeedbackCount() == null ? 0 : restaurant.getFeedbackCount();
//            double newRating = (currentRating * feedbackCount + event.getRating()) / (feedbackCount + 1);
//
//            restaurant.setRating(newRating);
//            restaurant.setFeedbackCount(feedbackCount + 1);
//
//            restaurantRepository.save(restaurant);
//        }
//    }
}
