package cartes;

public class JeuDeCartes {
	
	public static final int NB_CARTES_MAX = 106;
	
	private Configuration[] typesDeCartes = new Configuration[] {
			new Configuration(new Borne(25), 10),
			new Configuration(new Borne(50), 10),
			new Configuration(new Borne(75), 10),
			new Configuration(new Borne(100), 12),
			new Configuration(new Borne(200), 4),
			new Configuration(new DebutLimite(), 4),
			new Configuration(new FinLimite(), 6),
			new Configuration(new Parade(Type.FEU), 14),
			new Configuration(new Parade(Type.ESSENCE), 6),
			new Configuration(new Parade(Type.CREVAISON), 6),
			new Configuration(new Parade(Type.ACCIDENT), 6),
			new Configuration(new Attaque(Type.FEU), 5),
			new Configuration(new Attaque(Type.ESSENCE), 3),
			new Configuration(new Attaque(Type.CREVAISON), 3),
			new Configuration(new Attaque(Type.ACCIDENT), 3),
			new Configuration(new Botte(Type.FEU), 1),
			new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1),
			new Configuration(new Botte(Type.ACCIDENT), 1)
	};

	public JeuDeCartes() {
		
	}
	
	public String affichageJeuCartes() {
		StringBuilder s = new StringBuilder();
		for (Configuration config : typesDeCartes) {
			s.append(config.getNbExemplaires() + " " + config.getCarte().toString() + "\n");
		}
		return s.toString();
	}
	
	public boolean checkCount() {
		int count = 0;
		for (Configuration config : typesDeCartes) {
			count += config.getNbExemplaires();
		}
		return count == NB_CARTES_MAX;
	}
	
	public Carte[] getCartes() {
		Carte[] cartes = new Carte[NB_CARTES_MAX];
		int i = 0;
		for (int numConfig = 0; numConfig < 19; numConfig++) {
			Configuration config = typesDeCartes[numConfig];
			for (int numCarte = 0; numCarte < config.getNbExemplaires(); numCarte++) {
				cartes[i++] = config.getCarte();
			}
		}
		return cartes;
	}
	
	private static class Configuration {

		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}
		
		public Carte getCarte() {
			return carte;
		}
		
		public int getNbExemplaires() {
			return nbExemplaires;
		}

	}

}


