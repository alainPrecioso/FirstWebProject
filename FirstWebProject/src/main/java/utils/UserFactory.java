package utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class UserFactory {
	
	private static User user = null;
	
	
	public static User getUser (String username) {
		user = new User();
		user.setUsername(username);
		
		PreparedStatement ps;
		try {
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
