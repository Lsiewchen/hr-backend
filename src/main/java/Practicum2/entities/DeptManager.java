package Practicum2.entities;

import Practicum2.entities.id.DeptManagerId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * 'DeptManager' entity that maps to the "dept_manager" table in the database.
 * This entity describes the relationship between the Departments and Employees
 * entities, where it details the employees that are managers of each
 * department and their tenure as a manager with the department.
 *
 * This entity includes the {@code Employees} and {@code Department} entities,
 * and the from and to date of the employee's tenure as a manager with the
 * department.
 */
@Entity
@NamedQuery(name = "DeptManager.updateById",
        query="UPDATE DeptManager dm SET dm.toDate = :curDate " +
                "WHERE dm.employees.empNo = :empNo AND dm.toDate = :toDate")
@IdClass(DeptManagerId.class)
@Table(name = "dept_manager")
public class DeptManager {

    /**
     * Represents the association between the current department-managers
     * relationship and the {@code Departments} entity.
     * This object establishes a many-to-one relationship between the
     * {@code DeptManager} and {@code Departments} entities.
     * This field acts as part of the composite primary key in the
     * "dept_manager" table
     */
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Departments departments;

    /**
     * Represents the association between the current department-managers
     * relationship and the {@code Employees} entity.
     * This object establishes a many-to-one relationship between the {@code
     * DeptManager} and {@code Employees} entities.
     * This field acts as part of the composite primary key in the
     * "dept_manager" table
     */
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    /**
     * Specifies the start date of an employee's tenure as a manager of a
     * department.
     * This field maps to the "from_date" column in the "dept_manager" table.
     */
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * Specifies the end date of an employee's tenure as a manager of a
     * department.
     * This field maps to the "to_date" column in the "dept_manager" table.
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Default constructor for the {@code DeptManager} entity.
     */
    public DeptManager() {}

    /**
     * Constructs a {@code DeptManager} entity with the specified department,
     * employee, start date, and end date.
     *
     * @param departments the {@code Departments} entity associated with the
     *                    manager
     * @param employees   the {@code Employees} entity who is a manager
     *                    associated with the given department
     * @param fromDate    the start date of the employee's tenure as a department
     *                    manager
     * @param toDate      the end date of the employee's tenure as a department
     *                    manager
     */
    public DeptManager(Departments departments, Employees employees,
                       LocalDate fromDate, LocalDate toDate) {
        this.departments = departments;
        this.employees = employees;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Retrieves the {@code Departments} entity associated with the current
     * {@code DeptManager} instance.
     *
     * @return the {@code Departments} entity linked with the current department-manager
     * relationship, representing the department details.
     */
    public Departments getDepartments() {
        return departments;
    }

    /**
     * Sets the {@code Departments} entity associated with the current
     * {@code DeptManager} instance.
     *
     * @param departments the {@code Departments} entity representing the
     *                    department associated with the
     *                    department-manager relationship
     */
    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    /**
     * Retrieves the {@code Employees} entity associated with the current
     * {@code DeptManager} instance.
     *
     * @return the {@code Employees} entity linked with the
     * current department-manager relationship
     */
    public Employees getEmployees() {
        return employees;
    }

    /**
     * Sets the {@code Employees} entity associated with the current
     * {@code DeptManager} instance.
     *
     * @param employees the {@code Employees} entity representing the employee
     *                  associated with the department-manager relationship
     */
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    /**
     * Retrieves the start date of the employee's tenure as a manager
     * of a department.
     *
     * @return the start date of the employee's tenure as a department manager
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of the employee's tenure as a manager of a department.
     *
     * @param fromDate the start date of the employee's tenure as a department
     *                 manager
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the end date of the employee's tenure as a manager of a
     * department.
     *
     * @return the end date of the employee's tenure as a department manager
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of the employee's tenure as a manager of a department.
     *
     * @param toDate the end date of the employee's tenure as a department
     *               manager
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
