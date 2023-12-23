package com.example.ordercraftnew.Model;

public class CommandeProduit {
    private int id;
    private Commande commande;
    private Produit produit;
    private int quantite;
    private double prix_total;

    public CommandeProduit() {
    }

    public CommandeProduit(int id, Commande commande, Produit produit,int quantite,double prix_total)
    {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.quantite = quantite;
        this.prix_total = prix_total;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "CommandeProduit{" +
                "id=" + id +
                ", commande=" + commande +
                ", produit=" + produit +
                ", quantite=" + quantite +
                ", prix_total=" + prix_total +
                '}';
    }
}
