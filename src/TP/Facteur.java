package TP;

import java.util.Map;

public class Facteur implements Evaluable{
    private String facteur;
    private double valeur;

    public Facteur(String facteur){
        this.facteur= facteur.trim();
    }
    public void evaluer(Map<String,Double> tableSymbole) throws ExpressionException{

        int longueurFacteur = facteur.length();
        int longueurChaine;
        String chaine = "";
        Element a;
        int parenthese = 0;
        int nbElements=0;

        if (facteur.charAt(longueurFacteur-1) =='^') throw new ExpressionException();
        for (int i=0; i <longueurFacteur; i++){
            if((facteur.charAt(i)=='^' && parenthese==0)||i==longueurFacteur-1) {
                if(i==longueurFacteur-1){
                    chaine= chaine + facteur.charAt(i);
                    if(facteur.charAt(i)==')') parenthese--;
                    if(facteur.charAt(i)=='(') parenthese++;
                    if (parenthese>0) throw new ParentheseOuvranteException();
                    else if(parenthese<0) throw new ParentheseFermanteException();
                }
                if (!chaine.isEmpty()) {
                    longueurChaine= chaine.length();
                    chaine = chaine.trim();
                    if (DIGITS.contains(Character.toString(chaine.charAt(0)))){
                        a = new Nombre(chaine);
                    }else if (chaine.charAt(0)=='('){
                        if(chaine.charAt(longueurChaine-1)==')'){
                            chaine = chaine.substring(1, longueurChaine-1).trim();
                            a = new Expression(chaine, true);
                        }else {
                            throw new ParentheseOuvranteException();
                        }
                    }else if (chaine.charAt(longueurChaine-1)==')'){
                        a = new AppelFonction(chaine);
                    }else{
                        a = new Variable(chaine);
                    }
                    a.evaluer(tableSymbole);

                    if (nbElements==0) valeur= a.getValeur();
                    else{
                        if(valeur==0 && a.getValeur()==0) throw new PuissanceException();
                        valeur= Math.pow(valeur,a.getValeur());
                    }

                }else throw new ExpressionException();
                chaine = "";
                nbElements++;
            }else {
                chaine= chaine + facteur.charAt(i);
                if(i<longueurFacteur-1 && facteur.charAt(i)=='(') parenthese++;
                else if(i<longueurFacteur-1 && facteur.charAt(i)==')'){
                    parenthese--;
                }
            }
        }
        if (parenthese!=0) throw new ParentheseOuvranteException();

    }

    public double getValeur() {
        return valeur;
    }
}
