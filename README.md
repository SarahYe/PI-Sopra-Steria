# Projet industriel Serious Game

Ce projet s'inscrit dans le cadre du PI men� par les �tudiants de TELECOM Nancy en collaboration avec la Division Est de Sopra Steria. Il consiste à r�aliser une application adaptable et r�utilisable permettant la cr�ation de serious games.

## Pr�r�quis :

Environnement de travail :  Eclipse IDE for Java Developers, Scene Builder

Version Java : 1.8

## Technologies utilis�es :

 * Java
 * Slick2D
 * FXML
 * XML

## Ex�cution :

Apr�s avoir cloner le projet, il faut l'importer comme un projet existant sous Eclispe.

##### Lancer la configuration d'un serious game :
Dans le package *main*, ex�cuter la classe **MainParametres**.

*Fonctionnalit�s:*
  * Nommer le serious game
  * Ajouter les "blocs" n�cessaires à son sc�nario
  * Supprimer un bloc pr�alablement choisi
  * Configurer un bloc
  * Intervertir des blocs dans son sc�nario avec les boutons *up* et *down*
  * Tester le serious game sc�naris�
  * Enregistrer le serious game (les fichiers xml sont plac�s dans le dossier Games/"nom du serious game")
  
Pour l'instant, les blocs param�trables disponibles sont les suivants:
  * Quiz
  * Page explicative
  * Jeu de tri intitul� "Chassez l'intrus"

##### Param�trer un quiz :
Dans le package *main*, ex�cutez la classe **ParametresQuiz**.
*Fonctionnalit�s:*
  * Ajouter une question
  * Supprimer  une question
  * Changer l'ordre des questions dans la liste avec les boutons *up* et *down*
  * Tester le quiz

Pour chaque question, il faut : un intitul�, les diff�rents choix de r�ponse, une justification pour chacune des r�ponses (non obligatoire) ainsi que l'indication d'au moins une bonne r�ponse.

##### Lancer un quiz :
Dans le package *main*, ex�cutez la classe **MainQuiz** puis cliquez sur "Jouer".
Lorsque l'on s�lectionne une ou plusieurs r�ponses, la justification associ�e s'affiche apr�s avoir "Valider". Le passage à  la question suivante se fait apr�s la s�lection de toutes les bonnes r�ponses.

##### Param�trer un jeu de tri :
Dans le package *main*, ex�cutez la classe **ParametresOddWordOutGame**.
*Fonctionnalit�s:*
  * Ajouter une question
  * Entrer une liste de mauvaises et de bonnes r�ponses
 �* Supprimer une bonne ou mauvaise r�ponse entr�e
 �
##### Lancer le jeu de tri :
Dans le package *slickGames*, ex�cuter la classe **MainOddWordOutGame**.

##### Lancer une suite de bloc :
Dans le package *main*, ex�cuter la classe **MainChronologie**.
	* Le jeu execut� va chercher les fichiers xml dans le dossier Games/test (par d�faut).
	* Seuls les blocs "quiz" et "page explicative" fonctionnent dans la chronologie actuellement.

*Fonctionnement du jeu*

##### Executable jar :
Les fichier jar correspondent aux executables des diff�rentes classes. 

## Cr�dits :

Cr�dits correspondant aux �l�ments audio (musiques et SFX) uniquement utilis�s au sein du jeu de tri et du jeu de puzzle :

Music by Eric Matyas
www.soundimage.org
  
## Auteurs :

* Ma�l CLOUET
* Antoine SOCHALA
* Sarah YESUFU
