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

Pour cet exercice, nous avons modifié UneFenetre afin d'utiliser un GridLayout et afficher deux instances de UnMobile avec leurs boutons respectifs.

#### Changement de la disposition de la fenêtre
Déjà, j'ai remplacé BorderLayout par un GridLayout avec 2 lignes et 2 colonnes, ce qui permet d’afficher chaque mobile et son bouton dans une grille. 

#### Ajout d'un deuxième mobile
Ensuite, j'ai ajouté les deux mobiles et de leurs boutons avec deux instances de UnMobile (sonMobile1 et sonMobile2) ont été créées avec leurs propres boutons de contrôle (btnControl1 et btnControl2). Chaque bouton est positionné au-dessus de son mobile correspondant.

#### Gestion des boutons
Enfin, Chaque bouton utilise un ActionListener distinct pour contrôler l’animation de son mobile en appelant suspend() et resume() sur son propre thread (laThread1 et laThread2). Ce système permet d’arrêter et de relancer chaque mobile de manière indépendante.

Avec cette configuration, l’interface dispose les éléments dans une grille et permet de gérer les deux mobiles individuellement.

---
## TP 2 - Affichage synchronisé

Dans le cadre de ce TP, nous avons exploré l'utilisation de threads en Java pour synchroniser deux tâches (TA et TB) en utilisant la classe Affichage pour gérer l'affichage à l'écran. Le but est de garantir l'exclusion mutuelle entre les threads, afin que leurs messages s'affichent dans des séquences prédéfinies (AAABB ou BBAAA) et non de manière entrelacée (ABABA). Pour cela, nous avons utilisé des sémaphores binaires pour contrôler l'accès aux sections critiques des threads.

### Analyse du code déjà présent

- **Classe Affichage :** La classe Affichage hérite de Thread, et chaque instance prend un string à afficher. La méthode run parcourt chaque caractère et l'affiche, avec une pause de 100 millisecondes entre chaque caractère pour simuler un délai dans l'affichage.

- **Classe semaphoreBinaire** : Le rôle de cette classe est de contrôler l'accès à la section critique, c'est-à-dire l'endroit où une tâche peut afficher son message sans interruption par une autre. Le sémaphore binaire est modélisé pour avoir deux états : "libre" ou "occupé".

#### Incrémentation et Décrémentation du Sémaphore :

Lorsqu'un sémaphore binaire est initialisé, il reçoit une valeur (1 ou 0). Cette valeur représente la disponibilité de la section critique. Une valeur de 1 indique que la section critique est libre et une valeur de 0 indique qu'elle est occupée.

- **Décrémentation :** Lorsqu'une tâche veut entrer en section critique, elle décrémente la valeur du sémaphore. Si la valeur atteint 0, la tâche obtient l'accès à la section critique. Toute autre tâche essayant de décrémenter à 0 sera bloquée jusqu’à ce que le sémaphore soit réinitialisé.

- **Incrémentation :** Lorsqu’une tâche a terminé son travail en section critique, elle incrémente la valeur. Cela libère la section critique et permet à une autre tâche en attente d'y entrer.

### Classe Main

L'objectif de cette classe est de s'assurer que les messages "AAA" et "BB" générés respectivement par les tâches taskA et taskB s'affichent de manière cohérente, en suivant des séquences de type AAABB ou BBAAA.

```java
public class Main {
	public static void main(String[] args) {
		semaphoreBinaire semaphore = new semaphoreBinaire(1);

		Thread taskA = new Thread(() -> {
			try {
				semaphore.syncWait();
				System.out.println("j'entre en section critique - Task A");
				new Affichage("AAA").run();
				System.out.println("je sors de section critique - Task A");
			} finally {
				semaphore.syncSignal();
			}
		});

		Thread taskB = new Thread(() -> {
			try {
				semaphore.syncWait();
				System.out.println("j'entre en section critique - Task B");
				new Affichage("BB").run();
				System.out.println("je sors de section critique - Task B");
			} finally {
				semaphore.syncSignal();
			}
		});

		taskA.start();
		taskB.start();
	}
}
```

La classe Main commence par créer une instance de semaphoreBinaire initialisée avec une valeur de 1, indiquant que la section critique est disponible. Ce sémaphore binaire sert à réguler l'accès à la section critique en autorisant une seule tâche à la fois.

Ensuite, on crée deux tâches, taskA et taskB, sous forme d'objets Thread. Ces tâches utilisent des instances de la classe Affichage pour afficher "AAA" et "BB".

Chaque tâche appelle semaphore.syncWait() avant d’accéder à la section critique qui bloque la tâche si la section critique est occupée. Une fois en section critique, on utilise new Affichage(texte).run() pour afficher son message. Après avoir affiché le message, on appelle semaphore.syncSignal() qui libère la section critique pour permettre à l’autre tâche d’y accéder. Les threads taskA et taskB sont démarrés en même temps, et grâce au sémaphore binaire, seule l'une des deux peut entrer en section critique à la fois, ce qui permet d’afficher les messages sans entrelacement indésirable.

## TP2 (bis) - Application des sémaphores sur les mobiles du TP1

Dans cette partie, le but est d'adapter les mobiles pour qu'ils traversent une zone critique au centre de la fenêtre. La zone critique ne peut contenir qu'un mobile à la fois, et la synchronisation est gérée par un sémaphore binaire.

Dans notre cas, la zone critique est définie comme le second tiers de la fenêtre. Un sémaphore binaire assure que cette zone n'est occupée que par un seul mobile à la fois et stoppera les autres mobiles jusqu'à ce que la zone soit libérée.

Ceux-ci appellent syncWait() lorsqu’ils arrivent au début de la zone critique, ce qui les autorise à entrer si la zone est libre. Ensuite, lorsqu'ils sortent de la zone critique, ils appellent syncSignal() pour libérer la zone, permettant à un autre mobile d’y entrer.
