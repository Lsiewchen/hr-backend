package Practicum2.entities;

import Practicum2.entities.id.TitlesId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Titles.findByEmpNoToDate",
                query="SELECT t.title FROM Titles t " +
                        "WHERE t.employees.empNo = :empNo AND t.toDate = :toDate"),
        @NamedQuery(name = "Titles.updateById",
                query="UPDATE Titles t SET t.toDate = :curDate " +
                        "WHERE t.title = :oldTitle AND t.employees.empNo = :empNo AND t.toDate = :toDate")
})
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
    @Column(name = "to_date")
    private LocalDate toDate;
    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    public Titles() {}
    public Titles(String title, LocalDate fromDate, LocalDate toDate,
                  Employees employees) {
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
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
