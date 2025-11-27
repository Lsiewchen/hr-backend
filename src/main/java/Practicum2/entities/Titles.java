package Practicum2.entities;

import Practicum2.entities.id.TitlesId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@IdClass(TitlesId.class)
@Table(name="titles")
public class Titles {
    @Id
    @Size(max = 50)
    @Column(name = "title")
    private String title;
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Id
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    @Column(name = "to_date")
    private LocalDate toDate;

    public Titles() {}

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

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
