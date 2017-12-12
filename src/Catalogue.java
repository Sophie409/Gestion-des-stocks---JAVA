import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Catalogue implements I_Catalogue {

	private ArrayList<I_Produit> lesProduits;
	
	public Catalogue() {
		this.lesProduits = new ArrayList<>();
	}
	
	@Override
	public boolean addProduit(I_Produit produit) {
		if (this.lesProduits.add(produit)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean addProduit(String nom, double prix, int qte) {
		return this.addProduit(new Produit(nom, prix, qte));
	}

	@Override
	public int addProduits(List<I_Produit> l) {
		int cpt = 0;
		for (Iterator<I_Produit> iterator = l.iterator(); iterator.hasNext();) {
			I_Produit i_Produit = (I_Produit) iterator.next();
			this.addProduit(i_Produit);
			cpt ++;
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
		for (I_Produit i_Produit : lesProduits) {
			if (i_Produit.getNom().equals(nomProduit)) {
				i_Produit.ajouter(qteAchetee);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean vendreStock(String nomProduit, int qteVendue) {
		for (I_Produit i_Produit : lesProduits) {
			if (i_Produit.getNom().equals(nomProduit)) {
				i_Produit.enlever(qteVendue);
				return true;
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
		
		return nomProduits;
	}

	@Override
	public double getMontantTotalTTC() {
		int total = 0;
		for (Iterator<I_Produit> iterator = lesProduits.iterator(); iterator.hasNext();) {
			I_Produit i_Produit = (I_Produit) iterator.next();
			total += i_Produit.getPrixStockTTC();
		}
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
			str += i_Produit.getNom()+" prix HT : "+i_Produit.getPrixUnitaireHT()+"€ prix TTC : "+i_Produit.getPrixUnitaireTTC()+" quantité en stock : "+i_Produit.getQuantite()+"\n";
		}
		str += "\nMontant total TTC du stock : "+this.getMontantTotalTTC();
		return str;
	}

}
