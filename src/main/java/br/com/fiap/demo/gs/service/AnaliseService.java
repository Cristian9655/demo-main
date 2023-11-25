package br.com.fiap.demo.gs.service;

import java.util.List;

import br.com.fiap.demo.gs.data.AnaliseDao;
import br.com.fiap.demo.gs.model.Analise;

public class AnaliseService {

    static AnaliseDao dao = new AnaliseDao();

    public List<Analise> listarAnalises() {
        return dao.listarAnalises();
    }

    public Analise consultarAnalise(Long idAnalise) {
        return dao.consultarAnalise(idAnalise);
    }

    public boolean cadastraAnalise(Analise analise) {
        if (!validar(analise)) return false;
        dao.cadastraAnalise(analise);
        return true;
    }

    private boolean validar(Analise analise) {
        return analise != null 
                && analise.getSleepDisorder() != -1 
                && analise.getDiagnostic() != null 
                && !analise.getDiagnostic().isEmpty();
    }


    public boolean deletaAnalise(Long idAnalise) {
        Analise analise = dao.consultarAnalise(idAnalise);

        if (analise != null) {
            dao.deletaAnalise(idAnalise);
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaAnalise(Long idAnalise, Analise analiseAtualizada) {
        Analise analiseExistente = dao.consultarAnalise(idAnalise);

        if (analiseExistente != null) {
            analiseExistente.setSleepDisorder(analiseAtualizada.getSleepDisorder());
            analiseExistente.setDiagnostic(analiseAtualizada.getDiagnostic());
            analiseExistente.setIdUser(analiseAtualizada.getIdUser());

            dao.atualizaAnalise(analiseExistente);

            return true;
        } else {
            return false;
        }
    }
}