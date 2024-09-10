package com.csci318.microservice.feedback.Repositories;

import com.csci318.microservice.feedback.Entities.Event.FeedbackCreatedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedbackEventRepository extends JpaRepository<FeedbackCreatedEvent, UUID> {

}
