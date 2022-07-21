package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class UserFactory {
	
	
	
	public static User getUser (String username) {
		User user = new User();
		user.setUsername(username);
		
		try {
			PreparedStatement ps;
			ps = ConnexionFactory.getConnect()
					.prepareStatement("SELECT * FROM javaee.users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getString(4) != null) {
				user.setVideoGame(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}

}
