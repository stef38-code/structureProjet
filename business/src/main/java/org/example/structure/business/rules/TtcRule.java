package org.example.structure.business.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TtcRule {
    Logger log = LoggerFactory.getLogger(TtcRule.class);
    double calcule(double prixHtc,int taux){
        double tva = prixHtc * ((double) taux /100);
        log.debug("Le montant de la TVA est de {}€",tva);
        log.debug("Le prix TTC est donc de {}€",prixHtc+tva);
        return prixHtc+tva;
    }
}
