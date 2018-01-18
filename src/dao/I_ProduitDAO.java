package dao;

import java.util.List;

import model.I_Produit;
import model.Produit;

public interface I_ProduitDAO {
	
	public List<I_Produit> selectionnerTousLesProduits();

	public I_Produit selectionnerProduit(String nom);

	public boolean creerProduit(I_Produit produit);

	public boolean supprimerProduit(I_Produit produit);

	public boolean modifierProduit(I_Produit produit);
}
