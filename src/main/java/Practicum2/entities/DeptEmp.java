package Practicum2.entities;

import Practicum2.entities.id.DeptEmpId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "DeptEmp.findByEmpNoToDate",
                query="SELECT de.departments.deptNo FROM DeptEmp de " +
                        "WHERE de.employees.empNo = :empNo AND de.toDate = :toDate"),
        @NamedQuery(name = "DeptEmp.updateById",
                query="UPDATE DeptEmp de SET de.toDate = :curDate " +
                        "WHERE de.employees.empNo = :empNo AND de.departments.deptNo = :deptNo")
})
@IdClass(DeptEmpId.class)
@Table(name = "dept_emp")
public class DeptEmp {
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;
    @Id
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Departments departments;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    public DeptEmp() {}
    public DeptEmp(Employees employees, Departments departments,
                   LocalDate fromDate, LocalDate toDate) {
        this.employees = employees;
        this.departments = departments;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
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
