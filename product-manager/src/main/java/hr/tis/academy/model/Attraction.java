package hr.tis.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ATTRACTIONS", schema = "SIGHTSEEING")
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attractionId;

    @Column(nullable = false, unique = true)
    private String attractionName;

    @Column
    @Size(min = 20, max = 300)
    private String attractionDescription;

    @Enumerated(EnumType.STRING)
    @Column
    private AttractionType attractionType;

    @Column(nullable = false, unique = true)
    private String attractionURLName;

    @OneToMany(mappedBy = "attractionPicture", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Picture> pictures;

    @OneToMany(mappedBy = "attractionReview", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(mappedBy = "attractionsUser")
    private List<User> favoritedByUsers = new ArrayList<>();

    @ManyToOne
    private Location attractionLocation;

    public Attraction() {
    }

    public Attraction(Long attractionId, String attractionName, String attractionDescription, AttractionType attractionType, List<Picture> pictures, List<Review> reviews, List<User> favoritedByUsers, Location attractionLocation) {
        this.attractionId = attractionId;
        this.attractionName = attractionName;
        this.attractionDescription = attractionDescription;
        this.attractionType = attractionType;
        this.pictures = pictures;
        this.reviews = reviews;
        this.favoritedByUsers = favoritedByUsers;
        this.attractionLocation = attractionLocation;
    }



    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getAttractionDescription() {
        return attractionDescription;
    }

    public void setAttractionDescription(String attractionDescription) {
        this.attractionDescription = attractionDescription;
    }

    public AttractionType getAttractionType() {
        return attractionType;
    }

    public void setAttractionType(AttractionType attractionType) {
        this.attractionType = attractionType;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<User> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    public void setFavoritedByUsers(List<User> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }

    public Location getAttractionLocation() {
        return attractionLocation;
    }

    public void setAttractionLocation(Location attractionLocation) {
        this.attractionLocation = attractionLocation;
    }

    public String getAttractionURLName() {
        return attractionURLName;
    }

    public void setAttractionURLName(String attractionURLName) {
        this.attractionURLName = attractionURLName;
    }
}
