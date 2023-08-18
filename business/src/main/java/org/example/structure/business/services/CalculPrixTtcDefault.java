package org.example.structure.business.services;

import org.example.structure.business.adapters.in.CalculPrixTtc;
import org.example.structure.business.rules.TtcRule;

public class CalculPrixTtcDefault implements CalculPrixTtc {
    TtcRule ttcRule = new TtcRule();
    @Override
    public double apply(double prixHtc, int taux) {


        return ttcRule.calcule(prixHtc, taux);
    }
}
