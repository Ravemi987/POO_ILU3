package cartes;

public abstract class Carte {

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carte other) {
			return toString().equals(other.toString());
		}
		return false;
	}
	
}
