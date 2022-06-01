# Projet *Code les cartes*

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

            Clonage dans 'solutions'...

1. Basculer sur la branche `MOI`

        $ git checkout MOI
        $ cd solutions
        $ git checkout MOI

## À faire dans un nouveau projet

### Exécuter `generer` 

1. Exécuter au moins une fois au début

        $ sh gradlew foo_solution:generer

1. Ré-exécuter à chaque fois qu'on ajoute/change un cas de test

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

## Projets de familiarisation

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

## Projets rémunérés

1. Adrien: fusionsort
1. Marlond: arraylist

## Créer un nouveau projet

1. Le dépôt contient des répertoires `foo_*` avec des projets bidons

1. Pour créer p.ex. les projets `bar_*`, faire

        $ sh scripts/new_project.sh Bar bar

1. Devrait créer les répertoires suivants

        bar_procedure
        bar_efficiency
        solutions/bar_solution

1. Ouvrir `settings.gradle` et ajouter

        include 'bar_procedure'
        include 'bar_efficiency'
        include 'bar_solution'
        project(':bar_solution').projectDir = file('solutions/bar_solution')

1. Créer le projet Eclipse

        $ sh gradlew build 
        $ sh gradlew eclipse


1. Ouvrir le projet en Eclipse

1. Lancer l'application

        $ sh gradlew bar_solution:generer
        $ sh gradlew bar_solution:procedure
