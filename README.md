# Programmation avancée - BUT 3 - 2024
> Réalisé par Ewen GILBERT  
> *La rédaction et la réalisation des TP ont été réalisés avec l'aide d'outils d'intelligence artificielle.*

## TP 1 - Les Treads Java

### Exercice 1
La classe UnMobile représente un objet mobile qui se déplace horizontalement dans une interface graphique, en utilisant un thread pour contrôler son mouvement. L'animation est réalisée en exécutant une boucle dans la méthode run(), qui appelle repaint() à intervalles réguliers pour redessiner le mobile à sa nouvelle position.

Dans la méthode run(), une boucle itère jusqu'à ce que le mobile atteigne le bord droit de la fenêtre. À chaque itération :

- sonDebDessin (la position actuelle du mobile) est incrémenté de sonPas, ce qui déplace le mobile vers la droite.
- La méthode repaint() est appelée pour redessiner le mobile à sa nouvelle position.
- La méthode sleep() permet de suspendre temporairement le thread, contrôlant ainsi la vitesse du mouvement.

Cela nous permet de simuler un déplacement du mobile dans l'interface.

Maintenant, on souhaite mettre en place un moyen de faire faire un aller retour à notre mobile et pour celà, je modifie la méthode run()

On utilise deux boucles successives : La première boucle for incrémente la position sonDebDessin pour faire avancer le mobile de gauche à droite. Une fois le bord droit atteint, la deuxième boucle for prend le relais pour décrémenter sonDebDessin et ramener le mobile vers la gauche.

Appel de repaint() et sleep() : À chaque itération, repaint() redessine le mobile à sa nouvelle position, tandis que Thread.sleep(sonTemps) crée une pause pour contrôler la vitesse de déplacement.

Cette structure permet au mobile de rebondir naturellement entre les côtés gauche et droit de la fenêtre, créant un mouvement fluide et continu sans nécessiter de variable pour suivre la direction.

### Exercice 2

Dans cette étape, nous avons ajouté un bouton pour contrôler l'animation du mobile, en utilisant les méthodes suspend() et resume() de la classe Thread.

- Ajout du bouton sonBouton : Le bouton est placé en bas de la fenêtre (BorderLayout.SOUTH). Par défaut, son texte est "Stop", indiquant que l'animation est en cours.
- Gestion de l’état du Thread : Un ActionListener sur le bouton contrôle l’état du Thread.
  - Si le mobile est en mouvement (isRunning est true), le bouton arrête l'animation en appelant laThread.suspend(), puis change le texte du bouton en "Start".
  - Si l’animation est arrêtée, un clic relance le mouvement avec laThread.resume() et remet le texte du bouton à "Stop".

*Notons que les expressions suspend() et resume() sont dépréciées.*

Ce bouton permet de mettre en pause et de relancer l'animation du mobile, offrant un contrôle interactif du déplacement du mobile.

### Exercice 3

