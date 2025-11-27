package Practicum2.entities;

//@Entity
//@Table("departments")
public class Departments {
    private String deptNo;
    private String deptName;

    public Departments() {}

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
