package com.csci318.microservice.feedback.Services.Impl;

import com.csci318.microservice.feedback.Entities.Event.FeedbackCreatedEvent;
import com.csci318.microservice.feedback.Repositories.FeedbackEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventHandler {

    private final FeedbackEventRepository feedbackEventRepository;
    private final Logger logger = Logger.getLogger(EventHandler.class.getName());

    public EventHandler(FeedbackEventRepository feedbackEventRepository) {
        this.feedbackEventRepository = feedbackEventRepository;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleFeedbackCreatedEvent(FeedbackCreatedEvent feedbackCreatedEvent) {
        try {
            feedbackEventRepository.save(feedbackCreatedEvent);
            logger.info("Feedback created event saved to database");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving FeedbackCreatedEvent: " + e.getMessage(), e);
        }
    }
}
