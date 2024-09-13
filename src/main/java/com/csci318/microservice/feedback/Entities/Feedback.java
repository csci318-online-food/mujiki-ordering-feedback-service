package com.csci318.microservice.feedback.Entities;

import com.csci318.microservice.feedback.Entities.Event.FeedbackCreatedEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.logging.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback extends AbstractAggregateRoot<Feedback> {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "create_at")
    private Timestamp createAt;

    @Column(name = "modify_at")
    private Timestamp modifyAt;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "create_by")
    private String createBy;


}
