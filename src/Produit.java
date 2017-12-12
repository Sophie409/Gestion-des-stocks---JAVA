
public class Produit implements I_Produit{
	
	private String nom;
	private double prixUnitaireHT;
	private int quantiteStock;
	private double tauxTVA = 0.2;
	
	public Produit(String nom, double prix, int qte) {
		this.nom = nom;
		this.prixUnitaireHT = prix;
		this.quantiteStock = qte;
	}
	
	@Override
	public boolean ajouter(int qteAchetee) {
		if (qteAchetee > 0) {
			this.quantiteStock += qteAchetee;
			return true;
		}
		return false;
	}

	@Override
	public boolean enlever(int qteVendue) {
		if (qteVendue > 0) {
			this.quantiteStock -= qteVendue;
			return true;
		}
		return false;
	}

	// getters et setters
	
	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public int getQuantite() {
		return this.quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return this.prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return this.prixUnitaireHT*(1+this.tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return this.quantiteStock*this.getPrixUnitaireTTC();
	}

}
