package com.csci318.microservice.feedback.Controllers;

import com.csci318.microservice.feedback.Services.FeedbackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;


    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}
