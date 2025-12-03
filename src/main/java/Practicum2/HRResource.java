package Practicum2;

import Practicum2.dao.DepartmentsDAO;
import Practicum2.dao.EmployeesDAO;
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

/**
 * HRResource class that exposes all HR-related endpoints.
 */
@Path("hr")
public class HRResource {
    /**
     * Departments DAO used to perform all database operations related to departments.
     */
    private DepartmentsDAO departmentsDAO;
    /**
     * Employees DAO used to perform all database operations related to employees.
     */
    private EmployeesDAO employeesDAO;

    /**
     * Constructor to initialize DAO.
     */
    public HRResource() {
        this.departmentsDAO = new DepartmentsDAO();
        this.employeesDAO = new EmployeesDAO();
    }

    /**
     * GET request to retrieve all departments from the database.
     * @return - a 200 status with Response object containing either the list of
     * all departments as JSON or a 404 status with an appropriate error message
     * if no departments exist
     */
    @GET
    @Path("all-dept")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allDepartments() {
        List<Departments> departments = departmentsDAO.findAllDepartments();
        if (departments.isEmpty()) {
            return Response.status(404).entity("There is no department.").build();
        }
        return Response.ok().entity(departments).build();
    }

    /**
     * GET request to retrieve the full record of an employee record based on
     * employee number.
     * @param empNo - an integer of employee number
     * @return - a 200 status with Response object containing the employee's full
     * record as a JSON object or a 404 status with an appropriate error message
     * if no employee is found
     */
    @GET
    @Path("emp/{empNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fullEmpRecord(@PathParam("empNo") int empNo) {
        List<Object[]> fullEmpRecordById = employeesDAO.findFullRecordByEmpNo(empNo);
        if (fullEmpRecordById.isEmpty()) {
            return Response.status(404)
                    .entity("There is no employee with employee number of " + empNo + ".")
                    .build();
        }
        return Response.ok().entity(fullEmpRecordById).build();
    }

    /**
     * GET request to retrieve a paginated list of employees for a specific department.
     * @param deptNo - a String of department number
     * @param pageNo - a integer of page number, defaults to 1 if not provided
     * @return - a 200 status with Response object containing a list of EmployeesDTO
     * or a 404 status if no records are found
     */
    @GET
    @Path("all-emp/{deptNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allEmpRecords(@PathParam("deptNo") String deptNo,
                                  @QueryParam("pageNo") @DefaultValue("1") int pageNo) {
        List<EmployeesDTO> employeesRecord;
        try {
            employeesRecord = employeesDAO.findAllRecordByDept(deptNo, pageNo);
        }
        catch (IllegalArgumentException e) {
            return Response.status(404)
                    .entity("There is no employee record in page " + pageNo + ".")
                    .build();
        }
        if (employeesRecord.isEmpty()) {
            return Response.status(404)
                    .entity("There is no employee record in department "
                            + deptNo + " and page " + pageNo + ".")
                    .build();
        }
        return Response.ok().entity(employeesRecord).build();
    }

    /**
     * POST request to promote an employee based on the details provided in the
     * PromotionDTO object. The function includes updating the employee's title,
     * salary, department and department manager.
     * @param promotionDTO - an object containing the promotion details, including
     *                     employee number, new salary, new title, and new
     *                     department number
     * @return - a Response object containing the result of the promotion
     * operation, typically including a success message with updated details
     */
    @POST
    @Path("emp-promote")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response empPromotion(PromotionDTO promotionDTO) {
        String promoteEmployee = employeesDAO.promoteEmployee(promotionDTO);
        if (promoteEmployee == null) {
            return Response.ok().entity("Promotion successful.").build();
        }
        return Response.status(400).entity(promoteEmployee).build();
    }
}
