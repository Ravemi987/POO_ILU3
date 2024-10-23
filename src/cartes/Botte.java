package cartes;

public class Botte extends Probleme {
	
	public Botte(Type type) {
		super(type);
	}

	@Override
	public String toString() {
		Type type = getType();
		return type.getBotte();
	}
	
	@Override
	public int hashCode() {
		return 31 * super.hashCode();
	}
	
}
