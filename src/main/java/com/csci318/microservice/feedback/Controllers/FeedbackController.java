package com.csci318.microservice.feedback.Controllers;

import com.csci318.microservice.feedback.DTOs.FeedbackDTORequest;
import com.csci318.microservice.feedback.DTOs.FeedbackDTOResponse;
import com.csci318.microservice.feedback.Services.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.endpoint.base-url}/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;


    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/create")
    public ResponseEntity<FeedbackDTOResponse> createFeedback(@RequestBody FeedbackDTORequest feedbackDTORequest) {
        FeedbackDTOResponse responseDTO = feedbackService.createFeedback(feedbackDTORequest);
        return ResponseEntity.ok(responseDTO);
    }

}
