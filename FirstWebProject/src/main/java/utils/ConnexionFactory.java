package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnexionFactory {
	
	private static Connection connect = null;
	
	
	
	private ConnexionFactory() throws SQLException {
		
		try {
		    // lecture du contexte JDNI de notre servlet
		   Context initContext =  new InitialContext() ;
		    // initialisation de ce contexte
		   Context envContext  = (Context)initContext.lookup("java:/comp/env") ;
		    // lecture de la datasource définie par requête JNDI
		   DataSource ds = (DataSource)envContext.lookup("jdbc/tp-servlet") ;
		    // demande d'une connexion à cette datasource
		   ConnexionFactory.connect = ds.getConnection();
		}  catch (NamingException e) {
		    // gestion de l'exception
		}  catch (SQLException e) {
		    // gestion de l'exception
		}
		
//		String[] config = {"jdbc:mysql://127.0.0.1:3306/javaee", "root", "databouteilleorange"};
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			ConnexionFactory.connect = DriverManager.getConnection(config[0], config[1], config[2]);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	
	public static Connection getConnect() throws SQLException {
		if (connect == null) {
			try {
				new ConnexionFactory();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connect;
	}

	private static String[] loadConfig(String path) {
		String[] str = new String[3];
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			for (int i=0; i<3; i++) {
				try {
					str[i] = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			e.printStackTrace();
		}
		return str;
	}
}