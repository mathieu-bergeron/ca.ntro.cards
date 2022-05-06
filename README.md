# Projet *Paquet de structures*

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

## Créer un nouveau projet

1. Le dépôt contient le répertoire `_foo` avec un projet bidon

1. Pour créer p.ex. le projet `Bar`, faire

        $ sh scripts/new_project.sh Bar bar

1. Devrait créer le répertoire `cards_bar` avec le nouveau projet

1. Ouvrir `settings.gradle` et ajouter

        include 'cards_bar'

1. Créer le projet Eclipse

        $ sh gradlew cards_bar:build
        $ sh gradlew cards_bar:eclipse


1. Ouvrir le projet en Eclipse

1. Lancer l'application

        $ sh gradlew cards_bar:local
    

## Énoncés de projet

### Projet `cards_freesort` (ADRIEN)

1. Trier les cartes librement
1. Afficher selon le cas:
    * Trié, bravo!
    * Pas encore trié. Rappel ♥ < ♦ < ♣ < ♠

### Projet `cards_naivesort`

1. Le jeu "force" l'utilisateur à trier une carte à la fois
1. Deux rangées: liste source ET liste triée
1. Les cartes sont face contre table et on peut en tourner seulemetn deux à la fois
    * une carte marquée d'une flèche (la carte la plus petite)
    * la carte courante (en cours d'évaluation dans une boucle)

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
