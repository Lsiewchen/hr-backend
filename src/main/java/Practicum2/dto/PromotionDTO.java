package Practicum2.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO class used to transfer promotion details within the system.
 * This class is used to encapsulate the employee number, salary, title
 * and department number of the employee to be promoted for transfer between
 * layers of the application.
 */
public class PromotionDTO {

    /**
     * The employee number of an employee,
     */
    private int empNo;

    /**
     * Represents the salary amount to replace the employee's current salary
     * drawn if any.
     */
    private BigDecimal salary;

    /**
     * Represents the title to replace the employee's current title if any.
     */
    private String title;

    /**
     * Represents the department number of the new department to replace the
     * employee's current department if any.
     */
    private String deptNo;

    /**
     * Represents the date the employee is to be promoted.
     */
    private LocalDate promotionDate;

    /**
     * Default constructor for the PromotionDTO class.
     */
    public PromotionDTO() {}

    /**
     * Retrieves the employee number of this employee.
     *
     * @return the employee number of the current employee
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * Retrieves the new salary of this employee.
     *
     * @return the new salary of the employee
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * Retrieves the new title of this employee.
     *
     * @return the new title of the employee
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the department number of the new department of this
     * employee.
     *
     * @return the department number of the new department
     */
    public String getDeptNo() {
        return deptNo;
    }

    /**
     * Retrieves the promotion date of this employee.
     *
     * @return the promotion date of the employee
     */
    public LocalDate getPromotionDate() {
        return promotionDate;
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
     * Sets the salary for this employee.
     *
     * @param salary the new salary to be set for the employee
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * Sets the title for this employee.
     *
     * @param title the new title to be set for the employee
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the department number for this employee.
     *
     * @param deptNo the new department number to be set for the employee
     */
    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * Sets the promotion date for this employee.
     *
     * @param promotionDate the promotion date for the employee
     */
    public void setPromotionDate(LocalDate promotionDate) {
        this.promotionDate = promotionDate;
    }
}
