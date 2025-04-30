package TP;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreteur {
    private Map<String,Double> tableSymbole;
    private LigneCommande ligneCommande;

    public Interpreteur(){
        tableSymbole = new HashMap<String, Double>();
    }

    public void lancement(){
        System.out.println("Entrez vos commandes. Tapez end pour terminer votre programme.");
        System.out.println("");
        System.out.println("Une commande doit être de la forme");
        System.out.println("");
        System.out.println("let <variable> = <expression>");
        System.out.println("ou");
        System.out.println("print <expression>");
        System.out.println("");
        System.out.println("");
    }

    public void boucleSaisi(){
        Scanner sc = new Scanner(System.in);
        String ligneSaisie;
        System.out.print("> ");
        while (!((ligneSaisie = sc.nextLine()).trim().equals("end"))) {
            ligneCommande = new LigneCommande(ligneSaisie);
            try {
                ligneCommande.analyser(tableSymbole);
                ligneCommande.executer(tableSymbole);
            } catch (CommandeException exc) {
                System.out.println("Erreur : " + exc);
            }
            System.out.println();
            System.out.print("> ");
        }
        System.out.print("Fin du programme");
    }
}
