package Practicum2.entities;

import Practicum2.entities.id.SalariesId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 'Salaries' entity that maps to the "salaries" table in the database.
 * This entity includes the {@code Employees} entity, salary, and the start and
 * end dates of when each salary was drawn.
 * This entity is mapped to the {@code Employees} entity in a one-to-many
 * relationship.
 *
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Salaries.findByEmpNoToDate",
                query="SELECT s.salary FROM Salaries s " +
                        "WHERE s.employees.empNo = :empNo AND s.toDate = :toDate"),
        @NamedQuery(name = "Salaries.updateById",
                query="UPDATE Salaries s SET s.toDate = :curDate " +
                        "WHERE s.employees.empNo = :empNo AND s.toDate = :toDate")
})
@IdClass(SalariesId.class)
@Table(name="salaries")
public class Salaries {

    /**
     * Represents the association between the current {@code Salaries} entity
     * and the {@code Employees} entity.
     * This object establishes a many-to-one relationship between the {@code
     * Salaries} and {@code Employees} entities.
     * The employee number from this {@code Employees} entity acts as part
     * of the composite primary key in the "titles" table and maps to the
     * "emp_no" column.
     */
    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private Employees employees;

    /**
     * Represents the salary amount associated with an employee for a specific
     * period.
     * This field maps to the "salary" column in the "salaries" table.
     */
    @Column(name = "salary")
    private BigDecimal salary;

    /**
     * Starting date of a salary period for an employee.
     * This field acts as part of the composite primary key in the "salaries"
     * table and maps to the "from_date" column.
     */
    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    /**
     * Ending date of a salary period for an employee.
     * This field maps to the "to_date" column in the "salaries" table.
     */
    @Column(name = "to_date")
    private LocalDate toDate;

    /**
     * Default constructor for the {@code Salaries} entity.
     */
    public Salaries() {}

    /**
     * Constructs a new {@code Salaries} instance with the specified employee,
     * start and end dates, and salary amount.
     *
     * @param employees the {@code Employees} entity associated with this salary
     *                  record
     * @param fromDate the starting date of the salary period
     * @param salary the salary amount for the specified period
     * @param toDate the ending date of the salary period
     */
    public Salaries(Employees employees, LocalDate fromDate, BigDecimal salary,
                    LocalDate toDate) {
        this.employees = employees;
        this.fromDate = fromDate;
        this.salary = salary;
        this.toDate = toDate;
    }

    /**
     * Retrieves the associated {@code Employees} entity for the current
     * {@code Salaries} instance.
     *
     * @return the {@code Employees} entity associated with this
     * {@code Salaries} instance
     */
    public Employees getEmployees() {
        return employees;
    }

    /**
     * Sets the {@code Employees} entity associated with the current
     * {@code Salaries} instance.
     *
     * @param employees the {@code Employees} entity to be associated with
     *                  this {@code Salaries} instance
     */
    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    /**
     * Retrieves the starting date of the salary period for the current
     * {@code Salaries} instance.
     *
     * @return the starting date of the salary period as a {@code LocalDate}
     */
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Sets the starting date of the salary period for this {@code Salaries}
     * instance.
     *
     * @param fromDate the starting date of the salary period as a
     *                  {@code LocalDate}
     */
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Retrieves the salary amount associated with the current {@code Salaries}
     * instance.
     *
     * @return the salary amount as a {@code BigDecimal}
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Sets the salary amount for the current {@code Salaries} instance.
     *
     * @param salary the salary amount to set, represented as a
     *               {@code BigDecimal}
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Retrieves the ending date of the salary period for the current
     * {@code Salaries} instance.
     *
     * @return the ending date of the salary period as a {@code LocalDate}
     */
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Sets the ending date of the salary period for this {@code Salaries}
     * instance.
     *
     * @param toDate the ending date of the salary period as a {@code LocalDate}
     */
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
