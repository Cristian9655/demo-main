package br.com.fiap.demo.gs.service;

import java.util.List;

import br.com.fiap.demo.gs.data.SaudeDao;
import br.com.fiap.demo.gs.model.Saude;

public class SaudeService {

    static SaudeDao dao = new SaudeDao();

    public List<Saude> listarSaude() {
        return dao.listarSaude();
    }

    public Saude consultarSaude(Long idSaude) {
        return dao.consultarSaude(idSaude);
    }

    public boolean cadastraSaude(Saude saude) {
        if (!validar(saude)) return false;
        dao.cadastraSaude(saude);
        return true;
    }

    private boolean validar(Saude saude) {
        return saude != null
                && saude.getStress() != null
                && saude.getBmi() != null
                && saude.getBloodPressure() != null
                && saude.getHeartRate() != null
                && saude.getIdUser() != null;
    }

    public boolean deletaSaude(Long idSaude) {
        Saude saude = dao.consultarSaude(idSaude);

        if (saude != null) {
            dao.deletaSaude(idSaude);
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaSaude(Long idSaude, Saude saudeAtualizada) {
        Saude saudeExistente = dao.consultarSaude(idSaude);

        if (saudeExistente != null) {
            saudeExistente.setStress(saudeAtualizada.getStress());
            saudeExistente.setBmi(saudeAtualizada.getBmi());
            saudeExistente.setBloodPressure(saudeAtualizada.getBloodPressure());
            saudeExistente.setHeartRate(saudeAtualizada.getHeartRate());
            saudeExistente.setIdUser(saudeAtualizada.getIdUser());

            dao.atualizaSaude(saudeExistente);
            
            return true;
        } else {
            return false;
        }
    }
}
