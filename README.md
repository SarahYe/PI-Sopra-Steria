# Projet industriel Serious Game

Ce projet s'inscrit dans le cadre du PI men� par les �tudiants de TELECOM Nancy en collaboration avec la Division Est de Sopra Steria. Il consiste � r�aliser une application dite "blanche" permettant la cr�ation de serious games.

## Pr�r�quis :

Environnement de travail :  Eclipse IDE for Java Developers

Version Java : 1.8

## Ex�cution :

Apr�s avoir cloner le projet, il faut l'importer comme un projet existant sous Eclispe.

##### Lancer un quiz :
Dans le package *main*, ex�cutez la classe **MainQuiz** puis cliquez sur "Jouer".
Lorsque l'on s�lectionne une ou plusieurs r�ponses, la justification associ�e s'affiche apr�s avoir "Valider". Le passage � la question suivante se fait apr�s la s�lection de toutes les bonnes r�ponses.

##### Lancer le param�trage :
Dans le package *main*, ex�cuter la classe **MainParametres**.

*Fonctionnalit�s:*
  * Ajouter un bloc dans la liste
  * Supprimer un bloc selectionn� dans la liste
  * Changer l'ordre des blocs dans la liste avec les boutons *up* et *down*
  * Afficher l'interface de configuration du bloc selectionn� (seul l'interface du quiz est disponible actuellement)
  	* Ajouter une question
  	* Modifier une question existante
  	* Supprimer une question

Pour chaque question, il faut : un intitul�, les diff�rents choix de r�ponse, une justification pour chacune des r�ponses (non obligatoire) ainsi que l'indication de ou des bonne(s) r�ponse(s).

##### Tester le jeu "chassez les intrus" utilisant Slick2D :
Dans le package *slickGames*, ex�cuter la classe **MainOddWordOutGame**.

*Fonctionnalit�s en cours de d�veloppement*

*Fonctionnalit�s actuelles :*
  * Affichage d'une question et d�filement des r�ponses
  * Manipulation des �l�ments r�ponses (fl�ches droite et gauche du clavier uniquement !)
  * Disparition des r�ponses s'elles touchent un bord de la fen�tre de jeu

## Auteurs :

* Ma�l CLOUET
* Antoine SOCHALA
* Sarah YESUFU
