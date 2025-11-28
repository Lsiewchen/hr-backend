package Practicum2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "departments")
public class Departments {
    @Id
    @Size(max = 4)
    @Column(name = "dept_no")
    private String deptNo;
    @Size(max = 40)
    @Column(name = "dept_name")
    private String deptName;
    @JsonIgnore
    @OneToMany(mappedBy = "departments")
    private List<DeptEmp> deptEmp;
    @JsonIgnore
    @OneToMany(mappedBy = "departments")
    private List<DeptManager> deptManager;

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

    public List<DeptEmp> getDeptEmp() {
        return deptEmp;
    }

    public void setDeptEmp(List<DeptEmp> deptEmp) {
        this.deptEmp = deptEmp;
    }

    public List<DeptManager> getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(List<DeptManager> deptManager) {
        this.deptManager = deptManager;
    }
}
