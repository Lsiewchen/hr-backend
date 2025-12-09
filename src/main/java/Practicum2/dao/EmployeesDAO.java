package Practicum2.dao;

import Practicum2.EMFactory;
import Practicum2.entities.*;
import Practicum2.dto.EmployeesDTO;
import Practicum2.dto.PromotionDTO;
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
            em.getTransaction().commit();
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
            em.getTransaction().commit();
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
        int empNo = promotionDTO.getEmpNo();
        BigDecimal newSalary = promotionDTO.getSalary();
        String newTitle = promotionDTO.getTitle();
        String newDeptNo = promotionDTO.getDeptNo();
        LocalDate promotionDate = promotionDTO.getPromotionDate();

        try {
            em.getTransaction().begin();
            if (em.find(Employees.class, empNo) == null) {
                return "There is no such employee.";
            }
            if (newSalary == null &&
                    newTitle == null &&
                    newDeptNo == null &&
                    promotionDate == null) {
                return "JSON body is empty.";
            }

            //Salaries
            if (newSalary != null) {
                if (newSalary.compareTo(BigDecimal.ZERO) < 0) {
                    return "Salary cannot be negative.";
                }
                changeSalary(em, empNo, newSalary, promotionDate);
            }
            //Titles
            boolean changedTitle = false;
            String oldTitle = em.createNamedQuery("Titles.findByEmpNoToDate", String.class)
                    .setParameter("empNo", empNo)
                    .setParameter("toDate", LocalDate.of(9999, 1, 1))
                    .getSingleResult();
            if (promotionDTO.getTitle() != null) {
                changedTitle = changeTitle(em, empNo, newTitle, promotionDate);
            }
            //Departments Employees
            boolean changedDepartment = false;
            if (newDeptNo != null) {
                if (em.find(Departments.class, promotionDTO.getDeptNo()) == null) {
                    em.getTransaction().rollback();
                    return "The department " + newDeptNo + " does not exist.";
                }
                changedDepartment = changeDepartment(em, empNo, newDeptNo, promotionDate);
            }
            //Departments Manager
            boolean titleChangedToManager = newTitle != null
                    && newTitle.equalsIgnoreCase("manager");
            boolean previouslyManager = oldTitle.equalsIgnoreCase("manager");
            String currentDeptNo = em.createNamedQuery("DeptEmp.findByEmpNoToDate", String.class)
                    .setParameter("empNo", empNo)
                    .setParameter("toDate", LocalDate.of(9999, 1, 1))
                    .getSingleResult();
            if ((titleChangedToManager || previouslyManager)
                    && (changedTitle || changedDepartment)) {
                changeDeptManager(em, empNo, currentDeptNo, promotionDate);
            }

            em.getTransaction().commit();
            return null;
        }
        catch (Exception e){
            em.getTransaction().rollback();
            return "Promotion unsuccessful.";
        }
        finally {
            em.close();
        }
    }

    /**
     * Method to change the salary of an employee, it will check and update the
     * 'toDate' of the current salary before adding the new salary.
     * @param em - an Entity Manager to manage the persistence context
     * @param empNo - an integer of employee number
     * @param salary - a BigDecimal of the new salary
     * @param promotionDate - a LocalDate of the promotion date
     */
    private void changeSalary(EntityManager em, int empNo, BigDecimal salary, LocalDate promotionDate) {
        BigDecimal oldSalary = em.createNamedQuery("Salaries.findByEmpNoToDate", BigDecimal.class)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .getSingleResult();
        if (oldSalary.compareTo(salary) != 0) {
            em.createNamedQuery("Salaries.updateById")
                    .setParameter("empNo", empNo)
                    .setParameter("promotionDate", promotionDate)
                    .setParameter("toDate", LocalDate.of(9999, 1, 1))
                    .executeUpdate();
            Salaries newSalaries = new Salaries(em.find(Employees.class, empNo), promotionDate, salary,
                    LocalDate.of(9999, 1, 1));
            em.persist(newSalaries);
        }
    }

    /**
     * Method to change the title of an employee, it will check and update the
     * 'toDate' of the current title before adding the new title.
     * @param em - an Entity Manager to manage the persistence context
     * @param empNo - an integer of employee number
     * @param title - a String of the new title
     * @param promotionDate - a LocalDate of the promotion date
     * @return - a boolean to indicate if title has been changed
     */
    private boolean changeTitle(EntityManager em, int empNo, String title, LocalDate promotionDate) {
        String oldTitle = em.createNamedQuery("Titles.findByEmpNoToDate", String.class)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .getSingleResult();
        if (!oldTitle.equalsIgnoreCase(title)) {
            em.createNamedQuery("Titles.updateById")
                    .setParameter("promotionDate", promotionDate)
                    .setParameter("oldTitle", oldTitle)
                    .setParameter("empNo", empNo)
                    .setParameter("toDate", LocalDate.of(9999, 1, 1))
                    .executeUpdate();
            Titles newTitle = new Titles(title, promotionDate,
                    LocalDate.of(9999, 1, 1), em.find(Employees.class, empNo));
            em.persist(newTitle);
            return true;
        }
        return false;
    }

    /**
     * Method to change the department of an employee, it will check and update the
     * 'toDate' of the current title before adding the new department.
     * @param em - an Entity Manager to manage the persistence context
     * @param empNo - an integer of employee number
     * @param deptNo - a String of department number
     * @param promotionDate - a LocalDate of the promotion date
     * @return - a boolean to indicate if department has been changed
     */
    private boolean changeDepartment(EntityManager em, int empNo, String deptNo, LocalDate promotionDate) {
        String oldDept = em.createNamedQuery("DeptEmp.findByEmpNoToDate", String.class)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .getSingleResult();
        if (!oldDept.equals(deptNo)) {
            em.createNamedQuery("DeptEmp.updateById")
                    .setParameter("promotionDate", promotionDate)
                    .setParameter("empNo", empNo)
                    .setParameter("deptNo", oldDept)
                    .executeUpdate();
            DeptEmp newDeptEmp = new DeptEmp(em.find(Employees.class, empNo),
                    em.find(Departments.class, deptNo), promotionDate,
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
     * @param empNo - an integer of employee number
     * @param deptNo - a String of department number
     */
    private void changeDeptManager(EntityManager em, int empNo, String deptNo, LocalDate promotionDate) {
        em.createNamedQuery("DeptManager.updateById")
                .setParameter("promotionDate", promotionDate)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 1, 1))
                .executeUpdate();
        DeptManager deptManager = new DeptManager(em.find(Departments.class, deptNo),
                em.find(Employees.class, empNo), promotionDate,
                LocalDate.of(9999, 1, 1));
        em.persist(deptManager);
    }
}
