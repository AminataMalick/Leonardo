package fr.cpasam.leonardo.model.tag;

public abstract class Tag {

	protected long id;
	protected String keyword;
	
	public Tag() {}
	
	public Tag(long id,String keyword) {
		this.id=id;
		this.keyword=keyword;
	}	
	
	/**
	 * Retourne l'id d'un mot clé
	 * @return id
	 */
	public long GetTagId() {return id;}
	/**
	 * Retourne le contenu d'un mot clé
	 * @return keyword
	 */
	public String GetTagKeyword () {return keyword;}
	
	
	/**
	 * Met à jour l'id d'un mot clé
	 * @param id
	 */
	public void SetTagId(long id) {this.id=id;}
	/**
	 * Met à jour le contenu d'un mot clé
	 * @param keyword
	 */
	public void SetTagKeyword(String keyword) {this.keyword=keyword;}
	
	
}
