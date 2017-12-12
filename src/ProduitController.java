
public class ProduitController {
	public static void main(String[] args) {
		Catalogue c1 = new Catalogue();
		
		System.out.println(c1.addProduit("p1", 100, 100));
		System.out.println(c1.addProduit("p2", 100, 100));
		System.out.println(c1.addProduit("p3", 100, 100));
		System.out.println("------------------------------");
		
		System.out.println(c1.toString());
		
		System.out.println(c1.acheterStock("p1", 5));
		System.out.println(c1.vendreStock("p2", 100));
		
		System.out.println(c1.toString());
		
		c1.removeProduit("p2");
		String[] str = c1.getNomProduits();
		for (int i = 0; i < str.length; i++) {
			System.out.println(str[i]);
		}
		c1.clear();
		System.out.println(c1.toString());

	}
}
