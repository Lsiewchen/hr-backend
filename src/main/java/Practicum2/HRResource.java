package Practicum2;

import Practicum2.entities.*;
import Practicum2.entities.dto.EmployeesDTO;
import Practicum2.entities.dto.PromotionDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Path("hr")
public class HRResource {

    private final EntityManagerFactory emf = EMFactory.getInstance();

    //task 1
    @GET
    @Path("all-dept")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allDepartments() {
        List<Departments> departments;
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            departments = em.createNamedQuery("Departments.findAll", Departments.class).getResultList();
        }
        return Response.ok().entity(departments).build();
    }

    //task 2
    @GET
    @Path("emp/{empNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fullEmpRecord(@PathParam("empNo") int empNo) {
        List<Object[]> fullEmpRecordById;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            fullEmpRecordById = em.createNamedQuery("Employees.findFullRecordByEmpNo", Object[].class)
                    .setParameter("empNo", empNo)
                    .getResultList();
        }
        return Response.ok().entity(fullEmpRecordById).build();
    }

    //task 3
    @GET
    @Path("all-emp/{deptNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allEmpRecords(@PathParam("deptNo") String deptNo, @QueryParam("pageNo") @DefaultValue("1") int pageNo) {
        List<EmployeesDTO> employeesRecord;
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            employeesRecord = em.createNamedQuery("Employees.findAllRecordByDept", EmployeesDTO.class)
                    .setParameter("deptNo", deptNo)
                    .setFirstResult((pageNo - 1) * 20)
                    .setMaxResults(20)
                    .getResultList();
        }
        return Response.ok().entity(employeesRecord).build();
    }

    //task 4
    @POST
    @Path("emp-promote")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response empPromotion(PromotionDTO promotionDTO) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        int empNo = promotionDTO.getEmpNo();
        Employees employees = em.find(Employees.class, empNo);
        if (employees == null) {
            return Response.noContent().build();
        }
    //Salaries
        BigDecimal oldSalary = em.createNamedQuery("Salaries.findByEmpNoToDate", BigDecimal.class)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 01, 01))
                .getSingleResult();
        if (oldSalary.compareTo(promotionDTO.getSalary()) != 0) {
            em.createNamedQuery("Salaries.updateById")
                    .setParameter("empNo", empNo)
                    .setParameter("curDate", LocalDate.now())
                    .setParameter("toDate", LocalDate.of(9999, 01, 01))
                    .executeUpdate();
            Salaries newSalaries = new Salaries(employees, LocalDate.now(), promotionDTO.getSalary(), LocalDate.of(9999, 01, 01));
            em.persist(newSalaries);
        }
    //Titles
        String oldTitle = em.createNamedQuery("Titles.findByEmpNoToDate", String.class)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 01, 01))
                .getSingleResult();
        if (!oldTitle.equalsIgnoreCase(promotionDTO.getTitle())) {
            em.createNamedQuery("Titles.updateById")
                    .setParameter("curDate", LocalDate.now())
                    .setParameter("oldTitle", oldTitle)
                    .setParameter("empNo", empNo)
                    .setParameter("toDate", LocalDate.of(9999, 01, 01))
                    .executeUpdate();
            Titles newTitle = new Titles(promotionDTO.getTitle(), LocalDate.now(), LocalDate.of(9999, 01, 01), employees);
            em.persist(newTitle);

        //Department Manager
            if (promotionDTO.getTitle().equalsIgnoreCase("manager")) {
                DeptManager newDeptManager = new DeptManager(em.find(Departments.class, promotionDTO.getDeptNo()), employees, LocalDate.now(), LocalDate.of(9999, 01, 01));
                em.persist(newDeptManager);
            }
        }

    //Department Employee
        String oldDept = em.createNamedQuery("DeptEmp.findByEmpNoToDate", String.class)
                .setParameter("empNo", empNo)
                .setParameter("toDate", LocalDate.of(9999, 01, 01))
                .getSingleResult();
        if (!oldDept.equals(promotionDTO.getDeptNo())) {
            em.createNamedQuery("DeptEmp.updateById")
                    .setParameter("curDate", LocalDate.now())
                    .setParameter("empNo", empNo)
                    .setParameter("deptNo", oldDept)
                    .executeUpdate();
            DeptEmp newDeptEmp = new DeptEmp(employees, em.find(Departments.class, promotionDTO.getDeptNo()), LocalDate.now(), LocalDate.of(9999, 01, 01));
            em.persist(newDeptEmp);
            if (oldTitle.equalsIgnoreCase(promotionDTO.getTitle()) && oldTitle.equalsIgnoreCase("manager")) {
                em.createNamedQuery("DeptManager.updateById")
                        .setParameter("toDate", LocalDate.now())
                        .setParameter("deptNo", oldDept)
                        .setParameter("empNo", empNo)
                        .executeUpdate();
                DeptManager newDeptManager = new DeptManager(em.find(Departments.class, promotionDTO.getDeptNo()), employees, LocalDate.now(), LocalDate.of(9999, 01, 01));
                em.persist(newDeptManager);
            }
        }

        em.getTransaction().commit();
        em.close();
        return Response.ok().build();
    }

}
