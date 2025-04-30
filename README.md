# Mini Interpreter for a Pseudo-Programming Language

A simple interpreter for a custom pseudo-language, Written in Java and developed as part of an Object-Oriented Programming course project.

## Features

- Supports two commands:  
  - `let` to assign values to variables.  
  - `print` to display variable values or expression results.  
- Evaluates mathematical expressions with operators (`+`, `-`, `*`, `/`, `^`) and standard functions (`sin`, `cos`, `tan`, `abs`, `sqrt`, `log`).  
- Handles error detection (undeclared variables, syntax errors).  

## Usage Examples

```Entrez vos commandes. Tapez end pour terminer votre programme.
Une commande doit être de la forme
let <variable> = <expression>
ou
print <expression>
> let x = 2
Ok
> let y = -5
Ok
> print sin(x+ abs(y))*sqrt(9)
La valeur est : 0.365608030215442
> print z
Erreur : variable z non déclarée
> let z = 7*
Erreur : Expression erronée
> print x*(y+4)-(log(10)
Erreur : parenthèse fermante manquante
> end
Fin du programme
```

## Diagram Class

This project follows **object-oriented programming** principles, as demonstrated by the class diagram below:

![diagramme](https://github.com/user-attachments/assets/fbf3f2af-f9bb-4181-8368-e77b8a353b6a)

