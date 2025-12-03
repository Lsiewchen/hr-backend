package Practicum2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * 'Departments' entity that maps to the "departments" table in the database.
 * This entity includes the department number and name, and is mapped to the
 * {@code DeptEmp} and {@code DeptManager} entities in a one-to-many
 * relationship.
 *
 */
@Entity
@NamedQuery(name="Departments.findAll", query="SELECT d FROM Departments d")
@Table(name = "departments")
public class Departments {

    /**
     * The department number of a department, mapped to the "dept_no" column
     * in the database. Also used as the primary key in the "departments" table.
     */
    @Id
    @Size(max = 4)
    @Column(name = "dept_no")
    private String deptNo;

    /**
     * The name of the department, mapped to the "dept_name" column in the
     * database
     */
    @Size(max = 40)
    @Column(name = "dept_name")
    private String deptName;

    /**
     * List of {@code DeptEmp} entities associated with a department.
     * This object establishes a one-to-many relationship between the
     * {@code Departments} entity and the {@code DeptEmp} entity.
     */
    @OneToMany(mappedBy = "departments")
    @JsonIgnore
    private List<DeptEmp> deptEmp;

    /**
     * List of {@code DeptManager} entities associated with a department.
     * This object establishes a one-to-many relationship between the
     * {@code Departments} entity and the {@code DeptManager} entity.
     */
    @OneToMany(mappedBy = "departments")
    @JsonIgnore
    private List<DeptManager> deptManager;

    /**
     * Default constructor for the {@code Departments} entity.
     */
    public Departments() {}

    /**
     * Retrieves the department number of the department entity.
     *
     * @return the department number as a String
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * Sets the department number for the department entity.
     *
     * @param deptNo the department number to be set, represented as a String
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * Retrieves the name of the department.
     *
     * @return the name of the department as a String
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * Sets the name of the department.
     *
     * @param deptName the name of the department to be set, represented
     *                 as a String
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * Retrieves the list of {@code DeptEmp} entities associated with
     * a department.
     *
     * @return a list of {@code DeptEmp} objects representing the employees
     * in the department
     */
    public List<DeptEmp> getDeptEmp() {
        return deptEmp;
    }

    /**
     * Sets the list of {@code DeptEmp} entities associated with the department.
     *
     * @param deptEmp the list of {@code DeptEmp} objects to be
     *                associated with the department
     */
    public void setDeptEmp(List<DeptEmp> deptEmp) {
        this.deptEmp = deptEmp;
    }

    /**
     * Retrieves the list of {@code DeptManager} entities associated with the
     * department.
     *
     * @return a list of {@code DeptManager} objects representing the managers
     * of the department
     */
    public List<DeptManager> getDeptManager() {
        return deptManager;
    }

    /**
     * Sets the list of {@code DeptManager} entities associated with the
     * department.
     *
     * @param deptManager the list of {@code DeptManager} objects to be
     *                    associated with the department
     */
    public void setDeptManager(List<DeptManager> deptManager) {
        this.deptManager = deptManager;
    }
}
