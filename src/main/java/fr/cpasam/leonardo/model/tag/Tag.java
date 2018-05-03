package fr.cpasam.leonardo.model.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="tags")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Tag {

	@Id
	@GeneratedValue
	@Column(name="TAG_ID")
	protected long id;
	
	@Column(name="KEYWORD")
	protected String keyword;
	
	
}
