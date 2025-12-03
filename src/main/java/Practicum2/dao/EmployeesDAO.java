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

/**
 * DAO class that encapsulates all database access and operations for Employees data.
 */
public class EmployeesDAO {
    /**
     * Entity Manager Factory instance to be used to create the Entity Manager.
     */
    private EntityManagerFactory emf;

    /**
     * Constructor that retrieves the shared EntityManagerFactory instance.
     */
    public EmployeesDAO() {
        this.emf = EMFactory.getInstance();
    }

    /**
     * Method to get a full employees record by using the employee number.
     * @param empNo - an integer of employee number
     * @return - a list of the full employee record
     */
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

    /**
     * Method to get all employees record in a department by using the department
     * number and filtered by page number where each page has a maximum of 20 records.
     * @param deptNo - a String of department number
     * @param pageNo - an integer of page number
     * @return - a list of employee record (consist of employee number, first
     * name, last name and hire date)
     * @throws IllegalArgumentException - to handle negative page number
     */
    public List<EmployeesDTO> findAllRecordByDept(String deptNo, int pageNo)
            throws IllegalArgumentException {
        List<EmployeesDTO> employeesRecord;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            employeesRecord = em.createNamedQuery(
                    "Employees.findAllRecordByDept", EmployeesDTO.class)
                    .setParameter("deptNo", deptNo)
                    .setFirstResult((pageNo - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        }
        return employeesRecord;
    }

    /**
     * Method to promote employee by consuming a JSON string of the data
     * required for an employee's promotion.
     * @param promotionDTO - a promotionDTO object that consist of a mandatory
     *                     employee number , and optional fields including salary,
     *                     title and department
     * @return - a String message to display any error or null if successful
     */
    public String promoteEmployee(PromotionDTO promotionDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employees employees = em.find(Employees.class, promotionDTO.getEmpNo());
            if (employees == null) {
                return "There is no employee with employee number of " + promotionDTO.getEmpNo() + ".";
            }
            if (promotionDTO.getSalary() == null &&
                    promotionDTO.getTitle() == null &&
                    promotionDTO.getDeptNo() == null) {
                return "JSON body is empty.";
            }

            //Salaries
            if (promotionDTO.getSalary() != null) {
                if (promotionDTO.getSalary().compareTo(BigDecimal.ZERO) < 0) {
                    return "Salary cannot be negative.";
                }
                changeSalary(em, employees, promotionDTO.getSalary());
            }
            //Titles
            boolean changedTitle = false;
            if (promotionDTO.getTitle() != null) {
                changedTitle = changeTitle(em, employees, promotionDTO.getTitle());
            }
            //Departments Employees
            Departments departments = null;
            boolean changedDepartment = false;
            if (promotionDTO.getDeptNo() != null) {
                departments = em.find(Departments.class, promotionDTO.getDeptNo());
                if (departments == null) {
                    em.getTransaction().rollback();
                    return "The department " + promotionDTO.getDeptNo() + " does not exist.";
                }
                changedDepartment = changeDepartment(em, employees, departments);
            }
            //Departments Manager
            if (promotionDTO.getTitle() != null
                    && promotionDTO.getTitle().equalsIgnoreCase("manager")
                    && (changedTitle || changedDepartment)) {
                changeDeptManager(em, employees, departments);
            }

            em.getTransaction().commit();
            return null;
        }
        catch (Exception e){
            em.getTransaction().rollback();
            return "Promotion unsuccessful. Error: " + e.getClass().getName();
        }
        finally {
            em.close();
        }
    }

    /**
     * Method to change the salary of an employee, it will check and update the
     * 'toDate' of the current salary before adding the new salary.
     * @param em - an Entity Manager to manage the persistence context
     * @param employees - an Employees object
     * @param salary - a BigDecimal of the new salary
     */
    private void changeSalary(EntityManager em, Employees employees, BigDecimal salary) {
        BigDecimal oldSalary = em.createNamedQuery("Salaries.findByEmpNoToDate", BigDecimal.class)
                .setParameter("empNo", employees.getEmpNo())
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .getSingleResult();
        if (oldSalary.compareTo(salary) != 0) {
            em.createNamedQuery("Salaries.updateById")
                    .setParameter("empNo", employees.getEmpNo())
                    .setParameter("curDate", LocalDate.now())
                    .setParameter("toDate", LocalDate.of(9999, 1, 1))
                    .executeUpdate();
            Salaries newSalaries = new Salaries(employees, LocalDate.now(), salary,
                    LocalDate.of(9999, 1, 1));
            em.persist(newSalaries);
        }
    }

    /**
     * Method to change the title of an employee, it will check and update the
     * 'toDate' of the current title before adding the new title.
     * @param em - an Entity Manager to manage the persistence context
     * @param employees - an Employees object
     * @param title - a String of the new title
     * @return - a boolean to indicate if title has been changed
     */
    private boolean changeTitle(EntityManager em, Employees employees, String title) {
        String oldTitle = em.createNamedQuery("Titles.findByEmpNoToDate", String.class)
                .setParameter("empNo", employees.getEmpNo())
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .getSingleResult();
        if (!oldTitle.equalsIgnoreCase(title)) {
            em.createNamedQuery("Titles.updateById")
                    .setParameter("curDate", LocalDate.now())
                    .setParameter("oldTitle", oldTitle)
                    .setParameter("empNo", employees.getEmpNo())
                    .setParameter("toDate", LocalDate.of(9999, 1, 1))
                    .executeUpdate();
            Titles newTitle = new Titles(title, LocalDate.now(),
                    LocalDate.of(9999, 1, 1), employees);
            em.persist(newTitle);
            return true;
        }
        return false;
    }

    /**
     * Method to change the department of an employee, it will check and update the
     * 'toDate' of the current title before adding the new department.
     * @param em - an Entity Manager to manage the persistence context
     * @param employees - an Employees object
     * @param departments - a Departments object of the new department
     * @return - a boolean to indicate if department has been changed
     */
    private boolean changeDepartment(EntityManager em, Employees employees, Departments departments) {
        String oldDept = em.createNamedQuery("DeptEmp.findByEmpNoToDate", String.class)
                .setParameter("empNo", employees.getEmpNo())
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .getSingleResult();
        if (!oldDept.equals(departments.getDeptNo())) {
            em.createNamedQuery("DeptEmp.updateById")
                    .setParameter("curDate", LocalDate.now())
                    .setParameter("empNo", employees.getEmpNo())
                    .setParameter("deptNo", oldDept)
                    .executeUpdate();
            DeptEmp newDeptEmp = new DeptEmp(employees, departments, LocalDate.now(),
                    LocalDate.of(9999, 1, 1));
            em.persist(newDeptEmp);
            return true;
        }
        return false;
    }

    /**
     * Method to change the manager of a department, if employee is currently
     * department manager it will update the 'toDate' before adding a new
     * record for department manager.
     * @param em - an Entity Manager to manage the persistence context
     * @param employees - an Employees object
     * @param departments - a Departments object of the new department
     */
    private void changeDeptManager(EntityManager em, Employees employees, Departments departments) {
        em.createNamedQuery("DeptManager.updateById")
                .setParameter("curDate", LocalDate.now())
                .setParameter("empNo", employees.getEmpNo())
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .executeUpdate();
        DeptManager deptManager = new DeptManager(departments, employees,
                LocalDate.now(), LocalDate.of(9999, 1, 1));
        em.persist(deptManager);
    }
}
