package fr.cpasam.leonardo.model.tag;

public abstract class Tag {

	protected long id;
	protected String keyword;
	
	public Tag() {}
	
	public Tag(long id,String keyword) {
		this.id=id;
		this.keyword=keyword;
	}	
	
	public long GetTagId() {return id;}
	public String GetTagKeyword () {return keyword;}
	
	public void SetTagId(long id) {this.id=id;}
	public void SetTagKeyword(String keyword) {this.keyword=keyword;}
	
	
}
