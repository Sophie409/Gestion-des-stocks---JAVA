package model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Iterator;

import dao.I_ProduitDAO;
import dao.ProduitDAO;

import java.util.Arrays;

public class Catalogue implements I_Catalogue {
	
	private List<I_Produit> lesProduits;
	I_ProduitDAO produitDAO = new ProduitDAO();
	
	public Catalogue() {
		this.lesProduits = produitDAO.selectionnerTousLesProduits();
	}
	
	@Override
	public boolean addProduit(I_Produit produit) {
		if (produit != null && this.addProduit(produit.getNom(), produit.getPrixUnitaireHT(), produit.getQuantite())) {
			return true;
		}
		return false;
	}

	
	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		if (prix > 0 && qte >= 0) {
			DecimalFormat df = new DecimalFormat("0.00");
			df.format(prix);
			nom = nom.replaceAll("\t", " ");
			nom = nom.trim();
			if (!(nom.equals(produitDAO.selectionnerProduit(nom).getNom()))) {
				return produitDAO.creerProduit(new Produit(nom, prix, qte));
			}
		}
		return false;
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int cpt = 0;
		if (l != null) {
			for (Iterator<I_Produit> iterator = l.iterator(); iterator.hasNext();) {
				I_Produit i_Produit = (I_Produit) iterator.next();
				if (this.addProduit(i_Produit.getNom(), i_Produit.getPrixUnitaireHT(), i_Produit.getQuantite())) {
					cpt ++;
				}
			}
		}
		return cpt;
	}

	@Override
	public boolean removeProduit(String nom) {
		for (I_Produit produit : lesProduits) {
			if (nom.equals(produitDAO.selectionnerProduit(nom).getNom())) {
				return produitDAO.supprimerProduit(produit);				
			}
		}
		return false;

	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if (Arrays.asList(getNomProduits()).contains(nomProduit)) {
			I_Produit produit = produitDAO.selectionnerProduit(nomProduit);
			if (produit.ajouter(qteAchetee)) {
				return produitDAO.modifierProduit(produit);
			}
		}
		
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if (Arrays.asList(getNomProduits()).contains(nomProduit)) {
			I_Produit produit = produitDAO.selectionnerProduit(nomProduit);
			if (produit.enlever(qteVendue)) {
				return produitDAO.modifierProduit(produit);
			}
		}
		
		return false;
	}

	@Override
	public String[] getNomProduits() {
		int sizeArray =  this.lesProduits.size();
		String[] nomProduits = new String[sizeArray];
		for (int i = 0; i < sizeArray; i++) {
			nomProduits[i] = this.lesProduits.get(i).getNom();
		}
		Arrays.sort(nomProduits);
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		double total = 0;
		for (Iterator<I_Produit> iterator = lesProduits.iterator(); iterator.hasNext();) {
			I_Produit i_Produit = (I_Produit) iterator.next();
			total += i_Produit.getPrixStockTTC();
		}
		BigDecimal bd = new BigDecimal(total);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		total = bd.doubleValue();
		return total;
	}

	@Override
	public void clear() {
		for (I_Produit produit : lesProduits) {
			produitDAO.supprimerProduit(produit);
		}
	}
	
	@Override
	public String toString() {
		String str = "";
		for (I_Produit i_Produit : lesProduits) {
			BigDecimal bd1 = new BigDecimal(i_Produit.getPrixUnitaireHT());
			bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal bd2 = new BigDecimal(i_Produit.getPrixUnitaireTTC());
			bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);
			str += i_Produit.getNom()+" - prix HT : "+bd1+" € - prix TTC : "+bd2+" € - quantité en stock : "+i_Produit.getQuantite()+"\n";
		}
		BigDecimal bd3 = new BigDecimal(this.getMontantTotalTTC());
		bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
		str += "\nMontant total TTC du stock : "+bd3+" €";
		str = str.replaceAll("\\.", ",");
		return str;
	}

}
