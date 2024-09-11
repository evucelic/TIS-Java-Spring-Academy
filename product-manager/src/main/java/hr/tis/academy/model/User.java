package hr.tis.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS", schema = "SIGHTSEEING")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    @Email(message = "Email is not valid")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "USER_FAVORITES", schema = "SIGHTSEEING",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ATTRACTION_ID")
    )
    private List<Attraction> attractionsUser = new ArrayList<>();

    @OneToMany(mappedBy = "userReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Review> userReviews;

    @OneToMany(mappedBy = "journalEntry", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Journal> journals;

    public User() {
    }

    public User(Long userId, String name, String email, List<Attraction> attractionsUser, List<Review> userReviews, List<Journal> journals) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.attractionsUser = attractionsUser;
        this.userReviews = userReviews;
        this.journals = journals;
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

    public List<Journal> getJournals() {
        return journals;
    }

    public void setJournals(List<Journal> journals) {
        this.journals = journals;
    }
}
