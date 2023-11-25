package br.com.fiap.demo.gs.resource;

import java.util.List;

import br.com.fiap.demo.gs.model.Analise;
import br.com.fiap.demo.gs.service.AnaliseService;
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

@Path("/analises")
public class AnaliseResource {

    private final AnaliseService analiseService = new AnaliseService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Analise> listarAnalises() {
        return analiseService.listarAnalises();
    }

    @GET
    @Path("/{idAnalise}")
    @Produces(MediaType.APPLICATION_JSON)
    public Analise consultarAnalise(@PathParam("idAnalise") Long idAnalise) {
        return analiseService.consultarAnalise(idAnalise);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastraAnalise(Analise analise) {
        analiseService.cadastraAnalise(analise);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{idAnalise}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletaAnalise(@PathParam("idAnalise") Long idAnalise) {
        boolean removido = analiseService.deletaAnalise(idAnalise);

        if (removido) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{idAnalise}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizaAnalise(@PathParam("idAnalise") Long idAnalise, Analise analiseAtualizada) {
        boolean atualizado = analiseService.atualizaAnalise(idAnalise, analiseAtualizada);

        if (atualizado) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
