package TP;

import java.util.Map;

public class Terme implements Evaluable{
    private String terme;
    private double valeur;

    public Terme(String terme){
        this.terme = terme.trim();
    }
    public void evaluer(Map<String,Double> tableSymbole) throws ExpressionException{
        int parenthese = 0;
        int x = 0;
        int div = 0;
        Facteur a ;
        int nbElements =0;
        int longueurChaine = terme.length();
        String chaine = "";
        if(terme.charAt(longueurChaine-1) =='*' || terme.charAt(longueurChaine-1)=='/') throw new ExpressionException();

        for (int i=0; i<longueurChaine; i++){
            if((terme.charAt(i) == '*' || terme.charAt(i)=='/') && (parenthese==0)||(i==longueurChaine-1)){
                if(i==longueurChaine-1){
                    chaine = chaine+ terme.charAt(i);
                    if(terme.charAt(i)==')') parenthese--;
                    if(terme.charAt(i)=='(') parenthese++;
                    if (parenthese>0) throw new ParentheseOuvranteException();
                    else if(parenthese<0) throw new ParentheseFermanteException();
                }
                if(!chaine.isEmpty()){
                    chaine = chaine.trim();
                    a = new Facteur(chaine);
                    a.evaluer(tableSymbole);
                    if (nbElements==0) valeur= a.getValeur();
                    else{
                        if (x==1 && div==0) valeur = valeur * a.getValeur();
                        if (x==0 && div==1) {
                            if (a.getValeur()==0) throw new DivisionException();
                            else valeur = valeur / a.getValeur();
                        }
                    }
                    if(terme.charAt(i)=='*'){
                        x = 1;
                        div = 0;
                    }else if(terme.charAt(i)=='/'){
                        x = 0;
                        div = 1;
                    }
                    chaine = "";
                    nbElements++;

                }
                else throw new ExpressionException();

            }else{
                chaine = chaine+ terme.charAt(i);
                if(i<longueurChaine-1 && terme.charAt(i)=='(') parenthese++;
                else if(i<longueurChaine-1 && terme.charAt(i)==')'){
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
