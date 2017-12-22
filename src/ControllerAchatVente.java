
public class ControllerAchatVente {

	public static Catalogue cat = new Catalogue();
	
	public static void demandeAchat() {
		String[] produits = cat.getNomProduits();
		new FenetreAchat(produits);
	}
}
