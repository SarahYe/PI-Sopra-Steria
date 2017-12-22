# PI-Sopra-Steria
Projet industriel 3A TELECOM Nancy

Execution :
Pour lancer le quiz, sur eclipse il faut executer la classe MainQuiz situe dans le package quiz.
Il est possible de lancer directement l'interface de parametrage en executant la classe MainParametres situe dans le package parametres.

Il est possible d'aller sur l'interface de parametrage en cliquant sur le bouton "parametrage" situe sur l'ecran d'accueil du quiz.
Depuis cette interface, il est possible d'ajouter, modifier ou supprimer les questions deja presentes.
Une fois que l'on enregistre (bouton "Enregistrer"), on est renvoye sur l'interface d'accueil du quiz.

Pour lancer le quiz, il suffit de cliquer sur "Jouer".
Lorsque l'on selectionne une ou plusieurs reponses, la justification associee s'affiche une fois que l'on clique sur le bouton "valider".
Si toutes les bonnes reponses, et uniquement elles, ont ete cochees, un bouton "question suivante" s'affiche, permettant de passer a la suivante.
NB : seule la premiere question s'affiche en boucle pour l'instant, bug en cours de correction

Le fichier XML est utilise pour stocker tout le contenu relatif au quiz, c'est a dire le contenu des differentes questions, reponses ainsi que leurs justifications associees.
L'interface du quiz lit le fichier xml pour afficher les differentes questions tandis que l'interface de parametrage s'occupe de remplir le fichier xml avec le contenu fourni par l'utilisateur.