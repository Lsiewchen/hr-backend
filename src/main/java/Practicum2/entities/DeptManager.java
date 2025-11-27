package Practicum2.entities;

import Practicum2.entities.id.DeptManagerId;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@IdClass(DeptManagerId.class)
@Table(name = "dept_manager")
public class DeptManager {
    @Id
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Departments departments;
    @Id
    @ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    public DeptManager() {}

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
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

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
