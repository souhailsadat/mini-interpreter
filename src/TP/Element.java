package TP;

import java.util.Map;

public abstract class Element implements Evaluable{

    protected String chaineSaisie;
    protected double valeur;

    public abstract void evaluer(Map<String,Double> tableSymbole) throws ExpressionException;

    public double getValeur() {
        return valeur;
    }
}
