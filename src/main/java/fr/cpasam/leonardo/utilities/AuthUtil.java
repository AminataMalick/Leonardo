package fr.cpasam.leonardo.utilities;

import java.io.UnsupportedEncodingException;

import org.mindrot.jbcrypt.BCrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import fr.cpasam.leonardo.DAO.MemberDAO;
import fr.cpasam.leonardo.DAO.UserDAO;
import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.MemberCreationException;
import fr.cpasam.leonardo.exceptions.MemberDeletionException;
import fr.cpasam.leonardo.exceptions.MemberUpdateException;
import fr.cpasam.leonardo.exceptions.TokenCreationException;
import fr.cpasam.leonardo.exceptions.TokenDeletionException;
import fr.cpasam.leonardo.exceptions.TokenStorageException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.exceptions.WrongPasswordException;
import fr.cpasam.leonardo.exceptions.WrongTokenException;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;


public class AuthUtil {

	/**
	 * Vérifie la présence d'un utilisateur dans la base de données en fonction de ses informations de connexion
	 * @param mail l'e-mail de l'utilisateur fourni lors de la connexion
	 * @param pwd le mot de passe fourni lors de la connexion
	 * @return l'utilisateur associé à ces informations de connexion (l'utilisateur peut être nul)
	 * @throws UserNotFoundException dans le cas où aucun utilisateur n'a été trouvé dans la base de données en fonction du mail
	 * @throws WrongPasswordException dans le cas où le mot de passe donné dans le cadre de la connexion ne correspond pas à celui stocké dans la base de données
	 * @throws IncompleteDataException dans le cas où l'e-mail et/ou le mot de passe fourni lors de la connexion est vide
	 * @throws UnsupportedEncodingException 
	 */
	public static User connection(String mail, String pwd) throws UserNotFoundException, WrongPasswordException, IncompleteDataException, TokenCreationException, TokenStorageException, UnsupportedEncodingException {
		String[] fields = new String[] {mail, pwd};
		if(!Validator.checkFields(fields)) throw new IncompleteDataException();
		User user = exists(mail);
		if(user == null) throw new UserNotFoundException();
		
		// Les lignes suivantes doivent rester telles quelles pour permettre les tests lors de la démo
		if(!BCrypt.checkpw(pwd, user.getPwd())) throw new WrongPasswordException();

		// Fin de zone de test
		String token = generateToken(user);
		if(token == null) {System.out.println("NULL");throw new TokenCreationException();}
		user.setToken(token);
		if(!saveToken(user)) throw new TokenStorageException();
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
	public static boolean saveToken(User user) {
		return UserDAO.saveToken(user);
	}
	
	/**
	 * Enregistre un nouveau membre dans la base de données à partir des informations transmises
	 * @param firstName le prénom du membre à enregistrer
	 * @param lastName le nom du membre à enregistrer
	 * @param mail l'e-mail du membre à enregistrer
	 * @param pwd le mot de passe du membre à enregistrer
	 * @throws IncompleteDataException dans le cas où des données nécessaires à la création d'un membre sont manquantes
	 * @throws UserNotFoundException 
	 * @throws ChatNotFoundException 
	 * @throws MemberCreationError dans le cas où un problème est survenu lors de la transaction avec la base de données
	 */
	public static void registration(String firstName, String lastName, String mail, String pwd) throws IncompleteDataException, MemberCreationException, ChatNotFoundException, UserNotFoundException {
		String[] fields = new String[] {firstName, lastName, mail, pwd};
		if(!Validator.checkFields(fields)) throw new IncompleteDataException();
		String newPwd = encryptPassword(pwd);
		if(MemberDAO.create(firstName, lastName, mail, newPwd) == null) throw new MemberCreationException();
	}
	
	/**
	 * Génère le token associé à la session de l'utilisateur
	 * @param user l'utilisateur souhaitant se connecter
	 * @return le token généré
	 * @throws UnsupportedEncodingException dans le cas où une erreur d'encodage est survenue lors de la génération du token
	 */
	public static String generateToken(User user) throws UnsupportedEncodingException {
        return JWT.create()
                .withIssuer(Long.toString(user.getId()))
                .sign(Algorithm.HMAC256(user.getEmail() + user.getPwd()));
}
	
	/**
	 * Permet d'effectuer la modification d'un membre dans la base de données
	 * @param id l'id du membre à modifier
	 * @param firstName le nouveau prénom qui va écraser l'ancien dans la base de données
	 * @param lastName le nouveau nom qui va écraser l'ancien dans la base de données
	 * @param mail le nouvel e-mail qui va écraser l'ancien dans la base de données
	 * @param pwd le nouveau mot de passe qui va écraser l'ancien dans la base de données
	 * @param token le token à comparer pour vérifier l'authenticité de l'utilisateur
	 * @return le membre modifié
	 * @throws IncompleteDataException dans le cas où certains champs n'ont pas été renseignés
	 * @throws UserNotFoundException dans le cas où un problème est survenu lors de la récupération du membre à modifier
	 * @throws WrongTokenException dans le cas où le token reçu ne correspond pas à celui correspondant à l'utilisateur ayant fait la requête
	 * @throws MemberUpdateException dans le cas où un problème est survenu lors de la mise à jour du membre
	 * @throws ChatNotFoundException 
	 */
	public static Member modify(long id, String firstName, String lastName, String mail, String pwd, String token) throws IncompleteDataException, UserNotFoundException, WrongTokenException, MemberUpdateException, ChatNotFoundException {
		String[] fields = new String[] {Long.toString(id), firstName, lastName, mail, pwd, token};
		Validator.verifyCreatedMember(fields);
		String newPwd = encryptPassword(pwd);
		Member member = MemberDAO.update(id, firstName, lastName, mail, newPwd);
		if(member == null) throw new MemberUpdateException();
		return member;
	}
	
	/**
	 * Effectue la déconnexion d'un utilisateur
	 * @param id l'id de l'utilisateur souhaitant se déconnecter
	 * @param token le token associé à la requête envoyée par le client
	 * @throws IncompleteDataException dans le cas où le mail fourni est vide
	 * @throws UserNotFoundException dans le cas où aucun utilisateur n'est associé à l'e-mail fourni
	 * @throws WrongTokenException dans le cas où l'utilisateur n'est pas connecté
	 */
	public static void logout(Long id, String token) throws IncompleteDataException, UserNotFoundException, WrongTokenException, TokenDeletionException {
		String[] fields = new String[] {Long.toString(id), token};
		User user = Validator.verifyCreatedUser(fields);
		if(!UserDAO.deleteToken(user.getId())) throw new TokenDeletionException();
		user.setToken(null);
	}
	
	/**
	 * Permet d'encrypter le mot de passe avant de le stocker dans la base de données
	 * @param pwd le mot de passe à encrypter
	 * @return le mot de passe encrypté
	 */
	public static String encryptPassword(String pwd) {
		return BCrypt.hashpw(pwd, BCrypt.gensalt());
	}
	
	/**
	 * Permet la suppression du compte d'un membre
	 * @param id l'id du membre ayant demandé la suppression de son compte
	 * @param token le token de la session active du membre
	 * @throws IncompleteDataException dans le cas où certains champs n'ont pas été renseignés
	 * @throws UserNotFoundException dans le cas où un problème est survenu lors de la récupération du membre à supprimer dans la base de données
	 * @throws WrongTokenException dans le cas où le token reçu ne correspond pas à celui correspondant à l'utilisateur ayant fait la requête
	 * @throws MemberDeletionException dans le cas où un problème est survenu lors de la suppression du membre
	 * @throws ChatNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static void deleteAccount(long id, String token) throws IncompleteDataException, UserNotFoundException, WrongTokenException, MemberDeletionException, NumberFormatException, ChatNotFoundException {
		String[] fields = new String[] {Long.toString(id), token};
		Validator.verifyCreatedMember(fields);
		if(!MemberDAO.delete(id)) throw new MemberDeletionException();
	}
}
