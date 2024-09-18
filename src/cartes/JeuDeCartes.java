package cartes;

public class JeuDeCartes {
	
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
			new Configuration(new Attaque(Type.FEU), 14),
			new Configuration(new Attaque(Type.ESSENCE), 6),
			new Configuration(new Attaque(Type.CREVAISON), 6),
			new Configuration(new Attaque(Type.ACCIDENT), 6),
			new Configuration(new Botte(Type.FEU), 1),
			new Configuration(new Botte(Type.ESSENCE), 1),
			new Configuration(new Botte(Type.CREVAISON), 1),
			new Configuration(new Botte(Type.ACCIDENT), 1)
	};

	public JeuDeCartes() {
		
	}
	
	public String affichageJeuDeCartes() {
		return "coucou";
	}
	
	private static class Configuration {

		private int nbExemplaires;
		private Carte carte;
		
		private Configuration(Carte carte, int nbExemplaires) {
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

