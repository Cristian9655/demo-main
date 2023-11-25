package br.com.fiap.demo.gs.service;

import java.util.List;

import br.com.fiap.demo.gs.data.HabitosDao;
import br.com.fiap.demo.gs.model.Habitos;

public class HabitosService {

    static HabitosDao dao = new HabitosDao();

    public List<Habitos> listarHabitos() {
        return dao.listarHabitos();
    }

    public Habitos consultarHabitos(Long idHabitos) {
        return dao.consultarHabitos(idHabitos);
    }

    public boolean cadastraHabitos(Habitos habito) {
        if (!validar(habito)) return false;
        
        dao.cadastraHabitos(habito);
        return true;
    }

    private boolean validar(Habitos habito) {
        return habito != null
                && habito.getPhysicalActivity() != 0
                && habito.getDailySteps() != 0
                && habito.getIdUser() != null;
    }



    public boolean deletaHabitos(Long idHabitos) {
        Habitos habito = dao.consultarHabitos(idHabitos);

        if (habito != null) {
            dao.deletaHabitos(idHabitos);
            return true;
        } else {
            return false;
        }
    }

    public boolean atualizaHabitos(Long idHabitos, Habitos habitoAtualizado) {
        Habitos habitoExistente = dao.consultarHabitos(idHabitos);

        if (habitoExistente != null) {

            habitoExistente.setPhysicalActivity(habitoAtualizado.getPhysicalActivity());
            habitoExistente.setDailySteps(habitoAtualizado.getDailySteps());

            dao.atualizaHabitos(habitoExistente);

            return true;
        } else {
            return false;
        }
    }
}
