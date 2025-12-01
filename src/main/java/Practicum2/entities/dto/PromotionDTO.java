package Practicum2.entities.dto;

import java.math.BigDecimal;

public class PromotionDTO {
    private int empNo;
    private BigDecimal salary;
    private String title;
    private String deptNo;

    public PromotionDTO() {}
    public PromotionDTO(int empNo, BigDecimal salary, String title, String deptNo) {
        this.empNo = empNo;
        this.salary = salary;
        this.title = title;
        this.deptNo = deptNo;
    }

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
}
