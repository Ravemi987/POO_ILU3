package testsFonctionnels;

import cartes.Carte;
import cartes.JeuDeCartes;
import jeu.Jeu;
import jeu.Joueur;

public class TestJeu {
	public static void main(String[] args) {
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		Carte[] cartes = jeuDeCartes.donnerCartes();
		Jeu jeu = new Jeu(cartes);
		
		Joueur jack = new Joueur("Jack");
		Joueur bill = new Joueur("Bill");
		Joueur luffy = new Joueur("Luffy");
		
		jeu.inscrire(jack, bill, luffy);
		jeu.distribuerCartes();
		
		System.out.println(jeu.lancer());
	}
}
