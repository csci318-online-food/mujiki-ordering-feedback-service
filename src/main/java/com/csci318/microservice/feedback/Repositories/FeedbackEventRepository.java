package com.csci318.microservice.feedback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.csci318.microservice.feedback.Domain.Events.FeedbackCreatedEvent;

import java.util.UUID;

@Repository
public interface FeedbackEventRepository extends JpaRepository<FeedbackCreatedEvent, UUID> {

}
