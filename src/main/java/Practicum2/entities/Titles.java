package Practicum2.entities;

import Practicum2.entities.id.TitlesId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * 'Titles' entity that maps to the "titles" table in the database.
 * This entity includes the {@code Employees} entity, title, and the start and
 * end dates of when each title was held.
 * This entity is mapped to the {@code Employees} entity in a one-to-many
 * relationship.
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Titles.findByEmpNoToDate",
                query="SELECT t.title FROM Titles t " +
                        "WHERE t.employees.empNo = :empNo AND t.toDate = :toDate"),
        @NamedQuery(name = "Titles.updateById",
                query="UPDATE Titles t SET t.toDate = :curDate " +
                        "WHERE t.title = :oldTitle AND t.employees.empNo = :empNo AND t.toDate = :toDate")
})
@IdClass(TitlesId.class)
@Table(name="titles")
public class Titles {

    /**
     * Represents the title of an employee held during a specific period.
     * This field acts as part of the composite primary key in the "titles"
     * table and maps to the "title" column.
     */
    @Id
    @Size(max = 50)
    @Column(name = "title")
    private String title;

    /**
     * Starting date for a title held by an employee.
     * This field acts as part of the composite primary key in the "titles" table
     * and maps to the "from_date" column.
     */
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * Ending date for a title held by an employee.
     * This field maps to the "to_date" column in the "titles" table.
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Represents the association between the current {@code Titles} entity
     * and the {@code Employees} entity.
     * This object establishes a many-to-one relationship between the {@code
     * Titles} and {@code Employees} entities.
     * The employee number from this {@code Employees} entity acts as part
     * of the composite primary key in the "titles" table and maps to the
     * "emp_no" column.
     */
    @Id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    /**
     * Default constructor for the {@code Titles} entity.
     */
    public Titles() {}

    /**
     * Constructs a {@code Titles} entity with the specified title, start and
     * end dates and employee.
     *
     * @param title the title or position held by the employee
     * @param fromDate the starting date for the title held by the employee
     * @param toDate the ending date for the title held by the employee
     * @param employees the {@code Employees} entity associated with the title
     */
    public Titles(String title, LocalDate fromDate, LocalDate toDate,
                  Employees employees) {
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.employees = employees;
    }

    /**
     * Retrieves the title held by an employee.
     *
     * @return the title associated with this entity
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title associated with this entity.
     *
     * @param title the new title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the starting date for a title held by an employee.
     *
     * @return the start date for the title associated with this entity
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the starting date for a title held by an employee.
     *
     * @param fromDate the starting date to be set for the title
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the {@code Employees} entity associated with this
     * {@code Titles} instance.
     *
     * @return the {@code Employees} entity linked to this title
     */
    public Employees getEmployees() {
        return employees;
    }

    /**
     * Sets the {@code Employees} entity associated with the current {@code
     * Titles} instance.
     *
     * @param employees the {@code Employees} entity to be associated with
     *                  this {@code Titles} instance
     */
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    /**
     * Retrieves the ending date for a title held by an employee.
     *
     * @return the end date for the title associated with this entity
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the ending date for a title held by an employee.
     *
     * @param toDate the ending date to be set for the title
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
