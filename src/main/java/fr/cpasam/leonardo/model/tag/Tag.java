package fr.cpasam.leonardo.model.tag;

public class Tag {

	
	protected long id;
	protected String keyword;
	
	public Tag() {
		// TODO Auto-generated constructor stub
	}
	
	public Tag(long id,String keyword) {
		this.id=id;
		this.keyword=keyword;
	}	
	
	/**
	 * Retourne l'id d'un mot clé
	 * @return id
	 */
	public long getId() {return id;}
	/**
	 * Retourne le contenu d'un mot clé
	 * @return keyword
	 */
	public String getKeyword () {return keyword;}
	
	
	/**
	 * Met à jour l'id d'un mot clé
	 * @param id
	 */
	public void setId(long id) {this.id=id;}
	/**
	 * Met à jour le contenu d'un mot clé
	 * @param keyword
	 */
	public void setKeyword(String keyword) {this.keyword=keyword;}
	
	
}
