package br.com.fiap.demo.gs.service;

import java.util.List;

import br.com.fiap.demo.gs.data.SonoDao;
import br.com.fiap.demo.gs.model.Sono;

public class SonoService {

    static SonoDao dao = new SonoDao();

    public List<Sono> listarSonos() {
        return dao.listarSonos();
    }

    public Sono consultarSono(Long idSono) {
        return dao.consultarSono(idSono);
    }

    public boolean cadastraSono(Sono sono) {
        if (!validar(sono)) return false;
        dao.cadastraSono(sono);
        return true;
    }

    private boolean validar(Sono sono) {
        if (sono.getSleepDuration() == null || sono.getSleepQuality() == null) {
            return false;
        }
        return true;
    }


    public boolean deletaSono(Long idSono) {
        Sono sono = dao.consultarSono(idSono);

        if (sono != null) {
            dao.deletaSono(idSono);
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaSono(Long idSono, Sono sonoAtualizado) {
        Sono sonoExistente = dao.consultarSono(idSono);

        if (sonoExistente != null) {
            sonoExistente.setSleepDuration(sonoAtualizado.getSleepDuration());
            sonoExistente.setSleepQuality(sonoAtualizado.getSleepQuality());
            
            dao.atualizaSono(sonoExistente);
            
            return true;
        } else {
            return false;
        }
    }
}