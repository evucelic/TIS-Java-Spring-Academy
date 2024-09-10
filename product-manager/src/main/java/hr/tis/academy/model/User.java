package hr.tis.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "USERS", schema = "SIGHTSEEING")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column(unique = true)
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @ManyToMany
    private List<Attraction> attractionsUser;

    @OneToMany(mappedBy = "userReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Review> userReviews;

    public User() {
    }

    public User(Long userId, String name, String email, List<Attraction> attractionsUser, List<Review> userReviews) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.attractionsUser = attractionsUser;
        this.userReviews = userReviews;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Attraction> getAttractionsUser() {
        return attractionsUser;
    }

    public void setAttractionsUser(List<Attraction> attractionsUser) {
        this.attractionsUser = attractionsUser;
    }

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }
}
