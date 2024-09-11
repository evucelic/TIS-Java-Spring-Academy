package hr.tis.academy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "JOURNALS", schema = "SIGHTSEEING")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalId;

    @Column(nullable = false)
    private Date startDate;

    @Column
    private Date endDate;

    @Column(nullable = false)
    @Size(min = 20, max = 200)
    private String journalDescription;

    @ManyToOne
    private User journalEntry;

    @OneToMany(mappedBy = "journalAtrReviewsEntry", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<JournalAtrReview> journalsAtrReviews;


    public Journal(Date startDate, Date endDate, String journalDescription, User journalEntry, List<JournalAtrReview> journalsAtrReviews) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.journalDescription = journalDescription;
        this.journalEntry = journalEntry;
        this.journalsAtrReviews = journalsAtrReviews;
    }



    public Journal(){}




    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public @Size(min = 20, max = 200) String getJournalDescription() {
        return journalDescription;
    }

    public void setJournalDescription(@Size(min = 20, max = 200) String journalDescription) {
        this.journalDescription = journalDescription;
    }

    public User getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(User journalEntry) {
        this.journalEntry = journalEntry;
    }

    public List<JournalAtrReview> getJournalsAtrReviews() {
        return journalsAtrReviews;
    }

    public void setJournalsAtrReviews(List<JournalAtrReview> journalsAtrReviews) {
        this.journalsAtrReviews = journalsAtrReviews;
    }

}
