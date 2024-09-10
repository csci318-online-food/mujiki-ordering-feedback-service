package com.csci318.microservice.feedback.Services;

import com.csci318.microservice.feedback.DTOs.FeedbackDTORequest;
import com.csci318.microservice.feedback.DTOs.FeedbackDTOResponse;

public interface FeedbackService {

    FeedbackDTOResponse createFeedback(FeedbackDTORequest feedbackDTORequest);
}
