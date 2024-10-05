package testsFonctionnels;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {
	public static void main(String args[]) {
//		List<Integer> listNonMelangee = new ArrayList<>();
//		listNonMelangee.add(1);
//		listNonMelangee.add(4);
//		listNonMelangee.add(3);
//		listNonMelangee.add(2);
//		listNonMelangee.add(3);
//		List<Integer> list = new ArrayList<>(listNonMelangee);
//		System.out.println(list);
//		list = GestionCartes.melanger(list);
//		System.out.println(list);
//		System.out.println("Melange ok ? : " + GestionCartes.verifierMelange(listNonMelangee, list));
//		list = GestionCartes.rassembler(list);
//		System.out.println(list);
//		System.out.println("Rassembler ok ? : " + GestionCartes.verifierRassemblement(list));
		
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println(listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println(listeCartes);
		System.out.println(
				"liste melangee sans erreur ? " + GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));
		listeCartes = GestionCartes.rassembler(listeCartes);
		System.out.println(listeCartes);
		System.out.println("liste rassemblee sans erreur ? " + GestionCartes.verifierRassemblement(listeCartes));
		System.out.println();
		System.out.println("checkCount: " + jeu.checkCount());

	}

}