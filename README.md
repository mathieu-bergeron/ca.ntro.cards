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

## À faire dans un nouveau projet

### Exécuter `generer` au moins une fois

        $ sh gradlew foo_solution:generer

Plus à chaque fois qu'on change les cas de tests

### Dans `foo_procedure`

1. Remplir et compléter la classe `FooTestCaseDatabase`
    * méthode `describeTestCasesToGenerate`
        * pour décrire les cas de tests à générer

1. Remplir et compléter la classe `FooCardsModel`
    * remplir les méthodes utilitaires
    * remplir l'analyse du modèle à l'écran
        * `acceptManualModel`
    * afficher le modèle
        * `updateViewDataImpl`
    * créer des cas des tests
        * `initializeAsTestCases`

1. Dans `FooGenerateTestCases`
    * `shouldWriteJson` pour écrire les cas de tests en Json

1. Compléter la classe `FooProcedureWorld2d`
    * intepréter les `Card2d` pour mettre à jour le modèle
    * `buildAndSendManualModel`

1. Renommer `FooProcedureApp`

### Dans `foo_solution`

1. Renommer `MonFooCardsModel`

## Énoncés de projet

### Projets de familiarisation

1. Ajouter un attribut au modèle TriLibre
    * attribut: boolean trie
    * afficher la valeur de l'attribut (donc afficher true quand c'est trié)

1. Compléter naivesort
    * déplacer la prochaine petite carte dans la liste cible au bon endroit
    * sinon, refuser le déplacement et laisser la carte où elle est


1. Petits objectifs supplémentaires de familiaristion
    * ajouter la vitesse aux options
    * afficher les indices
    * améliorer l'apparence p.ex. des variables
    * afficher des cartes dans la vue des variales, etc.

### Projets rémunérés

1. Adrien: fusionsort
1. Marlond: arraylist
