package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.user.Admin;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.model.user.UserDAO;
import fr.cpasam.leonardo.utilities.Connexion;
import fr.cpasam.leonardo.utilities.DAOManager;

public class TestJDBC extends DAOManager {


	public static void main(String[] args) throws Exception {
	User user = new Member(10,"Hanna","Ri","rihanna@loveuse.com","riri","puteputepute");
	UserDAO.saveToken(user);
	
	}
}