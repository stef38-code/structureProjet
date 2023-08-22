package org.example.structure.business.models;

public class ArticleApi implements ArticleStep,ArticleStep.PrixTtc,ArticleStep.Volume,ArticleStep.Build{
    private  String nomProduit;
    private  Double prixTtc;
    private  String volume;

    ArticleApi() {
    }

    @Override
    public PrixTtc nomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
        return this;
    }

    @Override
    public Volume prixTtc(Double prixTtc) {
        this.prixTtc = prixTtc;
        return this;
    }

    @Override
    public Build volume(String volume) {
        this.volume = volume;
        return this;
    }

    @Override
    public Article build() {
        return new Article(nomProduit, prixTtc, volume);
    }
}
