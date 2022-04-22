# Data structures examples based on card games

## Installation

1. Forker git@github.com:mathieu-bergeron/ntro
    * donne git@github.com:MOI/ntro

1. Définir `cards` comme la branche principale de git@github.com:MOI/ntro
    * sur GitHub: 
        * view all branches
        * default branch => switch default branch
        * switch default branch
        * sélectionner `cards`
        * OK, I understand, OK, OK

1. Cloner git@github.com:mathieu-bergeron/ca.ntro.cards

1. Créer le fichier `scripts/ntro.git` avec dedans
    * git@github.com:MOI/ntro

1. Lancer le script

        $ sh scripts/init.sh

            Clonage dans 'ntro'...


## TODO

* `World2dCanvas` avec un Viewport
    1. on veut afficher plus de carte quand la fenêtre grandi
    1. on veut pouvoir zoomer la table de cartes

## Énoncés de projet

### Common et Playground

1. Affichage des cartes 
    * afficher des figures de carte arbitraire comme 145 coeur ou 13378 pique.
    * rassembler les cartes en paquet (si on a plusieurs cartes au même endroit, afficher un paquet)
1. Animations des cartes 
    * animation pour retourner la carte
    * animation pour étaler un paquer

### List

#### Jeu 1: rassembler les cartes par figure

1. Objectif
    * tous les 2 ensemble, tous les 3 ensembles, etc.

##### Méthode 1: quadratique

1. Choisir la première carte du paquet
    * s'il existe une pile avec cette figure, déposer la carte sur cette pile
    * sinon
        * chercher tout le paquet pour une carte avec la même figure
        * déposer la paire sur une nouvelle pile
1. Répéter

##### Méthode 2: linéaire 

1. Choisir la première carte du paquet
    * s'il existe une pile avec cette figure, déposer la carte sur cette pile
    * sinon créer une nouvelle pile
1. Répéter


### Map

#### Jeu 1: créer un arbre équilibré

#### Jeu 2: recherche binaire
