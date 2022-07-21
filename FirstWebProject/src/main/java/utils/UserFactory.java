package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class UserFactory {
	
	
	
	public static User getUser (ResultSet rs) {
		User user = new User();
		try {
			user.setUsername(rs.getString(2));
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
