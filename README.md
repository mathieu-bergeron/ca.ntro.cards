# Data structures examples based on card games

## TODO

* `World2dCanvas` avec un Viewport
    1. on veut afficher plus de carte quand la fenêtre grandi
    1. on veut pouvoir zoomer la table de cartes

## Énoncés de projet

### Playground

1. Affichage des cartes 
1. Animations des cartes (p.ex. animation pour retourner la carte)

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
