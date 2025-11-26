package Practicum2;


import java.math.BigDecimal;
import java.time.LocalDate;

//@Entity
//@Table(name="salaries")
public class Salaries {
    private int empNo;
    private BigDecimal salary;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Salaries() {}

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
