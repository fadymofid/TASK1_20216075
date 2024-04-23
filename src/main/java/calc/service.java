package calc;
import java.util.*;

import javax.ejb.*;
import javax.persistence.*;

import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import calc.Calculation;
@Stateless
@Path("/calc")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class service {
	
	@PersistenceContext(unitName="hello")
	private EntityManager entitymanager;


	@POST
	@Path("/perform")
	public Response CreateCalculation (Calculation calculation) {
		try {
			 entitymanager.persist(calculation);
		
	         int result = calculation.performCalculation(calculation.getNumber1(),calculation.getNumber2() , calculation.getOperation());
	         return Response.status(Response.Status.OK).entity("{\"Result\": "+ result + "}").build();
        }catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
		}
	
	@GET
    @Path("/calculations")
    public Response getAll() {
        try {
            return Response.status(Response.Status.OK).entity(entitymanager.createQuery("SELECT a FROM Calculation a").getResultList()).build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
		
	}
	

