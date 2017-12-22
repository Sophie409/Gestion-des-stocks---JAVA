
public class ControllerAchatVente {
	
	public static Catalogue cat = new Catalogue();
	
	public static void demandeAchat() {
		String[] produits = cat.getNomProduits();
		new FenetreAchat(produits);
	}
	
	public static void acheterProduit(String produit, int qte) {
		if (cat.acheterStock(produit, qte)) {
			javax.swing.JOptionPane.showMessageDialog(null,"Achat confirmé");
		} else {
			javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors de l'achat");
		}
	}
	
	public static void demandeVente() {
		String[] produits = cat.getNomProduits();
		new FenetreVente(produits);
	}
	
	public static void vendreProduit(String produit, int qte) {
		if (cat.vendreStock(produit, qte)) {
			javax.swing.JOptionPane.showMessageDialog(null,"Vente confirmée");
		} else {
			javax.swing.JOptionPane.showMessageDialog(null,"Erreur lors de la vente");
		}
	}
	
}
