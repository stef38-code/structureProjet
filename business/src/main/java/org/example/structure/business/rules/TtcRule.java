package org.example.structure.business.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TtcRule {
    Logger log = LoggerFactory.getLogger(TtcRule.class);
    public double calcule(double prixHtc,int taux){
        if(prixHtc<=0){
            return 0.0d;
        }
        double tva = prixHtc * ((double) taux /100);
        log.debug("Le montant de la TVA est de {}€",tva);
        log.debug("Le prix TTC est donc de {}€",prixHtc+tva);
        return prixHtc+tva;
    }
}
