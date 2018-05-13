package fr.cpasam.leonardo.utilities;

import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.exceptions.WrongTokenException;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.model.user.UserDAO;

public class Validator {

	/**
	 * Vérifie que les champs passés ne soient pas nuls
	 * @param fields le tableau constitué des champs à tester
	 * @return true si tous les champs sont non nuls, ou false sinon
	 */
	public static boolean checkFields(String[] fields) {
		for(String f : fields) if(f == null) return false;
		return true;
	}
	
	/**
	 * Vérifie le token envoyé dans la requête de l'utilisateur avec celui enregistré dans la base de données
	 * @param userId l'identifiant de l'utilisateur à l'origine de la requête
	 * @param token le token envoyé dans la requête
	 * @return true si les token correspondent, ou false sinon
	 */
	public static boolean checkCSRF(long userId, String token) {
		return UserDAO.getUserById(userId).getToken().equals(token);
	}
	
	/**
	 * Vérifie que les données de la requêtes sont correctes pour un membre connecté
	 * @param args l'ensemble des champs envoyé dans la requête du client
	 * @throws IncompleteDataException dans le cas où certains champs n'ont pas été renseignés
	 * @throws UserNotFoundException dans le cas où un problème est survenu lors de la récupération du membre
	 * @throws WrongTokenException dans le cas où le token reçu ne correspond pas à celui correspondant au membre ayant fait la requête
	 * @throws ChatNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static void verifyCreatedMember(String[] fields) throws IncompleteDataException, UserNotFoundException, WrongTokenException, NumberFormatException, ChatNotFoundException {
		if(!checkFields(fields)) throw new IncompleteDataException();
		if(MemberDAO.get(Long.parseLong(fields[0])) == null) throw new UserNotFoundException();
		if(!checkCSRF(Long.parseLong(fields[0]), fields[fields.length-1])) throw new WrongTokenException();
	}
	
	/**
	 * Vérifie que les données de la requêtes sont correctes pour un utilisateur connecté
	 * @param args l'ensemble des champs envoyé dans la requête du client
	 * @throws IncompleteDataException dans le cas où certains champs n'ont pas été renseignés
	 * @throws UserNotFoundException dans le cas où un problème est survenu lors de la récupération de l'utilisateur
	 * @throws WrongTokenException dans le cas où le token reçu ne correspond pas à celui correspondant à l'utilisateur ayant fait la requête
	 */
	public static User verifyCreatedUser(String[] fields) throws IncompleteDataException, UserNotFoundException, WrongTokenException {
		if(!checkFields(fields)) throw new IncompleteDataException();
		User user = UserDAO.getUserById(Long.parseLong(fields[0]));
		if(user == null) throw new UserNotFoundException();
		if(!checkCSRF(Long.parseLong(fields[0]), fields[fields.length-1])) throw new WrongTokenException();
		return user;
	}
	
}
