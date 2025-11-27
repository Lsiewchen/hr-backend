package Practicum2.entities;


import Practicum2.entities.id.SalariesId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@IdClass(SalariesId.class)
@Table(name="salaries")
public class Salaries {
    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "salary")
    private BigDecimal salary;
    @Column(name = "to_date")
    private LocalDate toDate;

    public Salaries() {}

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
