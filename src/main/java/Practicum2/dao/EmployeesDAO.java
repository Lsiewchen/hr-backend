package Practicum2.dao;

import Practicum2.EMFactory;
import Practicum2.entities.*;
import Practicum2.entities.dto.EmployeesDTO;
import Practicum2.entities.dto.PromotionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EmployeesDAO {
    private EntityManagerFactory emf;

    public EmployeesDAO() {
        this.emf = EMFactory.getInstance();
    }

    public List<Object[]> findFullRecordByEmpNo(int empNo) {
        List<Object[]> fullEmpRecordById;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            fullEmpRecordById = em.createNamedQuery(
                    "Employees.findFullRecordByEmpNo", Object[].class)
                    .setParameter("empNo", empNo)
                    .getResultList();
        }
        return fullEmpRecordById;
    }

    public List<EmployeesDTO> findAllRecordByDept(String deptNo, int pageNo) {
        List<EmployeesDTO> employeesRecord;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            employeesRecord = em.createNamedQuery("Employees.findAllRecordByDept", EmployeesDTO.class)
                    .setParameter("deptNo", deptNo)
                    .setFirstResult((pageNo - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        }
        return employeesRecord;
    }

    public String promoteEmployee(PromotionDTO promotionDTO) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();

            //If the employee cannot be found, return false
            int empNo = promotionDTO.getEmpNo();
            Employees employees = em.find(Employees.class, empNo);
            if (employees == null) {return "There is no employee with employee" +
                    "number of " + empNo+ ".";}

            //If the whole DTO is blank, return false
            if (promotionDTO.getSalary() == null &&
                promotionDTO.getTitle() == null &&
                promotionDTO.getDeptNo() == null
            ){return "JSON body is empty.";}

            //Salaries
            if (promotionDTO.getSalary() != null){
                BigDecimal oldSalary = em.createNamedQuery(
                        "Salaries.findByEmpNoToDate", BigDecimal.class)
                        .setParameter("empNo", empNo)
                        .setParameter("toDate", LocalDate.of(
                                9999, 01, 01))
                        .getSingleResult();
                if (oldSalary.compareTo(promotionDTO.getSalary()) != 0) {
                    em.createNamedQuery("Salaries.updateById")
                            .setParameter("empNo", empNo)
                            .setParameter("curDate", LocalDate.now())
                            .setParameter("toDate", LocalDate.of(
                                    9999, 01, 01))
                            .executeUpdate();
                    Salaries newSalaries = new Salaries(
                            employees,
                            LocalDate.now(),
                            promotionDTO.getSalary(),
                            LocalDate.of(9999, 01, 01));
                    em.persist(newSalaries);
                }
            }

            //Titles
            if (promotionDTO.getTitle() != null){
                String oldTitle = em.createNamedQuery(
                        "Titles.findByEmpNoToDate", String.class)
                        .setParameter("empNo", empNo)
                        .setParameter("toDate",
                                LocalDate.of(9999, 01, 01))
                        .getSingleResult();
                if (!oldTitle.equalsIgnoreCase(promotionDTO.getTitle())) {
                    em.createNamedQuery("Titles.updateById")
                            .setParameter("curDate", LocalDate.now())
                            .setParameter("oldTitle", oldTitle)
                            .setParameter("empNo", empNo)
                            .setParameter("toDate",
                                    LocalDate.of(9999, 01, 01))
                            .executeUpdate();
                    Titles newTitle = new Titles(promotionDTO.getTitle(),
                            LocalDate.now(),
                            LocalDate.of(9999, 01, 01),
                            employees);
                    em.persist(newTitle);

                    //Department Manager
                    if (promotionDTO.getTitle()
                            .equalsIgnoreCase("manager")) {
                        DeptManager newDeptManager = new DeptManager(
                                em.find(Departments.class,
                                promotionDTO.getDeptNo()),
                                employees, LocalDate.now(),
                                LocalDate.of(9999, 01, 01));
                        em.persist(newDeptManager);
                    }
                }
            }

            //Department Employee
            if (promotionDTO.getDeptNo() != null){
                String oldDept = em.createNamedQuery(
                                "DeptEmp.findByEmpNoToDate", String.class)
                        .setParameter("empNo", empNo)
                        .setParameter("toDate",
                                LocalDate.of(9999, 01, 01))
                        .getSingleResult();

                if (!oldDept.equals(promotionDTO.getDeptNo())) {
                    em.createNamedQuery("DeptEmp.updateById")
                            .setParameter("curDate", LocalDate.now())
                            .setParameter("empNo", empNo)
                            .setParameter("deptNo", oldDept)
                            .executeUpdate();

                    DeptEmp newDeptEmp = new DeptEmp(employees,
                            em.find(Departments.class,
                                    promotionDTO.getDeptNo()),
                                    LocalDate.now(),
                                    LocalDate.of(9999, 01, 01));
                    em.persist(newDeptEmp);

                    String oldTitle = em.createNamedQuery(
                            "Titles.findByEmpNoToDate", String.class)
                            .setParameter("empNo", empNo)
                            .setParameter("toDate",
                                    LocalDate.of(9999, 01, 01))
                            .getSingleResult();

                    if (oldTitle.equalsIgnoreCase(promotionDTO.getTitle())
                            && oldTitle.equalsIgnoreCase("manager")) {
                        em.createNamedQuery("DeptManager.updateById")
                                .setParameter("toDate", LocalDate.now())
                                .setParameter("deptNo", oldDept)
                                .setParameter("empNo", empNo)
                                .executeUpdate();
                        DeptManager newDeptManager = new DeptManager(
                                em.find(Departments.class,
                                        promotionDTO.getDeptNo()),
                                employees, LocalDate.now(),
                                LocalDate.of(9999, 01, 01));
                        em.persist(newDeptManager);
                    }
                }
            }

            em.getTransaction().commit();
            return "Promotion successful.";

        } catch (Exception e){
            em.getTransaction().rollback();
            return "Promotion unsuccessful. An exception has occurred: "
                    + e.getClass().getName();
        } finally {
            em.close();
        }
    }
}
