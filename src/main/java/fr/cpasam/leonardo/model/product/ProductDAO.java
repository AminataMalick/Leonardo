package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;
import fr.cpasam.leonardo.utilities.HibernateUtil;

public class ProductDAO {

	/**
	 * Retourne la liste de tous les produits de la BD
	 * @return myList
	 */
	public List<Product> getAllProducts() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();


		List<Product> myList = new ArrayList<Product>();
		Query query = session.getNamedQuery("findAllProducts");
		myList = query.getResultList();

		session.getTransaction().commit();  
		session.close();
		return myList ;   
	}

	
	/**
	 * Creation d'un produit avec récuperation des donnees du formulaire 
	 * @param id
	 * @param name
	 * @param provenance
	 * @param unityPrice
	 * @param shop
	 * @param tags
	 */
	public void CreateProduct(long id, String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product();

		product.id = id;
		product.name = name ;
		product.provenance = provenance ;
		product.unityPrice = unityPrice ;
		product.tags = tags ;


		session.save(product);
		session.getTransaction().commit();
		session.close();
	}


	
	/**
	 * Recherche d'un produit à partir de son id
	 * @param id
	 * @return product
	 */
	public Product GetProductID(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product() ;
		product.id = id ;
		Query query = session.getNamedQuery("findProductById");
		product = (Product)query;

		session.getTransaction().commit();
		session.close();
		return product;
	}

	 
	/**
	 * Met à jour les informations d'un produit
	 * @param id
	 * @param name
	 * @param provenance
	 * @param unityPrice
	 * @param shop
	 * @param tags
	 */
	public void UpdateProduct(long id, String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product();

		product.id = id;
		product.name = name ;
		product.provenance = provenance ;
		product.unityPrice = unityPrice ;
		product.tags = tags ;


		session.save(product);
		session.getTransaction().commit();
		session.close();
	}


	
	/**
	 * Supprime un produit à l'aide de son id et d'une requete nommee
	 * @param id
	 */
	public void DeleteProduct(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product() ;
		product.id = id ;
		Query query = session.getNamedQuery("findProductById");

		session.delete(query);
		session.save(query);
		session.getTransaction().commit();
		session.close();	

	}
}

