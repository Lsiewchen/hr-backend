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

@Path("hr")
public class HRResource {
    private DepartmentsDAO departmentsDAO;
    private EmployeesDAO employeesDAO;

    public HRResource() {
        this.departmentsDAO = new DepartmentsDAO();
        this.employeesDAO = new EmployeesDAO();
    }

    //task 1
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

    //task 2
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

    //task 3
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
                    .entity("There is no employee record in department " + deptNo + ".")
                    .build();
        }
        return Response.ok().entity(employeesRecord).build();
    }

    //task 4
    @POST
    @Path("emp-promote")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response empPromotion(PromotionDTO promotionDTO) {
        String promoteEmployee = employeesDAO.promoteEmployee(promotionDTO);

        return Response.ok().entity(promoteEmployee).build();

    }

}
