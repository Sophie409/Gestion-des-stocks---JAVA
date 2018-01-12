package dao;

import java.sql.*;

import model.Produit;

public class ProduitDAO {

	private Statement st = ConnexionDAO.getStatement();
	
	public void creerProduit(String nomProduit, double prixUnitaireHT, int quantiteStock) {
		ResultSet rs = null;
		try {
			rs.moveToInsertRow();
			rs.updateString(1, nomProduit);
			rs.updateDouble(2, prixUnitaireHT);
			rs.updateInt(3, quantiteStock);
			rs.insertRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void selectionnerTousLesProduits() {
		ResultSet rs = null;
		try {
			rs = st.executeQuery("SELECT * FROM Produits");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifierProduit(String nomProduit, double prixUnitaireHT, int quantiteStock) {
		ResultSet rs = null;
		try {
			if (rs.next()) {
				rs.updateString(1, nomProduit);
				rs.updateDouble(2, prixUnitaireHT);
				rs.updateInt(3, quantiteStock);
				rs.updateRow();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerProduit(Produit produit) {
		ResultSet rs = null;
		boolean trouve = false; 
		try {
			if (rs.next() && !trouve) {
				if (produit.getId() == rs.getInt("id")){
					rs.deleteRow();
					trouve = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
