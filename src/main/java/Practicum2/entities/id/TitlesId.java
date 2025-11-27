package Practicum2.entities.id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TitlesId implements Serializable {
    private String title;
    private LocalDate fromDate;
    private int employees;

    public TitlesId() {}
    public TitlesId(String title, LocalDate fromDate, int employees) {
        this.title = title;
        this.fromDate = fromDate;
        this.employees = employees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object titles) {
        if (this == titles) {
            return true;
        }
        if (titles == null || getClass() != titles.getClass()) {
            return false;
        }
        TitlesId inTitles = (TitlesId) titles;
        return Objects.equals(title, inTitles.title) &&
                Objects.equals(fromDate, inTitles.fromDate) &&
                Objects.equals(employees, inTitles.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, fromDate, employees);
    }
}
