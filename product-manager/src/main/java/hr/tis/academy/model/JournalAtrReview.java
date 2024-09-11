package hr.tis.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "JOURNALREVIEW", schema = "SIGHTSEEING")
public class JournalAtrReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

//    @ManyToOne
//    private Location attractionLocation;
    @Column(nullable = false)
    @Size(min = 1, max = 100)
    private String attractionLocationName;

    @Column(nullable = false)
    @Size(min = 1, max = 100)
    private String attractionName;

    @Column(nullable = false)
    @Size(min = 1, max = 100)
    private String attractionComment;

    @Column(nullable = false)
    private Date attractionDate;

    @ManyToOne
    private Journal journalAtrReviewsEntry;

    public JournalAtrReview() {
    }

    public JournalAtrReview(Long journalId, String attractionLocationName, String attractionName, String attractionComment, Date attractionDate, Journal journalAtrReviewsEntry) {
        this.journalId = journalId;
        this.attractionLocationName = attractionLocationName;
        this.attractionName = attractionName;
        this.attractionComment = attractionComment;
        this.attractionDate = attractionDate;
        this.journalAtrReviewsEntry = journalAtrReviewsEntry;
    }

    public JournalAtrReview(String attractionLocationName, String attractionName, String attractionComment, Date attractionDate, Journal journalAtrReviewsEntry) {
        this.attractionLocationName = attractionLocationName;
        this.attractionName = attractionName;
        this.attractionComment = attractionComment;
        this.attractionDate = attractionDate;
        this.journalAtrReviewsEntry = journalAtrReviewsEntry;
    }

    public JournalAtrReview(String attractionLocationName, String attractionName, String attractionComment, Date attractionDate) {
        this.attractionLocationName = attractionLocationName;
        this.attractionName = attractionName;
        this.attractionComment = attractionComment;
        this.attractionDate = attractionDate;
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public @Size(min = 1, max = 100) String getAttractionLocationName() {
        return attractionLocationName;
    }

    public void setAttractionLocationName(@Size(min = 1, max = 100) String attractionLocationName) {
        this.attractionLocationName = attractionLocationName;
    }

    public @Size(min = 1, max = 100) String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(@Size(min = 1, max = 100) String attractionName) {
        this.attractionName = attractionName;
    }

    public @Size(min = 1, max = 100) String getAttractionComment() {
        return attractionComment;
    }

    public void setAttractionComment(@Size(min = 1, max = 100) String attractionComment) {
        this.attractionComment = attractionComment;
    }

    public Date getAttractionDate() {
        return attractionDate;
    }

    public void setAttractionDate(Date attractionDate) {
        this.attractionDate = attractionDate;
    }

    public Journal getJournalAtrReviewsEntry() {
        return journalAtrReviewsEntry;
    }

    public void setJournalAtrReviewsEntry(Journal journalAtrReviewsEntry) {
        this.journalAtrReviewsEntry = journalAtrReviewsEntry;
    }
}
