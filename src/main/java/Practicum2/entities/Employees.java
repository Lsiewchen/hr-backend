package Practicum2.entities;

import Practicum2.entities.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

/**
 * 'Employees' entity that maps to the "employees" table in the database.
 * This entity includes the employee number, first name, last name, gender,
 * and hire date.
 * This entity is mapped to the {@code Salaries}, {@code Titles}, {@code
 * DeptEmp} and {@code DeptManager} entities in a one-to-many relationship.
 *
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Employees.findFullRecordByEmpNo",
            query = "SELECT e FROM Employees e WHERE e.empNo = :empNo"),
    @NamedQuery(name = "Employees.findAllRecordByDept",
            query = "SELECT NEW Practicum2.dto.EmployeesDTO" +
                    "(e.empNo, e.firstName, e.lastName, e.hireDate) " +
                    "FROM Employees e JOIN e.deptEmp de " +
                    "WHERE de.departments.deptNo = :deptNo")
})
@Table(name="employees")
public class Employees {

    /**
     * The employee number of an employee, mapped to the "emp_no" column
     * in the database. Also used as the primary key in the "employee" table.
     */
    @Id
    @Column(name = "emp_no")
    private int empNo;

    /**
     * The birthdate of an employee, mapped to the "birth_date" column in the
     * "employees" database table.
     */
    @Column(name = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    /**
     * The first name of an employee, mapped to the "first_name" column in
     * the "employees" database table.
     */
    @Size(max = 14)
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of an employee, mapped to the "last_name" column in
     * the "employees" database table.
     */
    @Size(max = 16)
    @Column(name = "last_name")
    private String lastName;

    /**
     * The gender of an employee, mapped to the "gender" column in the
     * "employees" database table. Uses the Gender ENUM that accepts 'M' or 'F'
     * as values.
     */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * The hire date of an employee, mapped to the "hire_date" column in the
     * "employees" database table.
     */
    @Column(name = "hire_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    /**
     * List of salaries associated with an employee.
     * This object establishes a one-to-many relationship between the
     * {@code Employees} entity and the {@code Salaries} entity
     */
    @OneToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<Salaries> salaries;

    /**
     * List of titles associated with an employee.
     * This object establishes a one-to-many relationship between the
     * {@code Employees} entity and the {@code Titles} entity
     */
    @OneToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<Titles> titles;

    /**
     * List of {@code DeptEmp} entities associated with an employee.
     * This object establishes a one-to-many relationship between the
     * {@code Employees} entity and the {@code DeptEmp} entity.
     */
    @OneToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<DeptEmp> deptEmp;

    /**
     * List of {@code DeptManager} entities associated with an employee.
     * This object establishes a one-to-many relationship between the
     * {@code Employees} entity and the {@code DeptManager} entity.
     */
    @OneToMany(mappedBy = "employees", fetch = FetchType.EAGER)
    private List<DeptManager> deptManager;

    /**
     * Default constructor for the {@code Employees} entity.
     */
    public Employees() {}

    /**
     * Retrieves the employee number of this employee.
     *
     * @return the employee number of the current employee
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * Sets the employee number for this employee.
     *
     * @param empNo the employee number to be set
     */
    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    /**
     * Retrieves the birthdate of the employee.
     *
     * @return the birthdate of the employee as a LocalDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birthdate of the employee.
     *
     * @param birthDate the birthdate to set for the employee as a LocalDate
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Retrieves the first name of the employee.
     *
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName the first name to be set for the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the employee.
     *
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the last name to be set for the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the gender of the employee.
     *
     * @return the gender of the employee as a Gender ENUM
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the gender of the employee.
     *
     * @param gender the gender to set for the employee, as a Gender ENUM
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Retrieves the hire date of the employee.
     *
     * @return the hire date of the employee as a LocalDate
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Sets the hire date for the employee.
     *
     * @param hireDate the hire date to be set for the employee as a LocalDate
     */
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Retrieves the list of salaries associated with the employee.
     *
     * @return a list of Salaries objects associated with this employee
     */
    public List<Salaries> getSalaries() {
        return salaries;
    }

    /**
     * Sets the list of salaries associated with the employee.
     *
     * @param salaries the list of Salaries objects to be associated with
     *                 this employee
     */
    public void setSalaries(List<Salaries> salaries) {
        this.salaries = salaries;
    }

    /**
     * Retrieves the list of titles associated with the employee.
     *
     * @return a list of Titles objects associated with this employee
     */
    public List<Titles> getTitles() {
        return titles;
    }

    /**
     * Sets the list of titles associated with the employee.
     *
     * @param titles the list of Titles objects to be set for this employee
     */
    public void setTitles(List<Titles> titles) {
        this.titles = titles;
    }

    /**
     * Retrieves the list of {@code DeptEmp} entities associated with
     * the department of the current {@code Employees} entity.
     *
     * @return a list of DeptEmp objects representing the employees in the
     * department of the current {@code Employees} entity
     */
    public List<DeptEmp> getDeptEmp() {
        return deptEmp;
    }

    /**
     * Sets the list of {@code DeptEmp} entities associated with the employee.
     *
     * @param deptEmp the list of {@code DeptEmp} objects to be
     *                associated with the employee
     */
    public void setDeptEmp(List<DeptEmp> deptEmp) {
        this.deptEmp = deptEmp;
    }

    /**
     * Retrieves the list of {@code DeptManager} entities associated with
     * the current {@code Employees} entity.
     *
     * @return a list of {@code DeptManager} objects representing the
     * managers of departments associated with this employee
     */
    public List<DeptManager> getDeptManager() {
        return deptManager;
    }

    /**
     * Sets the list of {@code DeptManager} entities associated with the
     * employee.
     *
     * @param deptManager the list of {@code DeptManager} objects to be
     *                    associated with the employee
     */
    public void setDeptManager(List<DeptManager> deptManager) {
        this.deptManager = deptManager;
    }
}

