package testsFonctionnels;

import java.util.Iterator;

import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Sabot;

public class TestSabot {
	public static void main(String[] args) {
		JeuDeCartes jeu = new JeuDeCartes();
		Sabot sabot = new Sabot(jeu.getCartes());
		
//		while(!sabot.estVide()) {
//			System.out.print("je pioche " + sabot.piocher() + "\n");
//		}
		
		for (Iterator<Carte> iter = sabot.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
			iter.remove();
		}
	}
}
