package br.com.fiap.demo.gs.model;

public class Habitos {

    private Long idHabitos;
    private int physicalActivity;
    private int dailySteps;  
    private Long idUser;

    public Habitos() {
    }

    public Habitos(long idHabitos, int physicalActivity, int dailySteps, long idUser) {
        this.idHabitos = idHabitos;
        this.physicalActivity = physicalActivity;
        this.dailySteps = dailySteps;
        this.idUser = idUser;
    }

    public Long getIdHabitos() {
        return idHabitos;
    }

    public void setIdHabitos(Long idHabitos) {
        this.idHabitos = idHabitos;
    }

    public int getPhysicalActivity() {
        return physicalActivity;
    }

    public void setPhysicalActivity(int physicalActivity) {
        this.physicalActivity = physicalActivity;
    }

    public Integer getDailySteps() {
        return dailySteps;
    }

    public void setDailySteps(Integer dailySteps) {
        this.dailySteps = dailySteps;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
