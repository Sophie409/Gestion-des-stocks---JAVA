package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Catalogue;
import view.FenetrePrincipale;

public class ControllerCatalogue {

	public static List<Catalogue> listeCatalogues = new ArrayList<Catalogue>();
	
	public static void ajouterCatalogue(String nom) {
		Catalogue cat = new Catalogue();
		cat.setNom(nom);
		for (Catalogue catalogue : listeCatalogues) {
			if (catalogue.getNom().equals(nom)) {
				javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors de l'ajout");
			}
		}
		listeCatalogues.add(cat);
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
		for (Catalogue catalogue : listeCatalogues) {
			if (catalogue.getNom().equals(nom)) {
				// TODO : afficher la fenetre de catalogue
				new FenetrePrincipale();
			}
		}
	}
	
	public static String[] ListerCatalogues() {
		int sizeArray = listeCatalogues.size();
		String[] tabCatalogues = new String[sizeArray];
		for (int i = 0; i < sizeArray; i++) {
			tabCatalogues[i] = listeCatalogues.get(i).getNom();
		}
		Arrays.sort(tabCatalogues);
		return tabCatalogues;
	}
	
	public static String[] ListerDetailsCatalogues() {
		int sizeArray = listeCatalogues.size();
		String[] nomProduits = null;
		String[] tabDetails = new String[sizeArray];
		for (int i = 0; i < sizeArray; i++) {
			nomProduits = listeCatalogues.get(i).getNomProduits();
			tabDetails[i] = listeCatalogues.get(i).getNom()+" : "+ nomProduits.length +" produits";
		}
		Arrays.sort(tabDetails);
		return tabDetails;
	}
		
}
