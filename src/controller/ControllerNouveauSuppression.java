package controller;
import model.Catalogue;
import view.FenetreNouveauProduit;
import view.FenetreSuppressionProduit;

public class ControllerNouveauSuppression {
	
	public static Catalogue cat = new Catalogue();
	
	public static void demandeSuppression() {
		String[] produits = cat.getNomProduits();
		new FenetreSuppressionProduit(produits);
	}
	
	public static void supprimerProduit(String nomProduit) {
		if (cat.removeProduit(nomProduit)) {
			javax.swing.JOptionPane.showMessageDialog(null,"Suppression confirmée"); 
		} else {
			javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors de la suppression"); 
		}
	}
	
	public static void demandeAjout() {
		new FenetreNouveauProduit();
	}
	
	public static void ajouterProduit(String nom, double prix, int qte) {
		if (cat.addProduit(nom, prix, qte)) {
			javax.swing.JOptionPane.showMessageDialog(null,"Ajout confirmé");
		} else {
			javax.swing.JOptionPane.showMessageDialog(null,"Une valeur n'est pas valide"); 
		}
	}
	
	
	
}
