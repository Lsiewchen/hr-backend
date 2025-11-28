package Practicum2.entities;

import Practicum2.entities.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="employees")
public class Employees {
    @Id
    @Column(name = "emp_no")
    private int empNo;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Size(max = 14)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 16)
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employees")
    private List<Salaries> salaries;
    @OneToMany(mappedBy = "employees")
    private List<Titles> titles;
    @OneToMany(mappedBy = "employees")
    private List<DeptEmp> deptEmp;
    @OneToMany(mappedBy = "employees")
    private List<DeptManager> deptManager;


    public Employees() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public List<Salaries> getSalaries() {
        return salaries;
    }

    public void setSalaries(List<Salaries> salaries) {
        this.salaries = salaries;
    }

    public List<Titles> getTitles() {
        return titles;
    }

    public void setTitles(List<Titles> titles) {
        this.titles = titles;
    }

    public List<DeptEmp> getDeptEmp() {
        return deptEmp;
    }

    public void setDeptEmp(List<DeptEmp> deptEmp) {
        this.deptEmp = deptEmp;
    }

    public List<DeptManager> getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(List<DeptManager> deptManager) {
        this.deptManager = deptManager;
    }
}

