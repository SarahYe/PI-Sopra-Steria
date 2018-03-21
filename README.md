# Projet industriel Serious Game

Ce projet s'inscrit dans le cadre du PI mené par les étudiants de TELECOM Nancy en collaboration avec la Division Est de Sopra Steria. Il consiste Ã  réaliser une application adaptable et réutilisable permettant la création de serious games.

## Préréquis :

Environnement de travail :  Eclipse IDE for Java Developers, Scene Builder

Version Java : 1.8

## Technologies utilisées :

 * Java
 * Slick2D
 * FXML
 * XML

## Exécution :

Après avoir cloner le projet, il faut l'importer comme un projet existant sous Eclispe.

##### Lancer la configuration d'un serious game :
Dans le package *main*, exécuter la classe **MainParametres**.

*Fonctionnalités:*
  * Nommer le serious game
  * Ajouter les "blocs" nécessaires Ã  son scénario
  * Supprimer un bloc préalablement choisi
  * Configurer un bloc
  * Intervertir des blocs dans son scénario avec les boutons *up* et *down*
  * Tester le serious game scénarisé
  * Enregistrer le serious game (les fichiers xml sont placés dans le dossier Games/"nom du serious game")
  
Pour l'instant, les blocs paramétrables disponibles sont les suivants:
  * Quiz
  * Page explicative
  * Jeu de tri intitulé "Chassez l'intrus"

##### Paramétrer un quiz :
Dans le package *main*, exécutez la classe **ParametresQuiz**.
*Fonctionnalités:*
  * Ajouter une question
  * Supprimer  une question
  * Changer l'ordre des questions dans la liste avec les boutons *up* et *down*
  * Tester le quiz

Pour chaque question, il faut : un intitulé, les différents choix de réponse, une justification pour chacune des réponses (non obligatoire) ainsi que l'indication d'au moins une bonne réponse.

##### Lancer un quiz :
Dans le package *main*, exécutez la classe **MainQuiz** puis cliquez sur "Jouer".
Lorsque l'on sélectionne une ou plusieurs réponses, la justification associée s'affiche après avoir "Valider". Le passage Ã Â  la question suivante se fait après la sélection de toutes les bonnes réponses.

##### Paramétrer un jeu de tri :
Dans le package *main*, exécutez la classe **ParametresOddWordOutGame**.
*Fonctionnalités:*
  * Ajouter une question
  * Entrer une liste de mauvaises et de bonnes réponses
  * Supprimer une bonne ou mauvaise réponse entrée
  
##### Lancer le jeu de tri :
Dans le package *slickGames*, exécuter la classe **MainOddWordOutGame**.

##### Lancer une suite de bloc :
Dans le package *main*, exécuter la classe **MainChronologie**.
	* Le jeu executé va chercher les fichiers xml dans le dossier Games/test (par défaut).
	* Seuls les blocs "quiz" et "page explicative" fonctionnent dans la chronologie actuellement.

*Fonctionnement du jeu*

##### Executable jar :
Les fichier jar correspondent aux executables des différentes classes. 

## Crédits :

Crédits correspondant aux éléments audio (musiques et SFX) uniquement utilisés au sein du jeu de tri et du jeu de puzzle :

Music by Eric Matyas
www.soundimage.org
  
## Auteurs :

* Maël CLOUET
* Antoine SOCHALA
* Sarah YESUFU
