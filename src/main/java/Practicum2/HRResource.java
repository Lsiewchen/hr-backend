package Practicum2;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;

@Path("hr")
public class HRResource {

    //task 1
    @GET
    @Path("all-dept")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allDepartments() {
        // from 'departments' table, get all
        return Response.ok().entity("all dept success").build();
    }

    //task 2
    @GET
    @Path("emp/{empNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fullEmpRecord(@PathParam("empNo") int empNo) {
        // using 'emp_no' from 'employees' table get all
        // 'employees' > 'dept_emp' > 'departments' > 'dept_manager'
        //             > 'salaries'
        //             > 'titles'
        return Response.ok().entity("full emp record for " + empNo + " success").build();
    }

    //task 3
    @GET
    @Path("all-emp/{deptNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allEmpRecords(@PathParam("deptNo") String depNo, @QueryParam("pageNo") @DefaultValue("1") int pageNo) {
        int pageNoIndex = pageNo - 1;
        // using 'dept_no' from 'employees' > 'dept_emp'
        // returned List based on 'pageNo' cal first and last index then create a new List using subList(), each page 20 records
        //e.g. page 3
        // start = 3-1 * 20 = 40
        // if (size - start < 20)
        //      end = size - 1
        // else
        //      end = 40 + 20 - 1 = 59
        return Response.ok().entity("all emp from dep " + depNo + " page " + pageNo + " success").build();
    }

    @POST
    @Path("emp-promote/{empNo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response empPromotion(@PathParam("empNo") int empNo, @QueryParam("salary") BigDecimal salary, @QueryParam("title") String title) {
        // create
        // #title if 'Manager' create in 'dept_manager', 'titles', 'salary' table
        // #title else create in 'titles' and 'salary' table
        //      for 'to_date' from 'titles', 'dept_manager', 'salary' table set to '9999-01-01'
        //      for 'from_date' from 'titles', 'dept_manager', 'salary' table set to today's date

        // update
        // old record 'to_date' from 'titles', 'salary' table to today's date
        return Response.ok().entity("success").build();
    }

}
