package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class MainJoueur {
	
	private List<Carte> main = new ArrayList<>();

	public MainJoueur() {
		
	}
	
	void prendre(Carte c) {
		main.add(c);
	}
	
	void jouer(Carte c) {
		assert main.contains(c);
		main.remove(c);
	}
	
	public String afficherMain() {
		StringBuilder string = new StringBuilder();
		for (Carte c : main) {
			string.append(c.toString() + '\n');
		}
		return string.toString();
	}
	
	@Override
	public String toString() {
		return afficherMain();
	}

}
