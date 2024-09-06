package com.csci318.microservice.feedback.Utils;

import com.csci318.microservice.feedback.Repositories.FeedbackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInitalizer implements CommandLineRunner {

    private final FeedbackRepository feedbackRepository;

    public DBInitalizer(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // if (feedbackRepository.count() == 0) {
        // }
    }
}
