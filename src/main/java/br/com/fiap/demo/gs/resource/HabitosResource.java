package br.com.fiap.demo.gs.resource;

import java.util.List;

import br.com.fiap.demo.gs.model.Habitos;
import br.com.fiap.demo.gs.service.HabitosService;
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

@Path("/habitos")
public class HabitosResource {

    private final HabitosService habitosService = new HabitosService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Habitos> listarHabitos() {
        return habitosService.listarHabitos();
    }

    @GET
    @Path("/{idHabitos}")
    @Produces(MediaType.APPLICATION_JSON)
    public Habitos consultarHabitos(@PathParam("idHabitos") Long idHabitos) {
        return habitosService.consultarHabitos(idHabitos);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraHabitos(Habitos habito) {
        boolean cadastrado = habitosService.cadastraHabitos(habito);

        if (cadastrado) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{idHabitos}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaHabitos(@PathParam("idHabitos") Long idHabitos) {
        boolean removido = habitosService.deletaHabitos(idHabitos);

        if (removido) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{idHabitos}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaHabitos(@PathParam("idHabitos") Long idHabitos, Habitos habitoAtualizado) {
        boolean atualizado = habitosService.atualizaHabitos(idHabitos, habitoAtualizado);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
