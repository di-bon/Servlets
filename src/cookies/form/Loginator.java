// Francesco Di Bon 5BIA 12-05-2022

package cookies.form;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Loginator
 */
@WebServlet("/App")
public class Loginator extends HttpServlet {
//	private static final String STORED_USERNAME = "pippo";
//	private static final String STORED_PASSWORD = "pippo1234";
	private static final long serialVersionUID = 1L;
	private RequestDispatcher disp = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loginator() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disp = null;
		String requestSource = request.getParameter("source");
//		System.out.println("requestSource: " + requestSource);
		
		if (requestSource == null || requestSource.equals("login.jsp")) {			
//			System.out.println("The value of 'requestSource' is null");
			System.out.println("boh");
			checkSession(request, response);
		}
		
//		if (requestSource == null) {
//			System.out.println("The value of 'requestSource' is null");
//			RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
//			disp.forward(request, response);
//			return;
//		}

		if (requestSource != null && disp == null) {
//			System.out.println("Source: " + requestSource);	
			if (requestSource.equals("login.jsp")) {
				handleLogin(request, response);
			} else if (requestSource.equals("logout.jsp")) {
				handleLogout(request, response);
			}
		}
		if (disp == null) {
			disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
		}
		disp.forward(request, response);
	}

	private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		if (email != null && password != null) {
			DbHelper dbHelper = new DbHelper();
			try { 
				dbHelper.connect();
				boolean doesUserExist = dbHelper.logon(email, password);
				dbHelper.disconnect();
				
				if (doesUserExist) {
					session.setMaxInactiveInterval(60);
					dbHelper.connect();
					String name = dbHelper.getName(email);
					dbHelper.disconnect();
					if (name.equals("")) {
						name = email;
					}
					Cookie cookie = new Cookie("JSESSIONID", session.getId());
					cookie.setMaxAge(Integer.MAX_VALUE);
					response.addCookie(cookie);
					session.setAttribute("name", name);
					session.setAttribute("email", email);
					session.setAttribute("password", password);
					session.setAttribute("lastlogin", new Date().toString());
					session.setAttribute("showlogintoast", "true");
					request.setAttribute("email", email);
					disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
					return;
				}
					session.setMaxInactiveInterval(60);
					session.setAttribute("wrongcredentials", true);
			} catch (SQLException sqle) {
				request.setAttribute("error", "SQLException");
				request.setAttribute("errorMessage", sqle.getMessage());
				session.invalidate();
				disp = request.getRequestDispatcher("/WEB-INF/Error.jsp");
				return;
			}
			
//			if (username.equals(STORED_USERNAME) && password.equals(STORED_PASSWORD)) {
//				System.out.println("username and password match");
//				HttpSession session = request.getSession();
//				session.setMaxInactiveInterval(10);
//				session.setAttribute("username", username);
//				disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
////				disp.forward(request, response);
//				return;
//			}
//			request.setAttribute("wrongPassword", true);
			
			session.setMaxInactiveInterval(60);
			session.setAttribute("wrongcredentials", true);
			disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
			return;
		}
	}
	
	private void checkSession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pastEmail = (String) session.getAttribute("email");
		String pastPassword = (String) session.getAttribute("password");
		
		if (pastEmail == null || pastPassword == null) {
			return;
		}
		
		try {
			DbHelper dbHelper = new DbHelper();
			dbHelper.connect();
			boolean found = dbHelper.logon(pastEmail, pastPassword);
			dbHelper.disconnect();
			if (found) {
				session.setAttribute("showlogintoast", "false");
				disp = request.getRequestDispatcher("/WEB-INF/Logout.jsp");
				return;
			}
		} catch (SQLException sqle) {
			return;
		}
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		disp = request.getRequestDispatcher("/WEB-INF/Login.jsp");
	}
}