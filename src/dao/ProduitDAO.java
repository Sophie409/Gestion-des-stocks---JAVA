package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.I_Produit;
import model.Produit;

public class ProduitDAO implements I_ProduitDAO {

	private static Statement st;
	private static ResultSet rs;
	private static Connection cn;
	private static PreparedStatement pst;
	
	public ProduitDAO() {
		ConnexionDAO connexion = ConnexionDAO.getInstance();
		cn = connexion.getConnection();
		
		st = ConnexionDAO.getInstance().getStatement();
		rs = null;
		pst = null;
	}
	
	public boolean creerProduit(I_Produit produit) {
		try {
			CallableStatement cst = cn.prepareCall("{call ajouterProduit(?,?,?)}");
			cst.setString(1, produit.getNom());
			cst.setDouble(2, produit.getPrixUnitaireHT());
			cst.setInt(3, produit.getQuantite());
			cst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public I_Produit selectionnerProduit(String nomProduit) {
		I_Produit produit;
		try {
			pst = cn.prepareStatement("SELECT nom FROM Produit WHERE nom = ?");
			pst.setString(1, nomProduit);
			rs = pst.executeQuery();
			if (rs.next()) {
				produit = new Produit(rs.getString("nom"), rs.getDouble("prixUnitaireHT"), rs.getInt("quantiteStock"));
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<I_Produit> selectionnerTousLesProduits() {
		
		List<I_Produit> listeProduits = new ArrayList<I_Produit>();
		Produit produit;
		
		try {
			rs = st.executeQuery("SELECT p.* FROM Produit p");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while (rs.next()) {
				produit = new Produit(rs.getString("nom"), rs.getDouble("prixUnitaireHT"), rs.getInt("quantiteStock"));
				listeProduits.add(produit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeProduits;
	}
	
	public boolean modifierProduit(I_Produit produit) {
		try {
			PreparedStatement pst = cn.prepareStatement("UPDATE Produit SET quantiteStock = ? WHERE nom = ?");
			pst.setInt(1, produit.getQuantite());
			pst.setString(2, produit.getNom());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean supprimerProduit(I_Produit produit) {
		try {
			pst = cn.prepareStatement("DELETE FROM Produit WHERE nom = ?");
			pst.setString(1, produit.getNom());
			rs = pst.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
