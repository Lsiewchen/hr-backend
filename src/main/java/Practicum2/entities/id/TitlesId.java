package Practicum2.entities.id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The TitlesId class is a composite primary key for the {@code Titles}
 * entity that maps the {@code title}, {@code fromDate} and {@code Employees}
 * entity of the {@code Titles} entity as keys.
 */
public class TitlesId implements Serializable {

    /**
     * Title of the employee in the {@code Titles} entity.
     */
    private String title;

    /**
     * Starting date of the title held by an employee.
     */
    private LocalDate fromDate;

    /**
     * The {@code Employees} entity to be mapped as a composite key for the
     * {@code Salaries} entity.
     */
    private int employees;

    /**
     * Default constructor for the TitlesId class.
     */
    public TitlesId() {}

    /**
     * Constructs a new TitlesId object with the specified title,
     * starting date when title was held and {@code Employees} entity.
     * @param title     title of the employee
     * @param fromDate  the starting date when the title is held
     *                  to be included in the composite key
     * @param employees the {@code Employees} entity to be included in the
     *                  composite key
     */
    public TitlesId(String title, LocalDate fromDate, int employees) {
        this.title = title;
        this.fromDate = fromDate;
        this.employees = employees;
    }

    /**
     * Retrieves the title associated with this entity.
     *
     * @return the title of the entity
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the entity.
     *
     * @param title the title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the starting date associated with the {@code Titles} entity.
     *
     * @return the starting date when the title was held
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the starting date of the title held by an employee in the
     * {@code Titles} entity.
     *
     * @param fromDate the starting date when the title was held
     */
    public void setFromDate(LocalDate fromDate) {
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
     * Compares this object to the specified object to determine if they are
     * equal.
     *
     * @param titles the object to compare with this instance
     * @return true if the specified object is equal to this instance;
     * false otherwise
     */
    @Override
    public boolean equals(Object titles) {
        if (this == titles) {
            return true;
        }
        if (titles == null || getClass() != titles.getClass()) {
            return false;
        }
        TitlesId inTitles = (TitlesId) titles;
        return Objects.equals(title, inTitles.title) &&
                Objects.equals(fromDate, inTitles.fromDate) &&
                Objects.equals(employees, inTitles.employees);
    }

    /**
     * Returns the hash code value for this object computed based on the
     * values of the {@code title}, {@code fromDate} and {@code employees}
     * fields.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, fromDate, employees);
    }
}
