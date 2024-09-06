package com.csci318.microservice.feedback.Services.Impl;

import com.csci318.microservice.feedback.Repositories.FeedbackRepository;
import com.csci318.microservice.feedback.Services.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final RestTemplate restTemplate;
    private final FeedbackRepository feedbackRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Value("${address.url.service}")
    private String ADDRESS_URL;

    public FeedbackServiceImpl(RestTemplate restTemplate, FeedbackRepository feedbackRepository, ApplicationEventPublisher eventPublisher) {
        this.restTemplate = restTemplate;
        this.feedbackRepository = feedbackRepository;
        this.eventPublisher = eventPublisher;
    }
}
