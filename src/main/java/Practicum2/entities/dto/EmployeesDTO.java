package Practicum2.entities.dto;

import java.time.LocalDate;

/**
 * DTO class used to transfer employee details within the system.
 * This class is used to encapsulate the employee number, first name, last
 * name and hire date for transfer between layers of the application.
 */
public class EmployeesDTO {

    /**
     * The employee number of an employee
     */
    private int empNo;

    /**
     * The first name of an employee
     */
    private String firstName;

    /**
     * The last name of an employee
     */
    private String lastName;

    /**
     * The hire date of an employee
     */
    private LocalDate hireDate;

    /**
     * Default constructor for the EmployeesDTO class.
     */
    public EmployeesDTO() {}

    /**
     * Retrieves the employee number of this employee.
     *
     * @return the employee number of the current employee
     */
    public int getEmpNo() {
        return empNo;
    }

    /**
     * Retrieves the first name of the employee.
     *
     * @return the first name of the employee
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name of the employee.
     *
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retrieves the hire date of the employee.
     *
     * @return the hire date of the employee as a LocalDate
     */
    public LocalDate getHireDate() {
        return hireDate;
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
     * Sets the first name of the employee.
     *
     * @param firstName the first name to be set for the employee
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the last name to be set for the employee
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the hire date for the employee.
     *
     * @param hireDate the hire date to be set for the employee as a LocalDate
     */
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}
