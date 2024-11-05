package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import cartes.Bataille;
import cartes.Botte;
import cartes.Carte;
import cartes.DebutLimite;

public class Joueur {
	
	private String nom;
	private ZoneDeJeu zoneDeJeu = new ZoneDeJeu();
	private MainJoueur main = new MainJoueur();
	
	private Random seed = new Random();

	public Joueur(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public MainJoueur getMain() {
		return main;
	}
	
	public void donner(Carte c) {
		main.prendre(c);
	}
	
	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte cartePiochee = sabot.piocher();
		donner(cartePiochee);
		return cartePiochee;
	}
	
	public int donnerKmParcourus() {
		return zoneDeJeu.donnerKmParcourus();
	}
	
	public void deposer(Carte c) {
		zoneDeJeu.deposer(c);
	}
	
	public boolean estDepotAutorise(Carte carte) {
		return zoneDeJeu.estDepotAutorise(carte);
	}
	
	public Set<Coup> coupsPossibles(Set<Joueur> participants) {
		Set<Coup> coups = new HashSet<>();
		Iterator<Joueur> itJoueurCible = participants.iterator();
		
		while (itJoueurCible.hasNext()) {
			Joueur joueurCible = itJoueurCible.next();
			
			ListIterator<Carte> cartesIterateur = joueurCible.getMain().getListeCartes().listIterator();
			for (; cartesIterateur.hasNext();) {
				Carte carte = cartesIterateur.next();
				
				Coup coup = new Coup(this, joueurCible, carte);
				if (coup.estValide()) {
					coups.add(coup);
				}
			}
		}
		
		return coups;
	}
	
	public Set<Coup> coupsDefausse() {
		Set<Coup> coups = new HashSet<>();
		ListIterator<Carte> itCartes = main.getListeCartes().listIterator();
		
		for (; itCartes.hasNext();) {
			Carte carte = itCartes.next();
			coups.add(new Coup(this, null, carte));
		}
		
		return coups;
	}
	
	public void retirerDeLaMain(Carte carte) {
		main.jouer(carte);
	}
	
	private Coup coupAleatoire(Set<Coup> coups) {
		int index = seed.nextInt(coups.size());
		Iterator<Coup> it = coups.iterator();
		Coup coupChoisit = null;
		
		for (; it.hasNext() && index >= 0; index--) {
			coupChoisit = it.next();
		}
		
		return coupChoisit;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {		
		Set<Coup> coupsLegaux = coupsPossibles(participants);
		
		if (!coupsLegaux.isEmpty()) {
			return coupAleatoire(coupsLegaux);
		} else {
			return coupAleatoire(coupsDefausse());
		}
	}

	
	public String afficherEtatJoueur() {
		StringBuilder string = new StringBuilder("INFOS " + nom + '\n');
		
		string.append("- Ensemble des bottes: [");
		Set<Botte> ensemblesBottes = zoneDeJeu.getBottes();
		
		Iterator<Botte> it = ensemblesBottes.iterator();
		while (it.hasNext()) {
			string.append(it.next().toString() + ", ");
		}
		string.append("]\n");
		
		string.append("- Présence d'une limitation de vitesse? : " 
		+ (zoneDeJeu.getLimites().contains(new DebutLimite()) ? "oui\n" : "non\n"));
		
		Bataille sommet = zoneDeJeu.donnerSommet(zoneDeJeu.getBataille());
		string.append("- Sommet de la pile de bataille: " + (sommet != null ? (sommet.toString() + "\n") : "null\n"));
		
		string.append("- Contenu de la main: " + main.toString());
		
		return string.toString();
	}

	@Override
	public String toString() {
		return nom;
	}
	
	@Override
	public int hashCode() {
		return 31 * nom.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur other) {
			return nom.equals(other.getNom());
		}
		return false;
	}

}
