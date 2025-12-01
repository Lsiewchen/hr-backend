package Practicum2.entities.dto;

import java.time.LocalDate;

public class EmployeesDTO {
    private int empNo;
    private String firstName;
    private String lastName;
    private LocalDate hireDate;

    public EmployeesDTO() {}
    public EmployeesDTO(int empNo, String firstName, String lastName, LocalDate hireDate) {
        this.empNo = empNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
    }

    public int getEmpNo() {
        return empNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }
}
