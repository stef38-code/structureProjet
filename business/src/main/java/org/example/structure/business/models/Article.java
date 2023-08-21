package org.example.structure.business.models;

public class Article {
    private final String nomProduit;
    private final Double prixTtc;
    private final String volume;

    Article(String nomProduit, Double prixTtc, String volume) {
        this.nomProduit = nomProduit;
        this.prixTtc = prixTtc;
        this.volume = volume;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public Double getPricTtc() {
        return prixTtc;
    }

    public String getVolume() {
        return volume;
    }
}
