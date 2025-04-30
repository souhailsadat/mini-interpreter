package TP;

import javax.lang.model.type.NullType;
import java.util.Map;

public class Nombre extends Element implements Evaluable{

    public Nombre(String chaineSaisie){
        this.chaineSaisie = chaineSaisie;
    }

    public void evaluer(Map<String,Double> tableSymbole) throws ExpressionException{
        if (chaineSaisie.contains("."))
            throw new ExpressionException();
        try {
            valeur = Double.parseDouble(chaineSaisie);
        } catch (NumberFormatException e){
            throw new ExpressionException();
        }
    }

    @Override
    public double getValeur() {
        return valeur;
    }
}


