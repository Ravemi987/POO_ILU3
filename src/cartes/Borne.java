package cartes;

import java.util.Objects;

public class Borne extends Carte {
	
	private int km;

	public Borne(int km) {
		this.km = km;
	}

	@Override
	public String toString() {
		return km+"KM";
	}

}
