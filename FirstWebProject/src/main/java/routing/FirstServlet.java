package routing;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.ConnexionFactory;
import utils.PasswordHash;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FirstServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String submit = request.getParameter("submit");
		switch (submit) {
		case "log in":

			try {
				logIn(request, response);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidKeySpecException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case "sign in":
			request.setAttribute("username", request.getParameter("username"));
			request.setAttribute("password", request.getParameter("password"));
			request.setAttribute("sign", "signing");
			index(request, response);
			break;

		case "save":
			try {
				save(request, response);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case "disconnect":
			request.getSession().invalidate();
			Cookie[] yums = request.getCookies();
			if (yums != null)
				for (Cookie yum : yums) {
					yum.setMaxAge(0);
					response.addCookie(yum);
				}
			index(request, response);
			break;

		case "click":
			index(request, response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void logIn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		if (isFieldNull(request, "log") && isFieldNull(request, "username") && isFieldNull(request, "password")) {
			request.setAttribute("log", "logging");
			index(request, response);
			return;
		}
		
		
		if (!isFieldNull(request, "username") && isFieldNull(request, "password")) {
			request.setAttribute("signInCheck", "password empty");
			request.setAttribute("log", "logging");
			request.setAttribute("username", request.getParameter("username"));
			index(request, response);
		}
		
		if (isFieldNull(request, "username") && !isFieldNull(request, "password")) {
			request.setAttribute("signInCheck", "username empty");
			request.setAttribute("log", "logging");
			index(request, response);
		}
		
		if (!doUserExists(request.getParameter("username"))) {
			request.setAttribute("signInCheck", "username doesn't exist");
			request.setAttribute("log", "logging");
			index(request, response);
			System.out.println("log in third if");
		} 
		
		
		

		if (checkPassword(request)) {
			connectUser(request, response);
			
		} else {

			request.setAttribute("signInCheck", "wrong password");
			request.setAttribute("username", request.getParameter("username"));
			request.setAttribute("log", "logging");
			index(request, response);
		}

	}

	protected void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		if (isFieldNull(request, "username")) {
			request.setAttribute("signInCheck", "Username empty");
			request.setAttribute("sign", "signing");
			index(request, response);
			return;
		}
		if (isFieldNull(request, "password")) {
			request.setAttribute("signInCheck", "Password empty");
			request.setAttribute("sign", "signing");
			index(request, response);
			return;
		}

		if (doUserExists(request.getParameter("username"))) {
			request.setAttribute("signInCheck", "User already exists");
			request.setAttribute("sign", "signing");
			index(request, response);
			return;
		}

		if (!areStringsSame(request.getParameter("password"), request.getParameter("password2"))) {
			request.setAttribute("signInCheck", "Password not the same");
			request.setAttribute("sign", "signing");
			index(request, response);
			return;
		}

		saveNewUser(request);
		HttpSession session = request.getSession();
		session.setAttribute("username", request.getParameter("username"));
		index(request, response);

	}

	protected void connectUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("username", request.getParameter("username"));
		if (request.getParameter("checkbox") != null) {
			Cookie yum = new Cookie("username", request.getParameter("username"));
			yum.setMaxAge(60 * 10);
			response.addCookie(yum);
		}
		index(request, response);
	}
	
	protected boolean checkPassword(HttpServletRequest request) {
		PreparedStatement preSta;
		try {
			preSta = ConnexionFactory.getConnect().prepareStatement("SELECT * FROM javaee.users WHERE username=?");
			preSta.setString(1, request.getParameter("username"));
			ResultSet rs = preSta.executeQuery();
			rs.next();
			if (PasswordHash.validatePassword(request.getParameter("password"), rs.getString(3))) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	protected boolean isFieldNull(HttpServletRequest request, String field) {
		if (request.getParameter(field) == null) {
			return true;
		}
		if (request.getParameter(field).equals("")) {
			return true;
		}
		return false;
	}

	protected boolean doUserExists(String username) {
		try {
			PreparedStatement ps = ConnexionFactory.getConnect()
					.prepareStatement("SELECT * FROM javaee.users WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (areStringsSame(rs.getString(2), username)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	protected boolean areStringsSame(String first, String second) {
		if (first.equals(second)) {
			return true;
		}
		return false;
	}

	protected void saveNewUser(HttpServletRequest request) {
		try {
			PreparedStatement ps = ConnexionFactory.getConnect().prepareStatement("INSERT javaee.users (username, password) VALUES (?, ?)");
			ps.setString(1, request.getParameter("username"));
			ps.setString(2, PasswordHash.createHash(request.getParameter("password")));
			ps.execute();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
