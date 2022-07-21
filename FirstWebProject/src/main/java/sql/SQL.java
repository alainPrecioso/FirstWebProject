package sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnexionFactory;

public class SQL {
	
	
	
	
	public static ResultSet getRS(String username) {
		try {
			PreparedStatement preSta = ConnexionFactory.getConnect().prepareStatement("SELECT * FROM javaee.users WHERE username=?");
			preSta.setString(1, username);
			return preSta.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	

}
