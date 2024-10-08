package jeu;

import java.util.Arrays;
import java.util.List;

import cartes.Carte;
import utils.GestionCartes;

public class Jeu {

	private Sabot sabot;
	private List<Carte> listeCartes;
	
	public Jeu(Carte[] cartes) {
		listeCartes = GestionCartes.melanger(Arrays.asList(cartes));
		sabot = new Sabot((Carte[]) listeCartes.toArray());
	}
}
