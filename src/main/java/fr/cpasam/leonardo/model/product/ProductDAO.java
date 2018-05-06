package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;
import fr.cpasam.leonardo.utilities.HibernateUtil;

public class ProductDAO {

	// Affichage de tous les produits de la BD
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

	// Creation d'un produit avec recuperation des donnees du formulaire 
	public void CreateProduct(long id, String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product();

		product.id = id;
		product.name = name ;
		product.provenance = provenance ;
		product.unityPrice = unityPrice ;
		product.shop = shop ;
		product.tags = tags ;


		session.save(product);
		session.getTransaction().commit();
		session.close();
	}


	// Recherche d'un produit a partir de son id
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

	// Mise a jour des informations d'un produit
	public void UpdateProduct(long id, String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Product product = new Product();

		product.id = id;
		product.name = name ;
		product.provenance = provenance ;
		product.unityPrice = unityPrice ;
		product.shop = shop ;
		product.tags = tags ;


		session.save(product);
		session.getTransaction().commit();
		session.close();
	}


	// Supprime un produit a l'aide de son id et d'une requete nommee
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

