package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	
	private static Random seed = new Random();
	
	
	public static <E> E extraire1 (List<E> list) {
		return list.remove(seed.nextInt(list.size()));
	}
	
	
	public static <E> E extraire2 (List<E> list) {
		int index = seed.nextInt(list.size());
		ListIterator<E> iterator = list.listIterator();
		E element = null;
		
		for (; iterator.hasNext() && index >= 0; index--) {
			element = iterator.next();
		}
		
		iterator.remove();
		return element;
	}
	
	
	public static <E> List<E> melanger(List<E> listNonMelangee) {
		int longueur = listNonMelangee.size();
		List<E> listMelange = new ArrayList<>(longueur);
		
		for (; longueur > 0; longueur--) {
			listMelange.add(extraire2(listNonMelangee));
		}
		
		return listMelange;

	}
	
	
	public static <E> boolean verifierMelange(List<E> list1, List<E> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		
		for (int i = 0; i < list1.size(); i++) {
			boolean found = false;
			for (int j = 0; j < list1.size() && !found; j++) {
				E e1 = list1.get(i);
				E e2 = list2.get(i);
				if (e1.equals(e2) && Collections.frequency(list1, e1) != Collections.frequency(list2, e2)) {
					return false;
				} else if (e1.equals(e2)) {
					found = true;
				}
			}
		}
		
		return true;
	}
	
	
	public static <E> List<E> rassembler(List<E> listMelange) {
		int longueur = listMelange.size();
		List<E> listTriee = new ArrayList<>(longueur);
		
		for (;longueur > 0;) {
			E element = listMelange.get(0);
			int nbElem = Collections.frequency(listMelange, element);
			
			for (int j = 0; j < nbElem; j++) {
				listTriee.add(element);
				listMelange.remove(element);
				longueur--;
			}
		}
		
		return listTriee;
	}
	
	public static <E> boolean verifierRassemblement(List<E> list) {
		E prev = null;
		int i = 0;
		for (ListIterator<E> iter1 = list.listIterator(); iter1.hasNext();) {
			int j = 0;
			E elem1 = iter1.next();
			if (prev != null && !elem1.equals(prev)) {
				// @TODO facto
				for (ListIterator<E> iter2 = list.listIterator(); iter2.hasNext();) {
					E elem2 = iter2.next();
					if (j > i && elem2.equals(prev)) {
						return false;
					}
					j++;
				}
			}
			prev = elem1;
			i++;
		}
		return true;
	}

}	
