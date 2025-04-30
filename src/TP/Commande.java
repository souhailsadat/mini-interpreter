package TP;

public enum Commande {
    PRINT, LET;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
