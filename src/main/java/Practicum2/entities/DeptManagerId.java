package Practicum2.entities;

import java.io.Serializable;
import java.util.Objects;

public class DeptManagerId implements Serializable {
    private String departments;
    private int employees;

    public DeptManagerId() {}
    public DeptManagerId(String departments, int employees) {
        this.departments = departments;
        this.employees = employees;
    }

    @Override
    public boolean equals(Object deptManager) {
        if (this == deptManager) {
            return true;
        }
        if (deptManager == null || getClass() != deptManager.getClass()) {
            return false;
        }
        DeptManagerId inDeptManager = (DeptManagerId) deptManager;
        return Objects.equals(departments, inDeptManager.departments) && Objects.equals(employees, inDeptManager.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departments, employees);
    }
}
