package Practicum2.entities;

import Practicum2.entities.id.SalariesId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Salaries.findByEmpNoToDate",
                query="SELECT s.salary FROM Salaries s " +
                        "WHERE s.employees.empNo = :empNo AND s.toDate = :toDate"),
        @NamedQuery(name = "Salaries.updateById",
                query="UPDATE Salaries s SET s.toDate = :curDate " +
                        "WHERE s.employees.empNo = :empNo AND s.toDate = :toDate")
})
@IdClass(SalariesId.class)
@Table(name="salaries")
public class Salaries {
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;
    @Column(name = "salary")
    private BigDecimal salary;
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    public Salaries() {}
    public Salaries(Employees employees, LocalDate fromDate, BigDecimal salary,
                    LocalDate toDate) {
        this.employees = employees;
        this.fromDate = fromDate;
        this.salary = salary;
        this.toDate = toDate;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
