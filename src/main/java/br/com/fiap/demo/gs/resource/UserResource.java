package br.com.fiap.demo.gs.resource;

import java.util.List;

import br.com.fiap.demo.gs.model.User;
import br.com.fiap.demo.gs.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    private final UserService userService = new UserService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> listarUsers() {
        return userService.listarUsers();
    }

    @GET
    @Path("/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public User consultarUser(@PathParam("idUser") Long idUser) {
        return userService.consultarUser(idUser);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraUser(User user) {
        userService.cadastraUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaUser(@PathParam("idUser") Long idUser) {
        boolean removido = userService.deletaUser(idUser);

        if (removido) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{idUser}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaUser(@PathParam("idUser") Long idUser, User userAtualizado) {
        boolean atualizado = userService.atualizaUser(idUser, userAtualizado);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
