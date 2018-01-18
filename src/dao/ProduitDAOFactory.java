package dao;

public class ProduitDAOFactory {

	public I_ProduitDAO createProduitDAO() {
		return new ProduitDAO();
	}
}
