package DAO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {

	 private static Connection connection ;
			  
			  static {
		try {
//			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/XE?serverTimezone=UTC","postgres","xe");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bibliotheque?serverTimezone=UTC","root","");

			if (connection!=null)
			System.out.println("Connexion établie");

		}
		catch (Exception e) {
			System.out.println("connexion impossible");
			JOptionPane.showMessageDialog(null,"Erreur de connection","Erreur", JOptionPane.ERROR_MESSAGE);
	}
	}public static Connection getConnexion() {return connection;}}

	
	
	
