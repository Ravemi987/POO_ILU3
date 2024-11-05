package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;
import cartes.JeuDeCartes;

public class Sabot implements Iterable<Carte> {

	private Carte[] cartes;
	private int nbCartes = JeuDeCartes.getNbCartesMax(); // Nombre total de cartes en comptant celles distribuées en début de partie
	private int nombreOperations = 0;
	
	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
	}
	
	public boolean estVide() {
		return nbCartes == 0;
	}
	
	public void ajouterCarte(Carte carte) {
		if (nbCartes >= JeuDeCartes.getNbCartesMax()) {
			throw new IllegalStateException();
		} else {
			cartes[nbCartes++] = carte;
			nombreOperations++;
		}
	}
	
	public Carte piocher() {
		Iterator<Carte> iter = iterator();
		Carte carte = iter.next();
		iter.remove();
		return carte;
	}
	
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}
	
	private class Iterateur implements Iterator<Carte> {
		
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nombreOperations;
		
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}
		
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i+1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nombreOperations++; nombreOperationsReference++;
		}
		
		private void verificationConcurrence() {
			if (nombreOperations != nombreOperationsReference)
				throw new ConcurrentModificationException();
		}
	}
}
