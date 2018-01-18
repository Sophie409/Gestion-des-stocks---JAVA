package controller;

import java.util.ArrayList;
import java.util.List;

import model.Catalogue;
import view.FenetrePrincipale;

public class ControllerCatalogue {

	public static List<Catalogue> listeCatalogues = new ArrayList<Catalogue>();
	
	public static void ajouterCatalogue(String nom) {
		for (Catalogue catalogue : listeCatalogues) {
			if (catalogue.getNom().equals(nom)) {
				javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors de l'ajout");
			}
		}
		listeCatalogues.add(new Catalogue(nom));
		javax.swing.JOptionPane.showMessageDialog(null,"Ajout confirmé");
	}

	public static void supprimerCatalogue(String nom) {
		for (Catalogue catalogue : listeCatalogues) {
			if (catalogue.getNom().equals(nom)) {
				listeCatalogues.remove(catalogue);
				javax.swing.JOptionPane.showMessageDialog(null,"Suppression confirmée"); 
			}
		}
		javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors de la suppression");
	}

	public static void afficherCatalogue(String nom) {
		new FenetrePrincipale();
	}

	
}
