# Projet industriel Serious Game

Ce projet s'inscrit dans le cadre du PI mené par les étudiants de TELECOM Nancy en collaboration avec la Division Est de Sopra Steria. Il consiste à réaliser une application dite "blanche" permettant la création de serious games.

## Préréquis :

Environnement de travail :  Eclipse IDE for Java Developers

Version Java : 1.8

## Exécution :

Après avoir cloner le projet, il faut l'importer comme un projet existant sous Eclispe.

##### Lancer un quiz :
Dans le package *main*, exécutez la classe **MainQuiz** puis cliquez sur "Jouer".
Lorsque l'on sélectionne une ou plusieurs réponses, la justification associée s'affiche après avoir "Valider". Le passage à  la question suivante se fait après la sélection de toutes les bonnes réponses.

##### Lancer le paramétrage :
Dans le package *main*, exécuter la classe **MainParametres**.

*Fonctionnalités:*
  * Ajouter un bloc dans la liste
  * Supprimer un bloc selectionné dans la liste
  * Changer l'ordre des blocs dans la liste avec les boutons *up* et *down*
  * Afficher l'interface de configuration du bloc selectionné (seul l'interface du quiz est disponible actuellement)
  	* Ajouter une question
  	* Modifier une question existante
  	* Supprimer une question

Pour chaque question, il faut : un intitulé, les différents choix de réponse, une justification pour chacune des réponses (non obligatoire) ainsi que l'indication de ou des bonne(s) réponse(s).

##### Tester le jeu "chassez les intrus" utilisant Slick2D :
Dans le package *slickGames*, exécuter la classe **MainOddWordOutGame**.

*Fonctionnalités en cours de développement*

*Fonctionnalités actuelles :*
  * Affichage d'une question et défilement des réponses
  * Manipulation des éléments réponses (flèches droite et gauche du clavier uniquement !)
  * Disparition des réponses s'elles touchent un bord de la fenêtre de jeu

## Auteurs :

* Maël CLOUET
* Antoine SOCHALA
* Sarah YESUFU
