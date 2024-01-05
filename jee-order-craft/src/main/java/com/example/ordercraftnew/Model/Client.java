package com.example.ordercraftnew.Model;

public class Client {
    private int id;
    private String name;
    private String email;
    private String ville;

    public Client() {
    }

    public Client(int id, String name, String email, String ville) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.ville = ville;
    }

    public Client(String name, String email, String ville) {
        this.name = name;
        this.email = email;
        this.ville = ville;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
