package jeu;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	
	private Joueur joueurCourant;
	private Joueur joueurCible;
	private Carte carteJouee;
	
	public Coup(Joueur joueurCourant, Joueur joueurCible, Carte carteJouee) {
		this.joueurCourant = joueurCourant;
		this.joueurCible = joueurCible;
		this.carteJouee = carteJouee;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}

	public Carte getCarteJouee() {
		return carteJouee;
	}
	
	public boolean estValide() {
		if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) {
			return joueurCourant != null && joueurCible != null && !joueurCourant.equals(joueurCible);
		} else {
			return joueurCourant != null && joueurCourant.equals(joueurCible);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		
		if (estValide()) {
			if (carteJouee instanceof Attaque || carteJouee instanceof DebutLimite) {
				string.append("Le joueur " + joueurCourant.toString() + " depose la carte " + carteJouee.toString()  
				+ " dans la zone de jeu de " + joueurCible.toString() + "\n");
			} else {
				string.append("Le joueur " + joueurCourant.toString() + " depose la carte " + carteJouee.toString()  
				+ " dans sa zone de jeu\n");
			}
		} else {
			string.append("Le joueur " + joueurCourant.toString() + " se defausse de la carte " + carteJouee.toString() + "\n");
		}
		
		return string.toString();
	}
	
	@Override
	public int hashCode() {
		return 31 * joueurCourant.hashCode() * (joueurCible == null ? 31 : joueurCible.hashCode()) * carteJouee.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coup other) {
			return joueurCourant.equals(other.getJoueurCourant())
					&& joueurCible.equals(other.getJoueurCible())
					&& carteJouee.equals(other.getCarteJouee());
		}
		return false;
	}
}
