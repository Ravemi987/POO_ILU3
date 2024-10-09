package jeu;

import java.util.ArrayList;
import java.util.List;

import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;

public class ZoneDeJeu {
	
	private List<Limite> pileLimites = new ArrayList<>();
	private List<Bataille> pileBataille = new ArrayList<>();
	private List<Borne> pileBorne = new ArrayList<>();

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty() || (pileLimites.get(pileLimites.size() - 1) instanceof FinLimite)) {
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

}
