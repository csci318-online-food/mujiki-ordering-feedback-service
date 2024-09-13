package com.csci318.microservice.feedback.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csci318.microservice.feedback.Domain.Entities.Feedback;

import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    @Query("SELECT f FROM Feedback f WHERE f.restaurantId = :restaurantId")
    List<Feedback> findByRestaurantId(UUID restaurantId);
}
