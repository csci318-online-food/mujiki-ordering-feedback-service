package com.csci318.microservice.feedback.Domain.Entities;

import com.csci318.microservice.feedback.Domain.Relations.Restaurant;
import com.csci318.microservice.feedback.Domain.Relations.User;
import com.csci318.microservice.feedback.Utils.Annotations.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.UUID;

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
    @ManyToOne(targetEntity = User.class)
    private UUID userId;

    @Column(name = "restaurant_id")
    @ManyToOne(targetEntity = Restaurant.class)
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
