package Practicum2.entities.id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The SalariesId class is a composite primary key for the {@code Salaries}
 * entity that maps the {@code Employees} entity and {@code fromDate}
 * of the {@code Salaries} entity as keys.
 */
public class SalariesId implements Serializable {

    /**
     * The {@code Employees} entity to be mapped as a composite key for the
     * {@code Salaries} entity.
     */
    private int employees;

    /**
     * Starting date of a salary period for an employee.
     */
    private LocalDate fromDate;

    /**
     * Default constructor for the SalariesId class.
     */
    public SalariesId() {}

    /**
     * Constructs a new SalariesId object with the specified {@code Employees}
     * entity and starting salary drawn date.
     *
     * @param employees the {@code Employees} entity to be included in the
     *                  composite key
     * @param fromDate the starting date of the salary period
     *                 to be included in the composite key
     */
    public SalariesId(int employees, LocalDate fromDate) {
        this.employees = employees;
        this.fromDate = fromDate;
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
     * Retrieves the starting date of a salary period for an employee.
     *
     * @return the starting date of the salary period
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the starting date of a salary period for an employee.
     *
     * @param fromDate the starting date of the salary period to be set
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Compares this object to the specified object to determine if they are
     * equal.
     *
     * @param salaries the object to compare with this instance
     * @return true if the specified object is equal to this instance;
     * false otherwise
     */
    @Override
    public boolean equals(Object salaries) {
        if (this == salaries) {
            return true;
        }
        if (salaries == null || getClass() != salaries.getClass()) {
            return false;
        }
        SalariesId inSalaries = (SalariesId) salaries;
        return Objects.equals(employees, inSalaries.employees) &&
                Objects.equals(fromDate, inSalaries.fromDate);
    }

    /**
     * Returns the hash code value for this object computed based on the
     * values of the {@code employees} and {@code fromDate} fields.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(employees, fromDate);
    }
}
