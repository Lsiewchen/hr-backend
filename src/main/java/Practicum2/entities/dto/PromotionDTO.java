package Practicum2.entities.dto;

import java.math.BigDecimal;

public class PromotionDTO {
    private int empNo;
    private BigDecimal salary;
    private String title;
    private String deptNo;

    public PromotionDTO() {}

    public int getEmpNo() {
        return empNo;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }
}
