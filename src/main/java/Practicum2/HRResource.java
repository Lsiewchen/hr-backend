package Practicum2;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("hr")
public class HRResource {
    @GET
    @Path("test")
    public Response ping() {
        return Response.ok().entity("test success").build();
    }
}
