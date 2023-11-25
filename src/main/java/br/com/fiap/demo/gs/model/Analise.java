package br.com.fiap.demo.gs.model;

public class Analise {

    private Long idAnalise;
    private int sleepDisorder;
    private String diagnostic;
    private Long idUser;

    public Analise() {
    }

    public Analise(Long idAnalise, int sleepDisorder, String diagnostic, Long idUser) {
        this.idAnalise = idAnalise;
        this.sleepDisorder = sleepDisorder;
        this.diagnostic = diagnostic;
        this.idUser = idUser;
    }

    public Long getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(Long idAnalise) {
        this.idAnalise = idAnalise;
    }

    public int getSleepDisorder() {
        return sleepDisorder;
    }

    public void setSleepDisorder(int sleepDisorder) {
        this.sleepDisorder = sleepDisorder;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}

