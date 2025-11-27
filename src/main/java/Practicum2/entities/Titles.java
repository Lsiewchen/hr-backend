package Practicum2.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="titles")
public class Titles {
    @Id
    @Column(name = "emp_no")
    private int empNo;
    @Column(name = "title")
    private String title;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employees employees;


    public Titles() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
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

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
}
