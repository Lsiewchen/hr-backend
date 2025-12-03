package Practicum2.entities.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * The DeptEmpId class is a composite primary key for the {@code DeptEmp}
 * entity that maps the {@code Employees} and {@code Departments} entities to
 * as keys.
 */
public class DeptEmpId implements Serializable {

    /**
     * The {@code Employees} entity to be mapped as a composite key for the
     * {@code DeptEmp} entity
     */
    private int employees;

    /**
     * The {@code Departments} entity to be mapped as a composite key for the
     * {@code DeptEmp} entity
     */
    private String departments;

    /**
     * Default constructor for the DeptEmpId class.
     */
    public DeptEmpId() {}

    /**
     * Constructs a new DeptEmpId object with the specified employee ID and department ID.
     *
     * @param employees the {@code Employees} entity to be included in the
     *                  composite key
     * @param departments the {@code Departments} entity to be included in the
     *                    composite key
     */
    public DeptEmpId(int employees, String departments) {
        this.employees = employees;
        this.departments = departments;
    }

    /**
     * Retrieves the {@code Employees} entity associated with this entity.
     *
     * @return the {@code Employees} entity
     */
    public int getEmployees() {
        return employees;
    }

    /**
     * Sets the {@code Employees} entity to be used as part of the composite key.
     *
     * @param employees the {@code Employees} entity to be set
     */
    public void setEmployees(int employees) {
        this.employees = employees;
    }

    /**
     * Retrieves the {@code Departments} entity associated with this entity.
     *
     * @return the {@code Departments} entity
     */
    public String getDepartments() {
        return departments;
    }

    /**
     * Sets the {@code Departments} entity to be used as part of the composite key.
     *
     * @param departments the {@code Departments} entity to be set
     */
    public void setDepartments(String departments) {
        this.departments = departments;
    }

    /**
     * Compares this object to the specified object to determine if they are
     * equal.
     *
     * @param deptEmp the object to compare with this instance
     * @return true if the specified object is equal to this instance;
     * false otherwise
     */
    @Override
    public boolean equals(Object deptEmp) {
        if (this == deptEmp) {
            return true;
        }
        if (deptEmp == null || getClass() != deptEmp.getClass()) {
            return false;
        }
        DeptEmpId inDeptEmp = (DeptEmpId) deptEmp;
        return Objects.equals(employees, inDeptEmp.employees) &&
                Objects.equals(departments, inDeptEmp.departments);
    }

    /**
     * Returns the hash code value for this object computed based on the
     * values of the {@code employees} and {@code departments} fields.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(employees, departments);
    }
}
