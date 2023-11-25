package br.com.fiap.demo.gs.model;

public class Sono {
	private Long idSono;
	private Double sleepDuration;
	private Integer sleepQuality;
	private Long idUser;
	
	public Sono() {
	}
	
	public Sono(Long idSono, Double sleepDuration, Integer sleepQuality, Long idUser) {
	    this.idSono = idSono;
	    this.sleepDuration = sleepDuration;
	    this.sleepQuality = sleepQuality;
	    this.idUser = idUser;
	}
	
	public Long getIdSono() {
	    return idSono;
	}
	
	public void setIdSono(Long idSono) {
	    this.idSono = idSono;
	}
	
	public Double getSleepDuration() {
	    return sleepDuration;
	}
	
	public void setSleepDuration(Double sleepDuration) {
	    this.sleepDuration = sleepDuration;
	}
	
	public Integer getSleepQuality() {
	    return sleepQuality;
	}
	
	public void setSleepQuality(Integer sleepQuality) {
	    this.sleepQuality = sleepQuality;
	}
	
	public Long getIdUser() {
	    return idUser;
	}
	
	public void setIdUser(Long idUser) {
	    this.idUser = idUser;
	}
}
