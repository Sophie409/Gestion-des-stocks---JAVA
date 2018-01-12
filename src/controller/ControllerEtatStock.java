package controller;
import model.Catalogue;
import view.FenetreAffichage;

public class ControllerEtatStock {

	public static Catalogue cat = new Catalogue();
	
	public static void affichageStock() {
		new FenetreAffichage(cat.toString());
	}
	
}
