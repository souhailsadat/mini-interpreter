package TP;

import java.security.spec.ECParameterSpec;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Expression extends Element implements Evaluable{

    private boolean sous_expression;

    public Expression(String chaineSaisie){
        this.chaineSaisie= chaineSaisie.trim();
        sous_expression = false;
    }

    public Expression(String chaineSaisie, boolean sous_expression){
        this.chaineSaisie= chaineSaisie.trim();
        this.sous_expression = sous_expression;
    }

    public void evaluer(Map<String,Double> tableSymbole) throws ExpressionException{
        int parenthese = 0;
        int plus = 1;
        int moins = 0;
        Terme a ;
        int longueurChaine = chaineSaisie.length();
        if (longueurChaine == 0)
            throw new ExpressionException();
        String chaine = "";
        if(chaineSaisie.charAt(0)== '+' || chaineSaisie.charAt(longueurChaine-1) =='+' || chaineSaisie.charAt(longueurChaine-1)=='-') throw new ExpressionException();

        for (int i=0; i<longueurChaine; i++){
            if((chaineSaisie.charAt(i) == '+' || chaineSaisie.charAt(i)=='-') && (parenthese==0)||(i==longueurChaine-1)){
                if(i==longueurChaine-1){
                    chaine = chaine+ chaineSaisie.charAt(i);
                    if(chaineSaisie.charAt(i)==')') parenthese--;
                    if(chaineSaisie.charAt(i)=='(') parenthese++;
                    if (parenthese>0) throw new ParentheseOuvranteException();
                    else if(parenthese<0) throw new ParentheseFermanteException();
                }
                if(!chaine.isEmpty()){
                    chaine = chaine.trim();
                    a = new Terme(chaine);
                    a.evaluer(tableSymbole);
                    //System.out.println("terme : "+chaine+" = "+a.getValeur()+", plus = "+plus+", moins = "+moins);
                    if (plus==1) valeur = valeur + a.getValeur();
                    if (moins==1) valeur = valeur - a.getValeur();

                }
                else if (i != 0 ) throw new ExpressionException();
                chaine = "";
                if(chaineSaisie.charAt(i)=='+'){
                    plus = 1;
                    moins = 0;
                }else if(chaineSaisie.charAt(i)=='-'){
                    plus = 0;
                    moins = 1;
                }
            }else{
                chaine = chaine+ chaineSaisie.charAt(i);
                if(i<longueurChaine-1 && chaineSaisie.charAt(i)=='(') parenthese++;
                else if(i<longueurChaine-1 && chaineSaisie.charAt(i)==')'){
                    parenthese--;
                    if(parenthese<0 && sous_expression == false) throw new ParentheseFermanteException();
                }
            }
        }
    }
}
