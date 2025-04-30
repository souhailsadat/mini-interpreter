package TP;

import java.util.Map;

public interface Evaluable {
    String LETTRES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String DIGITS = "0123456789";
    void evaluer(Map<String,Double> tableSymbole) throws ExpressionException;
}
