package Practicum2;

import Practicum2.entities.Departments;
import Practicum2.entities.DeptEmp;
import Practicum2.entities.Employees;
import Practicum2.entities.Titles;
import Practicum2.entities.enums.Gender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;

public class Driver {
//    static final String DBNAME = "employees";

    public static void main(String[] args){
//        try (EntityManagerFactory emf = EMFactory.getInstance()) {
//            EntityManager em = emf.createEntityManager();
//            em.getTransaction().begin();

//            Departments departments = new Departments();
//            departments.setDeptNo("abc");
//            departments.setDeptName("testDepartment");
//            em.persist(departments);
//            em.getTransaction().commit();

//            Employees employees = new Employees();
//            employees.setEmpNo(1);
//            employees.setBirthDate(LocalDate.now());
//            employees.setFirstName("Siew");
//            employees.setLastName("Chen");
//            employees.setGender(Gender.F);
//            employees.setHireDate(LocalDate.now());
//            em.persist(employees);
//            em.getTransaction().commit();

//            DeptEmp deptEmp = new DeptEmp();
//            deptEmp.setDepartments(departments);
//            deptEmp.setEmployees(employees);
//            deptEmp.setFromDate(LocalDate.now());
//            deptEmp.setToDate(LocalDate.now());
//            em.persist(deptEmp);
//            em.getTransaction().commit();

//            Titles titles = new Titles();
//            titles.setTitle("testTitle");
//            titles.setFromDate(LocalDate.now());
//            titles.setEmployees(employees);
//            titles.setToDate(LocalDate.now());
//            em.persist(titles);
//            em.getTransaction().commit();

//            em.close();
//        }
    }
}
