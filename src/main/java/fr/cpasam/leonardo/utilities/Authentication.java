package fr.cpasam.leonardo.utilities;

import org.mindrot.jbcrypt.BCrypt;

import fr.cpasam.leonardo.errors.MemberCreationError;
import fr.cpasam.leonardo.exceptions.BadPasswordException;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;


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
			if(!BCrypt.checkpw(pwd, user.GetUserPwd())) throw new BadPasswordException();				
		return user;
	}
	
	/** 
	 * Vérifie si un utilisateur est associé à l'e-mail donné
	 * @param mail l'e-mail faisant office de login
	 * @return user l'utilisateur associé à l'e-mail donné, ou null si aucun utilisateur n'y est associé
	 */
	private static User exists(String mail) {
		return getUser(mail);
	}
	
	/**
	 * Sauvegarde le token généré lors de la connexion d'un utilisateur dans la base de données
	 * @param user l'utilisateur duquel on souhaite enregistrer le token
	 */
	public static void saveToken(User user) {
		updateUser(user.GetUserId(), user.GetUserFistName(), user.GetUserLastName(), user.GetUserEmail(), user.GetUserPwd(), user.getToken());
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
	public static void registration(String firstName, String lastName, String mail, String pwd) throws IncompleteDataException, MemberCreationError {
		if(firstName == null || lastName == null || mail == null || pwd == null) throw new IncompleteDataException();
		String newPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
		if(MemberDAO.CreateMember(firstName, lastName, mail, newPwd) == null) throw new MemberCreationError();
	}
	
}
