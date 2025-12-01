package Practicum2.entities;

import Practicum2.entities.id.DeptManagerId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NamedQuery(name = "DeptManager.updateById",
        query="UPDATE DeptManager dm SET dm.toDate = :toDate " +
                "WHERE dm.departments.deptNo = :deptNo AND dm.employees.empNo = :empNo")
@IdClass(DeptManagerId.class)
@Table(name = "dept_manager")
public class DeptManager {
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Departments departments;
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    public DeptManager() {}
    public DeptManager(Departments departments, Employees employees,
                       LocalDate fromDate, LocalDate toDate) {
        this.departments = departments;
        this.employees = employees;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

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
