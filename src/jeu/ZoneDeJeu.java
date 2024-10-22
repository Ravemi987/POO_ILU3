package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();
	
	private <E> E donnerSommet(List<E> pile) {
		if (!pile.isEmpty()) {
			return pile.get(pile.size() - 1);
		}
		return null;
	}

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || (donnerSommet(pileLimites) instanceof FinLimite)) {
			return 200;
		}
		return 50;
	}
	
	public int donnerKmParcourus() {
		int total = 0;
		for (Borne b : pileBorne) {
			total += b.getKm();
		}
		return total;
	}
	
	public void deposer(Carte c) {
		if (c instanceof Borne borne) {
			pileBorne.add(borne);
		} else if (c instanceof Limite limite)  {
			pileLimites.add(limite);
		} else if (c instanceof Bataille bataille) {
			pileBataille.add(bataille);
		}
	}
	
	public boolean peutAvancer() {
		Bataille c = donnerSommet(pileBataille);
		if (c != null) {
			return c.equals(Cartes.FEU_VERT);
		}
		return false;
	}
	
	private boolean estDepotFeuVertAutorise() {
		if (pileBataille.isEmpty()) {
			return true;
		} else if (donnerSommet(pileBataille).equals(Cartes.FEU_ROUGE)) {
			return true;
		} else {
			Bataille s = donnerSommet(pileBataille); 
			if (s instanceof Parade && !(s.equals(Cartes.FEU_VERT))) {
				return true;
			}
			return false;
		}
	}
	
	private boolean estDepotBorneAutorise(Borne b) {
		return !(donnerSommet(pileBataille).equals(Cartes.FEU_ROUGE)) 
				&& b.getKm() <= donnerLimitationVitesse()
				&& b.getKm() + donnerKmParcourus() <= 1000;
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileLimites.isEmpty() || (donnerSommet(pileLimites) instanceof FinLimite);
		} else {
			return donnerSommet(pileLimites) instanceof DebutLimite;
		}
	}
	
	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque) {
			Bataille s = donnerSommet(pileBataille);
			return (s != null) && !s.equals(Cartes.FEU_ROUGE);
		}
		if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			} else {
				Bataille s = donnerSommet(pileBataille);
				return (s != null) && (s instanceof Attaque) && s.getType().equals(bataille.getType());
			}
		}
		return false;
	}
	
	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		} else if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		} else if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		} else {
			return false;
		}
	}

}
