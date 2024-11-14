import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {
	public static void main(String[] args) {
		new UneFenetreSem();

		/*
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
		taskB.start();*/
	}
}

