package hr.tis.academy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PICTURES", schema = "SIGHTSEEING")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @Column(nullable = false)
    private String pictureURL;

    @ManyToOne
    private Attraction attractionPicture;

    public Picture() {
    }

    public Picture(Long pictureId, String pictureURL, Attraction attractionPicture) {
        this.pictureId = pictureId;
        this.pictureURL = pictureURL;
        this.attractionPicture = attractionPicture;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Attraction getAttractionPicture() {
        return attractionPicture;
    }

    public void setAttractionPicture(Attraction attractionPicture) {
        this.attractionPicture = attractionPicture;
    }
}
