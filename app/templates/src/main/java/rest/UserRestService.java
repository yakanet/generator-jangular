package <%= packageName %>.rest;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mycompany.myapp.dao.api.UserDao;
import com.mycompany.myapp.entity.User;

@Stateless
@LocalBean
@Path("/user")
public class UserRestService {

    private UserDao userDao;

    @Inject
    public UserRestService(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> findAll() {
        return userDao.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findById(@PathParam("id") Long id) {
        User user =  userDao.find(id);
        if(user == null){
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addUser(@Valid User user) {
        if (userDao.findByUsername(user.getUsername()) != null) {
            return Response.status(Status.CONFLICT).build();
        }
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        if (validator.validate(user).size() > 0) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        user = userDao.persist(user);
        return Response.ok(user).build();
    }
}