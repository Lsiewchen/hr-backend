package Practicum2.entities;

import jakarta.persistence.*;

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
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @OneToMany(mappedBy = "employees")
    private List<Salaries> salariesList;

    @OneToMany(mappedBy = "employees")
    private List<Titles> titlesList;

    @OneToMany(mappedBy = "employees")
    private List<DeptEmp> deptEmpsList;

    @OneToMany(mappedBy = "employees")
    private List<DeptManager> deptManagersList;


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

    public List<Salaries> getSalariesList() {
        return salariesList;
    }

    public void setSalariesList(List<Salaries> salariesList) {
        this.salariesList = salariesList;
    }

    public List<Titles> getTitlesList() {
        return titlesList;
    }

    public void setTitlesList(List<Titles> titlesList) {
        this.titlesList = titlesList;
    }

    public List<DeptEmp> getDeptEmpsList() {
        return deptEmpsList;
    }

    public void setDeptEmpsList(List<DeptEmp> deptEmpsList) {
        this.deptEmpsList = deptEmpsList;
    }

    public List<DeptManager> getDeptManagersList() {
        return deptManagersList;
    }

    public void setDeptManagersList(List<DeptManager> deptManagersList) {
        this.deptManagersList = deptManagersList;
    }
}

