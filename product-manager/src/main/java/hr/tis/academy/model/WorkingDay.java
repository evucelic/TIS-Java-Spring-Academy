package hr.tis.academy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "WORKING_DAY", schema = "PRODUCT_MANAGER")
public class WorkingDay {

    @EmbeddedId
    private WorkingDayId id;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public WorkingDay() {
    }

    public WorkingDay(WorkingDayId id, String startTime, String endTime, Store store) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.store = store;
    }

    public WorkingDayId getId() {
        return id;
    }

    public void setId(WorkingDayId id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
