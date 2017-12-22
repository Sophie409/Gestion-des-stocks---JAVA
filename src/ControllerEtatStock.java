
public class ControllerEtatStock {

	public static Catalogue cat = new Catalogue();
	
	public static void affichageStock() {
		new FenetreAffichage(cat.toString());
	}
	
}
