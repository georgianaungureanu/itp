package xyz.bcdi.greasypopcorn.webservices;

import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import xyz.bcdi.greasypopcorn.core.User;
import xyz.bcdi.greasypopcorn.dbaccess.UserDAO;

@Path("users")
public class UserResource extends AbstractResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return UserDAO.getInstance().getUsers();
	}
	
	@GET
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("username") String username) {
		return UserDAO.getInstance().getUser(username);
	}
	
	@PUT
	public Response replaceOrCreateUsers() {
		return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
	}
	
	@PUT
	@Path("{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response replaceOrCreateUser(User u, @PathParam("username") String username) {
		return null;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User u, @Context UriInfo uriInfo) {
		return null;
	}
	
	@POST
	@Path("{username}")
	public Response createUser(@PathParam("username") String username) {
		return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "PUT", "DELETE").build();
	}
	
	@DELETE
	public Response deleteUsers() {
		return Response.status(Status.METHOD_NOT_ALLOWED).allow("GET", "POST").build();
	}
	
	@DELETE
	@Path("{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("username") String username) {
		boolean wasDeleted = UserDAO.getInstance().deleteUser(username);
		return buildResponseForDeleteEntity(wasDeleted);
	}
}
