package model;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class Catalogue implements I_Catalogue {

	private static ArrayList<I_Produit> lesProduits;
	
	public Catalogue() {
		this.lesProduits = new ArrayList<>();
		this.addProduit("Mars", 10, 5);
		this.addProduit("Treets", 10, 4);
		this.addProduit("Raider", 1, 10);
		this.addProduit("Twix", 10.45, 5);
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
			for (I_Produit i_Produit : lesProduits) {
				if (i_Produit.getNom().equals(nom)) {
					return false;
				}
			}
			return this.lesProduits.add(new Produit(nom, prix, qte));
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
		for (I_Produit i_Produit : lesProduits) {
			if (i_Produit.getNom().equals(nom)) {
				this.lesProduits.remove(i_Produit);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean acheterStock(String nomProduit, int qteAchetee) {
		if (qteAchetee > 0) {
			for (I_Produit i_Produit : lesProduits) {
				if (i_Produit.getNom().equals(nomProduit)) {
					i_Produit.ajouter(qteAchetee);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		if (qteVendue > 0) {
			for (I_Produit i_Produit : lesProduits) {
				if (i_Produit.getNom().equals(nomProduit)) {
					if (i_Produit.getQuantite() >= qteVendue && i_Produit.getQuantite() > 0) {
						i_Produit.enlever(qteVendue);
						return true;
					}
					return false;
				}
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
		for (Iterator<I_Produit> iterator = lesProduits.iterator(); iterator.hasNext();) {
			I_Produit i_Produit = (I_Produit) iterator.next();
			iterator.remove();
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
			str += i_Produit.getNom()+" - prix HT : "+bd1+" � - prix TTC : "+bd2+" � - quantit� en stock : "+i_Produit.getQuantite()+"\n";
		}
		BigDecimal bd3 = new BigDecimal(this.getMontantTotalTTC());
		bd3 = bd3.setScale(2, BigDecimal.ROUND_HALF_UP);
		str += "\nMontant total TTC du stock : "+bd3+" �";
		str = str.replaceAll("\\.", ",");
		return str;
	}

}
