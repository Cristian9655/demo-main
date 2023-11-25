package br.com.fiap.demo.gs.model;

public class Saude {
    private Long idSaude;
    private Integer stress;
    private String bmi;
    private String bloodPressure;
    private Integer heartRate;
    private Long idUser;

    public Saude() {
    }

    public Saude(Long idSaude, Integer stress, String bmi, String bloodPressure, Integer heartRate, Long idUser) {
        this.idSaude = idSaude;
        this.stress = stress;
        this.bmi = bmi;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.idUser = idUser;
    }

    public Long getIdSaude() {
        return idSaude;
    }

    public void setIdSaude(Long idSaude) {
        this.idSaude = idSaude;
    }

    public Integer getStress() {
        return stress;
    }

    public void setStress(Integer stress) {
        this.stress = stress;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}