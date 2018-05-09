package fr.cpasam.leonardo.utilities;

import java.io.UnsupportedEncodingException;

import org.mindrot.jbcrypt.BCrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import fr.cpasam.leonardo.exceptions.BadPasswordException;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.MemberCreationException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.model.user.UserDAO;


public class Authentication {

	/**
	 * Vérifie la présence d'un utilisateur dans la base de données en fonction de ses informations de connexion
	 * @param mail l'e-mail de l'utilisateur fourni lors de la connexion
	 * @param pwd le mot de passe fourni lors de la connexion
	 * @return l'utilisateur associé à ces informations de connexion (l'utilisateur peut être nul)
	 * @throws UserNotFoundException dans le cas où aucun utilisateur n'a été trouvé dans la base de données en fonction du mail
	 * @throws BadPasswordException dans le cas où le mot de passe donné dans le cadre de la connexion ne correspond pas à celui stocké dans la base de données
	 * @throws IncompleteDataException dans le cas où l'e-mail et/ou le mot de passe fourni lors de la connexion est vide
	 */
	public static User connection(String mail, String pwd) throws UserNotFoundException, BadPasswordException, IncompleteDataException {
		if(mail == null || pwd == null) throw new IncompleteDataException();
		User user = exists(mail);
		if(user == null) throw new UserNotFoundException();
			if(!BCrypt.checkpw(pwd, user.getPwd())) throw new BadPasswordException();				
		return user;
	}
	
	/** 
	 * Vérifie si un utilisateur est associé à l'e-mail donné
	 * @param mail l'e-mail faisant office de login
	 * @return user l'utilisateur associé à l'e-mail donné, ou null si aucun utilisateur n'y est associé
	 */
	private static User exists(String mail) {
		return UserDAO.mailToUser(mail);
	}
	
	/**
	 * Sauvegarde le token généré lors de la connexion d'un utilisateur dans la base de données
	 * @param user l'utilisateur duquel on souhaite enregistrer le token
	 */
	public static void saveToken(User user) {
		updateUser(user.getId(), user.getFistName(), user.getLastName(), user.getEmail(), user.getPwd(), user.getToken());
	}
	
	/**
	 * Enregistre un nouveau membre dans la base de données à partir des informations transmises
	 * @param firstName le prénom du membre à enregistrer
	 * @param lastName le nom du membre à enregistrer
	 * @param mail l'e-mail du membre à enregistrer
	 * @param pwd le mot de passe du membre à enregistrer
	 * @throws IncompleteDataException dans le cas où des données nécessaires à la création d'un membre sont manquantes
	 * @throws MemberCreationError dans le cas où un problème est survenu lors de la transaction avec la base de données
	 */
	public static void registration(String firstName, String lastName, String mail, String pwd) throws IncompleteDataException, MemberCreationException {
		if(firstName == null || lastName == null || mail == null || pwd == null) throw new IncompleteDataException();
		String newPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
		if(MemberDAO.create(firstName, lastName, mail, newPwd) == null) throw new MemberCreationException();
	}
	
	/**
	 * Génère un token lié à la session de l'utilisateur lors de la connexion de ce-dernier
	 * @param user l'utilisateur qui souhaite se connecter
	 * @return le token généré à partir de l'e-mail et de l'id de l'utilisateur ou null si une erreur est survenue
	 */
	public static String generateToken(User user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		        .withIssuer("leonardo")
		        .withArrayClaim("mail", new String[] {user.getEmail(), Long.toString(user.getId())})
		        .sign(algorithm);
		    return token;
		} catch (UnsupportedEncodingException exception){
		} catch (JWTCreationException exception){}
		return null;
	}
	
	public static boolean checkCSRF(long userId, String token) {
		return UserDAO.get(userId).getToken().equals(token);
	}
	
}
