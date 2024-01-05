package com.example.ordercraftnew.Model;

import java.time.LocalDate;

public class Commande {
    private int id;
    private Client client;
    private String address_livraison;
    private LocalDate date;
    private Etat etat_commande;

    public Commande() {
    }

    public Commande(Client client, String address_livraison, LocalDate date, Etat etat_commande) {
        this.client = client;
        this.address_livraison = address_livraison;
        this.date = date;
        this.etat_commande = etat_commande;
    }

    public Commande(int id, Client client, String address_livraison, LocalDate date, Etat etat_commande) {
        this.id = id;
        this.client = client;
        this.address_livraison = address_livraison;
        this.date = date;
        this.etat_commande = etat_commande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAddress_livraison() {
        return address_livraison;
    }

    public void setAddress_livrison(String address_livraison) {
        this.address_livraison = address_livraison;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Etat getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(Etat etat_commande) {
        this.etat_commande = etat_commande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", client=" + client +
                ", address_livraison='" + address_livraison + '\'' +
                ", date=" + date +
                ", etat_commande='" + etat_commande + '\'' +
                '}';
    }
}
