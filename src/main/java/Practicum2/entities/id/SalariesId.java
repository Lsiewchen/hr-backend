package Practicum2.entities.id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class SalariesId implements Serializable {
    private int employees;
    private LocalDate fromDate;

    public SalariesId() {}
    public SalariesId(int employees, LocalDate fromDate) {
        this.employees = employees;
        this.fromDate = fromDate;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(employees, fromDate);
    }
}
