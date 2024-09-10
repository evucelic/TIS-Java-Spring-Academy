package hr.tis.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "REVIEWS", schema = "SIGHTSEEING")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private Timestamp reviewDate;


    @Column(nullable = false)
    @DecimalMin(value = "0.0")
    @DecimalMax(value= "5.0")
    private BigDecimal reviewScore;

    @Column
    @Size(min = 50, max = 500)
    private String reviewText;

    @ManyToOne
    private Attraction attractionReview;

    @ManyToOne
    private User userReview;


    public Review() {
    }

    public Review(Long reviewId, Timestamp reviewDate, BigDecimal reviewScore, String reviewText, Attraction attractionReview, User userReview) {
        this.reviewId = reviewId;
        this.reviewDate = reviewDate;
        this.reviewScore = reviewScore;
        this.reviewText = reviewText;
        this.attractionReview = attractionReview;
        this.userReview = userReview;
    }

    public Long getReviewId() {
        return reviewId;
    }


    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    public @DecimalMin(value = "0.0") @DecimalMax(value = "5.0") BigDecimal getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(@DecimalMin(value = "0.0") @DecimalMax(value = "5.0") BigDecimal reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Attraction getAttractionReview() {
        return attractionReview;
    }

    public void setAttractionReview(Attraction attractionReview) {
        this.attractionReview = attractionReview;
    }

    public User getUserReview() {
        return userReview;
    }

    public void setUserReview(User userReview) {
        this.userReview = userReview;
    }
}
