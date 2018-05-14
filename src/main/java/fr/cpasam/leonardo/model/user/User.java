package fr.cpasam.leonardo.model.user;


public abstract class User {

	protected long id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String pwd;
	protected String token;

	
	/**
	 * Constructeur 4 paramètres
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 */
	public User(String firstName, String lastName, String email, String pwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		}
	
	/**
	 * Constructeur 6 paramètres
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 * @param token
	 */
	public User(long id, String firstName, String lastName, String email, String pwd, String token) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.token=token;
	}
	
	
	/**
	 * Retourne l'id d'un utilisateur
	 * @return id
	 */
	public long getId() {return id;}
	/**
	 * Retourne le prénom d'un utilisateur
	 * @return fistName
	 */
	public String getFirstName() {return firstName;}
	/**
	 * Retourne le nom d'un utilisateur
	 * @return lastName
	 */
	public String getLastName() {return lastName ;}
	/**
	 * Retourne l'email d'un utilisateur
	 * @return email
	 */
	public String getEmail() {return email;}
	/**
	 * Retourne le mot de passe d'un utilisateur
	 * @return pwd
	 */
	public String getPwd() {return pwd;}
		
	/**
	 * Retourne le token associé à la session d'un utilisateur
	 * @return token
	 */
	public String getToken() {return this.token;}
	
	/**
	 * Met à jour l'id d'un utilisateur
	 * @param id
	 */
	public void setId(long id) {this.id=id;}
	/**
	 * Met à jour le prenom d'un utilisateur
	 * @param firstname
	 */
	public void setFistName(String firstname) {this.firstName=firstname;}
	/**
	 * Met à jour le nom d'un utilisateur
	 * @param lastname
	 */
	public void setLastName(String lastname) {this.lastName=lastname;}
	/**
	 * Met à jour l'email d'un utilisateur
	 * @param email
	 */
	public void setEmail(String email) {this.email=email;}
	/**
	 * Met à jour le mot de passe d'un utilisateur
	 * @param pwd
	 */
	public void setPwd(String pwd) {this.pwd=pwd;}

	
	/**
	 * Met à jour le token de la session d'un utilisateur
	 * @param token
	 */
	public void setToken(String token) {this.token = token;}
	
	
	/**
	 * Renvoie la représentation textuelle d'un utilisateur
	 * @return la chaine de caractère représentant l'utilisateur
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pwd="
				+ pwd + ", token=" + token + "]";
	}
	
}






