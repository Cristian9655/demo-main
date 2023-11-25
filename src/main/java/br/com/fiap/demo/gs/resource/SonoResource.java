package br.com.fiap.demo.gs.resource;

import java.util.List;

import br.com.fiap.demo.gs.model.Sono;
import br.com.fiap.demo.gs.service.SonoService;
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

@Path("/sonos")
public class SonoResource {

    private final SonoService sonoService = new SonoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sono> listarSonos() {
        return sonoService.listarSonos();
    }

    @GET
    @Path("/{idSono}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sono consultarSono(@PathParam("idSono") Long idSono) {
        return sonoService.consultarSono(idSono);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraSono(Sono sono) {
        sonoService.cadastraSono(sono);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{idSono}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaSono(@PathParam("idSono") Long idSono) {
        boolean removido = sonoService.deletaSono(idSono);

        if (removido) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{idSono}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaSono(@PathParam("idSono") Long idSono, Sono sonoAtualizado) {
        boolean atualizado = sonoService.atualizaSono(idSono, sonoAtualizado);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}