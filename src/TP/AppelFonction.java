package TP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AppelFonction extends Element implements Evaluable{

    public AppelFonction(String chaineSaisie){this.chaineSaisie = chaineSaisie;}

    public void evaluer(Map<String,Double> tableSymbole) throws ExpressionException{
        int delim = chaineSaisie.indexOf('(');
        String nom = chaineSaisie.substring(0,delim);
        nom = nom.trim();
        String chaine = chaineSaisie.substring(delim+1,chaineSaisie.length()-1);
        Expression param = new Expression(chaine.trim(), true);

        if (nom.equals(FonctionStandard.SIN.toString())){
            param.evaluer(tableSymbole);
            valeur = Math.sin(param.getValeur());
            return;
        }
        if (nom.equals(FonctionStandard.COS.toString())){
            param.evaluer(tableSymbole);
            valeur = Math.cos(param.getValeur());
            return;
        }
        if (nom.equals(FonctionStandard.TAN.toString())){
            param.evaluer(tableSymbole);
            valeur = Math.tan(param.getValeur());
            return;
        }
        if (nom.equals(FonctionStandard.ABS.toString())){
            param.evaluer(tableSymbole);
            valeur = Math.abs(param.getValeur());
            return;
        }
        if (nom.equals(FonctionStandard.SQRT.toString())){
            param.evaluer(tableSymbole);
            if (param.getValeur() < 0){
                throw new SqrtException();
            }
            valeur = Math.sqrt(param.getValeur());
            return;
        }
        if (nom.equals(FonctionStandard.LOG.toString())){
            param.evaluer(tableSymbole);
            if (param.getValeur() <= 0){
                throw new LogException();
            }
            valeur = Math.log(param.getValeur());
            return;
        }
        throw new FonctionNonExistanteException(nom);
    }
}
