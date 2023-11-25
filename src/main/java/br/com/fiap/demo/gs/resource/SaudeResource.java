package br.com.fiap.demo.gs.resource;

import java.util.List;

import br.com.fiap.demo.gs.model.Saude;
import br.com.fiap.demo.gs.service.SaudeService;
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

@Path("/saude")
public class SaudeResource {

    private final SaudeService saudeService = new SaudeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Saude> listarSaude() {
        return saudeService.listarSaude();
    }

    @GET
    @Path("/{idSaude}")
    @Produces(MediaType.APPLICATION_JSON)
    public Saude consultarSaude(@PathParam("idSaude") Long idSaude) {
        return saudeService.consultarSaude(idSaude);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraSaude(Saude saude) {
        saudeService.cadastraSaude(saude);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{idSaude}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaSaude(@PathParam("idSaude") Long idSaude) {
        boolean removido = saudeService.deletaSaude(idSaude);

        if (removido) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{idSaude}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaSaude(@PathParam("idSaude") Long idSaude, Saude saudeAtualizada) {
        boolean atualizado = saudeService.atualizaSaude(idSaude, saudeAtualizada);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}