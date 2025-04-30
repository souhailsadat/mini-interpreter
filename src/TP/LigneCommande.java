package TP;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class LigneCommande {
    private String ligneSaisie;
    private Commande commande;
    private Expression expression;
    private Variable variable;

    public LigneCommande(String ligneSaisie){this.ligneSaisie = ligneSaisie;}

    public void analyser(Map<String,Double> tableSymbole) throws CommandeException{
        StringTokenizer tok = new StringTokenizer(ligneSaisie," ");
        int nb = tok.countTokens();
        if (nb == 0)
            throw new CommandeException();
        String cmd = tok.nextToken();
        if (nb == 1)
        {
            if (cmd.equals(Commande.LET.toString()) || cmd.equals(Commande.PRINT.toString()))
                throw new CommandeException();
            else
                throw new CommandeInexistanteException(cmd);
        }
        if (cmd.equals(Commande.PRINT.toString())){
            commande = Commande.PRINT;
            expression = new Expression(tok.nextToken("\n").trim());
            expression.evaluer(tableSymbole);
            return;
        }
        if (cmd.equals(Commande.LET.toString())){
            commande = Commande.LET;
            try{
                String var = tok.nextToken("=").trim();
                if (var.isEmpty())
                    throw new CommandeException();
                variable = new Variable(var);
                variable.validerNom();
                String exp = tok.nextToken("\n").replaceFirst("=","").trim();
                if (exp.isEmpty())
                    throw new CommandeException();
                expression = new Expression(exp);
                expression.evaluer(tableSymbole);
            }
            catch (NoSuchElementException e){
                throw new CommandeException();
            }
            return;
        }
        throw new CommandeInexistanteException(cmd);
    }

    public void executer(Map<String,Double> tableSymbole){
        switch (commande){
            case PRINT:
                System.out.println("La valeur est : " + expression.getValeur());
                break;
            case LET:
                tableSymbole.put(variable.getNom(), expression.getValeur());
                System.out.println("Ok");
        }
    }
}
