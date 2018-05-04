package fr.cpasam.leonardo.utilities;

public interface CRUD_Service <T> {

	  public void Create(T entity);
	  public T Read();
	  public void Update(T entity);
	  public void Delete(T entity);
	} 

