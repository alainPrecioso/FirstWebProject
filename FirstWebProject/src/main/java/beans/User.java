package beans;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3384610494902867598L;
	private String username;
	private String videoGame;
	
	
	public User() {
		super();
	}
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getVideoGame() {
		return videoGame;
	}


	public void setVideoGame(String videoGame) {
		this.videoGame = videoGame;
	}


	
	
	

}
