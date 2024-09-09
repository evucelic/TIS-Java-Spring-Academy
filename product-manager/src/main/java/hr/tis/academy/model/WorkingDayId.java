package hr.tis.academy.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WorkingDayId implements Serializable {

    private String dayOfWeek;
    private Long storeId;

    public WorkingDayId() {}

    public WorkingDayId(String dayOfWeek, Long storeId) {
        this.dayOfWeek = dayOfWeek;
        this.storeId = storeId;
    }

    // Getters, Setters, equals, and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDayId that = (WorkingDayId) o;
        return Objects.equals(dayOfWeek, that.dayOfWeek) &&
                Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeek, storeId);
    }
}
