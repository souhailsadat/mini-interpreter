package TP;

class CommandeException extends Exception{
    public String toString() { return "Commande erronée"; }
}

class CommandeInexistanteException extends CommandeException{
    private String nom;
    public CommandeInexistanteException(String nom){this.nom = nom;}
    public String toString() { return "Commande " + nom + " inexistante. Les seules commandes possibles sont: print, let et end"; }
}

class ExpressionException extends CommandeException{
    public String toString() { return "Expression erronée"; }
}

class VariableException extends ExpressionException{
    protected String nom;
    public VariableException(String nom){this.nom = nom;}
}

class VariableNonDeclareeException extends VariableException{
    public VariableNonDeclareeException(String nom){super(nom);}
    public String toString() { return "variable " + nom + " non déclarée"; }
}

class VariableNomException extends VariableException{
    public VariableNomException(String nom){super(nom);}
    public String toString() { return "'"+ nom + "' ne peut pas être un nom de variable"; }
}

class VariableCommenceParChiffreException extends VariableException{
    public VariableCommenceParChiffreException(String nom){super(nom);}
    public String toString() { return "le nom de variable '" + nom + "' ne doit pas commencer par un chiffre"; }
}

class FonctionException extends ExpressionException{
    protected String nom;
    public FonctionException(){};
    public FonctionException(String nom){this.nom = nom;}
}

class FonctionNonExistanteException extends FonctionException{
    public FonctionNonExistanteException(String nom){super(nom);}
    public String toString() { return "Fonction " + nom + " non existante"; }
}

class LogException extends FonctionException{
    public String toString() { return "Fonction log nécessite un argument strictement positif"; }
}

class SqrtException extends FonctionException{
    public String toString() { return "Fonction sqrt nécessite un argument positif"; }
}

class NombreException extends ExpressionException{
    public String toString() { return "Le nombre contient des caractères spéciaux"; }
}

class ParentheseFermanteException extends ExpressionException{
    public String toString() { return "Parenthèse ouvrante manquante"; }
}

class ParentheseOuvranteException extends ExpressionException{
    public String toString() { return "Parenthèse fermante manquante"; }
}

class PuissanceException extends ExpressionException{
    public String toString() { return "Math Error 0^0"; }
}

class DivisionException extends ExpressionException{
    public String toString() { return "Division par 0"; }
}