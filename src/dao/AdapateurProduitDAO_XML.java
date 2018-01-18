package dao;

import java.util.List;
import model.I_Produit;

public class AdapateurProduitDAO_XML implements I_ProduitDAO {

	private ProduitDAO_XML produitXML;
	
	public AdapateurProduitDAO_XML() {
		produitXML = new ProduitDAO_XML();
	}

	@Override
	public List<I_Produit> selectionnerTousLesProduits() {
		return produitXML.lireTous();
	}

	@Override
	public I_Produit selectionnerProduit(String nom) {
		return produitXML.lire(nom);
	}

	@Override
	public boolean creerProduit(I_Produit produit) {
		return produitXML.creer(produit);
	}

	@Override
	public boolean supprimerProduit(I_Produit produit) {
		return produitXML.supprimer(produit);
	}

	@Override
	public boolean modifierProduit(I_Produit produit) {
		return produitXML.maj(produit);
	}
}
