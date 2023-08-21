package org.example.structure.business.services;

import org.example.structure.business.adapters.in.BorneInformation;
import org.example.structure.business.models.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BorneInformationTest {
    @Test
    @DisplayName("Borme Information: Passage du premier article")
    void premierPassageTest(){
        /*Création de la classe de l'usage
        * dans le package org.example.structure.business.services
        * sans oublier que cette classe doit implémenter une interface
        * qui sera org.example.structure.adapter.in
        */
        BorneInformation borneInformation = new BorneInformationDefault();
        /* creation de la méthode lecture(String codeProduit)
        * elle doit retourner les informations de l'article suivantes :
        * - Nom du produit
        * - Prix (ttc) en euro
        * - Volume
        * Cet objet sera dans org.example.structure.models, on utilisera API fluent(cf: doc/APIfluent.adoc) pour la création
        *
         */
        Article article = borneInformation.lecture("05948707");


    }
}
