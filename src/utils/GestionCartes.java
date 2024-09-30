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
		
		for (; iterator.hasNext() && index > 0; index--) {
			element = iterator.next();
		}
		
		iterator.remove();
		return element;
	}
	
	public static <E> List<E> melanger(List<E> listTriee) {
		int longueur = listTriee.size();
		List<E> listMelange = new ArrayList<>(longueur);
		
		for (; longueur > 0; longueur--) {
			listMelange.add(extraire2(listMelange));
		}
		
		return listMelange;

	}
	
	public static <E> boolean verifierMelange(List<E> list1, List<E> list2) {
		if (list1.size() != list2.size()) {
			return false;
		}
		
		for (int i = 0; i < list1.size(); i++) {
			if (Collections.frequency(list1, list1.get(i)) != Collections.frequency(list2, list2.get(i))) {
				return false;
			}
		}
		
		return true;
	}

}
