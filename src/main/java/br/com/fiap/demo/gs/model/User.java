package br.com.fiap.demo.gs.model;

public class User {
    private Long idUser;
    private String nome;
    private String senha;
    private String email;
    private String gender;
    private int age;
    private String occupation;

    public User() {
    }

    public User(Long idUser, String nome, String senha, String email, String gender, int age, String occupation) {
        this.idUser = idUser;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.occupation = occupation;
    }
    
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}