package Practicum2.entities;

import Practicum2.entities.id.DeptEmpId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

/**
 * 'DeptEmp' entity that maps to the "dept_emp" table in the database.
 * This entity describes the relationship between the Departments and Employees
 * entities, where it details the employees of each department and their start
 * and end date of employment with the department.
 * This entity includes the {@code Employees} and {@code Department} entities,
 * and the from and to date of the employee's employment with the department.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "DeptEmp.findByEmpNoToDate",
                query="SELECT de.departments.deptNo FROM DeptEmp de " +
                        "WHERE de.employees.empNo = :empNo AND de.toDate = :toDate"),
        @NamedQuery(name = "DeptEmp.updateById",
                query="UPDATE DeptEmp de SET de.toDate = :promotionDate " +
                        "WHERE de.employees.empNo = :empNo AND de.departments.deptNo = :deptNo")
})
@IdClass(DeptEmpId.class)
@Table(name = "dept_emp")
public class DeptEmp {

    /**
     * Represents the association between the current department-employee
     * relationship and the {@code Employees} entity.
     * This object establishes a many-to-one relationship between the {@code
     * DeptEmp} and {@code Employees} entities.
     * This field acts as part of the composite primary key in the
     * "dept_emp" table
     */
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    /**
     * Represents the association between the current department-employee
     * relationship and the {@code Departments} entity.
     * This object establishes a many-to-one relationship between the
     * {@code DeptEmp} and {@code Departments} entities.
     * This field acts as part of the composite primary key in the "dept_emp"
     * table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private Departments departments;

    /**
     * Specifies the start date of an employee's employment within a department.
     * This field maps to the "from_date" column in the "dept_emp" table.
     */
    @Column(name = "from_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;

    /**
     * Specifies the end date of an employee's employment within a department.
     * This field maps to the "to_date" column in the "dept_emp" table.
     */
    @Column(name = "to_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;

    /**
     * Default constructor for the {@code DeptEmp} entity.
     */
    public DeptEmp() {}

    /**
     * Constructs a new instance of the {@code DeptEmp} entity with the specified
     * {@code Employees} and {@code Departments} entities, as well as the
     * employment start and end dates.
     *
     * @param employees the {@code Employees} entity associated with the
     *                  department
     * @param departments the {@code Departments} entity associated with the
     *                    employee
     * @param fromDate the start date of the employee's employment in the department
     * @param toDate the end date of the employee's employment in the department
     */
    public DeptEmp(Employees employees, Departments departments,
                   LocalDate fromDate, LocalDate toDate) {
        this.employees = employees;
        this.departments = departments;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Retrieves the {@code Employees} entity associated with the current
     * {@code DeptEmp} instance.
     *
     * @return the {@code Employees} entity associated with the department
     */
    public Employees getEmployees() {
        return employees;
    }

    /**
     * Sets the {@code Employees} entity associated with the current
     * {@code DeptEmp} instance.
     *
     * @param employees the {@code Employees} entity representing the employee
     *                  linked to a department
     */
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    /**
     * Retrieves the {@code Departments} entity associated with the current
     * {@code DeptEmp} instance.
     *
     * @return the {@code Departments} entity representing the department
     *         associated with the employee
     */
    public Departments getDepartments() {
        return departments;
    }

    /**
     * Sets the {@code Departments} entity associated with the current
     * {@code DeptEmp} instance.
     *
     * @param departments the {@code Departments} entity representing the
     *                    department associated with the employee
     */
    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    /**
     * Retrieves the start date of an employee's employment within a department.
     *
     * @return the start date of the employee's employment in the department
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the start date of an employee's employment within a department.
     *
     * @param fromDate the start date of the employee's employment in the
     *                 department
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the end date of an employee's employment within a department.
     *
     * @return the end date of the employee's employment in the department
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the end date of an employee's employment within a department.
     *
     * @param toDate the end date of the employee's employment in the department
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
