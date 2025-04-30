package TP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Variable extends Element implements Evaluable {

    public Variable(String chaineSaisie) {
        this.chaineSaisie = chaineSaisie;
    }

    public void validerNom() throws ExpressionException{

        for (char car : chaineSaisie.toCharArray()) {
            if (!(LETTRES + DIGITS).contains(Character.toString(car))) {
                throw new ExpressionException();
            }
        }
        if (DIGITS.contains(Character.toString(chaineSaisie.charAt(0)))) {
            throw new VariableCommenceParChiffreException(chaineSaisie);
        }
        for (FonctionStandard fct : FonctionStandard.values()){
            if (chaineSaisie.equals(fct.toString())){
                throw new VariableNomException(chaineSaisie);
            }
        }
        for (Commande cmd : Commande.values()){
            if (chaineSaisie.equals(cmd.toString())){
                throw new VariableNomException(chaineSaisie);
            }
        }
    }

    public void evaluer(Map<String, Double> tableSymbole) throws ExpressionException {
        validerNom();
        if (tableSymbole.containsKey(chaineSaisie)) {
            valeur = tableSymbole.get(chaineSaisie);
        } else {
            throw new VariableNonDeclareeException(chaineSaisie);
        }
    }

    public String getNom() {
        return chaineSaisie;
    }
}
