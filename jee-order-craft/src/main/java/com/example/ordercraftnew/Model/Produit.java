package com.example.ordercraftnew.Model;

public class Produit {
    private int id;
    private String name;
    private String description;
    private double prix;
    private int quantite_produit;

    public Produit() {
    }

    public Produit(int id, String name, String description, double prix, int quantite_produit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.quantite_produit = quantite_produit;
    }

    public Produit(String name, String description, double prix, int quantite_produit) {
        this.name = name;
        this.description = description;
        this.prix = prix;
        this.quantite_produit = quantite_produit;
    }

    public int getQuantite_produit() {
        return quantite_produit;
    }

    public void setQuantite_produit(int quantite_produit) {
        this.quantite_produit = quantite_produit;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                '}';
    }
}
