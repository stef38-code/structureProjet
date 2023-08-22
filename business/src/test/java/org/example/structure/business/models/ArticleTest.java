package org.example.structure.business.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {
    @Test
    void createNewArticle_withBuilder() {
        String nomProduit = "Produit1";
        double prixTtc = 12.34;
        String volume = "Volume1";
        Article article = ArticleBuilder.get().nomProduit(nomProduit).prixTtc(prixTtc).volume(volume).build();
        assertThat(article).satisfies(p -> {
            assertThat(p.getNomProduit()).hasToString(nomProduit);
            assertThat(p.getPricTtc()).isEqualTo(prixTtc);
            assertThat(p.getVolume()).isEqualTo(volume);
        });
    }
}
