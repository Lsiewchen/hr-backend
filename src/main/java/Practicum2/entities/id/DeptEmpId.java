package Practicum2.entities.id;

import java.io.Serializable;
import java.util.Objects;

public class DeptEmpId implements Serializable {
    private int employees;
    private String departments;

    public DeptEmpId() {}
    public DeptEmpId(int employees, String departments) {
        this.employees = employees;
        this.departments = departments;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    @Override
    public boolean equals(Object deptEmp) {
        if (this == deptEmp) {
            return true;
        }
        if (deptEmp == null || getClass() != deptEmp.getClass()) {
            return false;
        }
        DeptEmpId inDeptEmp = (DeptEmpId) deptEmp;
        return Objects.equals(employees, inDeptEmp.employees) &&
                Objects.equals(departments, inDeptEmp.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employees, departments);
    }
}
