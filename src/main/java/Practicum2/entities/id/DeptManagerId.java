package Practicum2.entities.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * The DeptManagerId class is a composite primary key for the {@code
 * DeptManager}
 * entity that maps the {@code Employees} and {@code Departments} entities to
 * as keys.
 */
public class DeptManagerId implements Serializable {

    /**
     * The {@code Departments} entity to be mapped as a composite key for the
     * {@code DeptManager} entity
     */
    private String departments;

    /**
     * The {@code Employees} entity to be mapped as a composite key for the
     * {@code DeptManager} entity
     */
    private int employees;

    /**
     * Default constructor for the DeptManagerId class.
     */
    public DeptManagerId() {}

    /**
     * Constructs a new DeptEmpId object with the specified employee ID and department ID.
     *
     * @param employees the {@code Employees} entity to be included in the
     *                  composite key
     * @param departments the {@code Departments} entity to be included in the
     *                    composite key
     */
    public DeptManagerId(String departments, int employees) {
        this.departments = departments;
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
     * Compares this object to the specified object to determine if they are
     * equal.
     *
     * @param deptManager the object to compare with this instance
     * @return true if the specified object is equal to this instance;
     * false otherwise
     */
    @Override
    public boolean equals(Object deptManager) {
        if (this == deptManager) {
            return true;
        }
        if (deptManager == null || getClass() != deptManager.getClass()) {
            return false;
        }
        DeptManagerId inDeptManager = (DeptManagerId) deptManager;
        return Objects.equals(departments, inDeptManager.departments) &&
                Objects.equals(employees, inDeptManager.employees);
    }

    /**
     * Returns the hash code value for this object computed based on the
     * values of the {@code departments} and {@code employees} fields.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(departments, employees);
    }
}
