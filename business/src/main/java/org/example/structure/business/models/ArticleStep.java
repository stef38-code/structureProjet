package org.example.structure.business.models;

public interface ArticleStep {
    PrixTtc nomProduit(String nomProduit);
    interface PrixTtc{
        Volume prixTtc(Double prixTtc);
    }
    interface Volume {
        Build volume(String volume);
    }
    interface Build{
        Article build();
    }
}
