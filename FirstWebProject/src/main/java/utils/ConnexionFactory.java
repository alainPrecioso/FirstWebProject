package utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnexionFactory {
	
	private static Connection connect = null;
	
	
	
	private ConnexionFactory() {
		
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
		
	}

	
	public static Connection getConnect() {
		if (connect == null) {
			new ConnexionFactory();
		}
		return connect;
	}
}